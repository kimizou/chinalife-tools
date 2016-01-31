package com.chinalife.tools.controller;

import com.chinalife.tools.common.BizResultCodeEnum;
import com.chinalife.tools.common.exception.BizException;
import com.chinalife.tools.dao.entity.QuantitativePrice;
import com.chinalife.tools.dao.entity.SumResult;
import com.chinalife.tools.dao.entity.Workload;
import com.chinalife.tools.dao.entity.WorkloadDetail;
import com.chinalife.tools.service.QuantitativeAssessmentService;
import com.chinalife.tools.service.QuantitativePriceService;
import com.chinalife.tools.util.PageableContent;
import com.chinalife.tools.util.excel.ExcelReaderUtil;
import com.chinalife.tools.util.excel.RowReader;
import com.chinalife.tools.web.WebResult;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/24.
 */
@Controller
@RequestMapping("quantitative-assessment")
public class QuantitativeAssessmentController {
    @Autowired
    private QuantitativePriceService quantitativePriceService;

    @Autowired
    private QuantitativeAssessmentService quantitativeAssessmentService;

    @RequestMapping(value = "price", method = RequestMethod.GET)
    public String staffIndex() {
        return "quantitative-assessment/price";
    }

    @RequestMapping(value = "workload", method = RequestMethod.GET)
    public String workloadIndex() {
        return "quantitative-assessment/workload/index";
    }

    @RequestMapping(value = "workload/add", method = RequestMethod.GET)
    public String workloadAdd(HttpServletRequest request) {
        request.getSession().removeAttribute("importWorkloadData");
        return "quantitative-assessment/workload/edit";
    }

    @ResponseBody
    @RequestMapping(value = "price/search", method = RequestMethod.POST)
    public WebResult<PageableContent<QuantitativePrice>> searchPrice(int currentPage, int rows, String taskName) {
        PageableContent<QuantitativePrice> data = quantitativePriceService.search(currentPage, rows, taskName);
        return new WebResult<PageableContent<QuantitativePrice>>(BizResultCodeEnum.SUCCESS, data);
    }

    @ResponseBody
    @RequestMapping(value = "price/import", method = RequestMethod.POST)
    public WebResult<PageableContent<QuantitativePrice>> importPrice(@RequestParam("fileToUpload") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        try {
            Workbook wb = null;
            if (fileName.endsWith(".xls")) {
                wb = new HSSFWorkbook(file.getInputStream());
            } else if (fileName.endsWith(".xlsx")) {
                wb = new XSSFWorkbook(file.getInputStream());
            } else {
                throw new BizException("不支持的文件类型");
            }
        } catch (IOException e) {
            throw new BizException(e);
        }

        return new WebResult<PageableContent<QuantitativePrice>>(BizResultCodeEnum.SUCCESS);
    }

    @ResponseBody
    @RequestMapping(value = "workload/import", method = RequestMethod.POST)
    public WebResult<String> importWorkload(@RequestParam("fileToUpload") MultipartFile file, HttpServletRequest request) {
        RowReader rowReader = new RowReader();
        try {
            ExcelReaderUtil.readExcel(rowReader, file.getOriginalFilename(), file.getInputStream());
        } catch (IOException e) {
            throw new BizException(e);
        }
        request.getSession().setAttribute("importWorkloadData", rowReader.getWorkloadDetails());
        return new WebResult<String>(BizResultCodeEnum.SUCCESS);
    }

    @ResponseBody
    @RequestMapping(value = "workload/import/search", method = RequestMethod.POST)
    public WebResult<PageableContent<WorkloadDetail>> importWorkloadSearch(int currentPage, int rows, HttpServletRequest request) {
        List<WorkloadDetail> list = new ArrayList<WorkloadDetail>();

        int totalRows = 0;
        List<WorkloadDetail> data = (List<WorkloadDetail>) request.getSession().getAttribute("importWorkloadData");
        if (!CollectionUtils.isEmpty(data)) {
            totalRows = data.size();
            int startIndex = (currentPage - 1) * rows;
            int endIndex = startIndex + rows - 1;
            for (int i = startIndex; i <= endIndex && i <= data.size() - 1; i++) {
                list.add(data.get(i));
            }
        }

        PageableContent<WorkloadDetail> result = new PageableContent<WorkloadDetail>(list, currentPage, rows, totalRows);

        return new WebResult<PageableContent<WorkloadDetail>>(BizResultCodeEnum.SUCCESS, result);
    }

    @ResponseBody
    @RequestMapping(value = "workload/import/save", method = RequestMethod.POST)
    public WebResult<String> importWorkloadSave(String yearMonth, HttpServletRequest request) {
        List<WorkloadDetail> list = (List<WorkloadDetail>) request.getSession().getAttribute("importWorkloadData");
        if (CollectionUtils.isEmpty(list)) {
            throw new BizException("工作量数据不能为空，请先选择文件导入");
        }
        quantitativeAssessmentService.saveWorkLoad(yearMonth, list);
        return new WebResult<String>(BizResultCodeEnum.SUCCESS);
    }

    @ResponseBody
    @RequestMapping(value = "workload/search", method = RequestMethod.POST)
    public WebResult<PageableContent<Workload>> workloadSearch(int currentPage, int rows, String yearMonth) {
        PageableContent<Workload> list = quantitativeAssessmentService.searchWorkload(currentPage, rows, yearMonth);
        return new WebResult<PageableContent<Workload>>(BizResultCodeEnum.SUCCESS, list);
    }

    @RequestMapping(value = "workload/detail", method = RequestMethod.GET)
    public String workloadDetail(Model model, Long id) {
        model.addAttribute("workloadId", id);
        return "quantitative-assessment/workload/detail";
    }

    @ResponseBody
    @RequestMapping(value = "workload/sum/search", method = RequestMethod.POST)
    public WebResult<PageableContent<SumResult>> sumResultSearch(int currentPage, int rows, Long workloadId) {
        PageableContent<SumResult> list = quantitativeAssessmentService.searchSumResult(currentPage, rows, workloadId);
        return new WebResult<PageableContent<SumResult>>(BizResultCodeEnum.SUCCESS, list);
    }

    @ResponseBody
    @RequestMapping(value = "workload/detail/search", method = RequestMethod.POST)
    public WebResult<PageableContent<WorkloadDetail>> searchWorkloadDetails(int currentPage, int rows, Long workloadId) {
        PageableContent<WorkloadDetail> list = quantitativeAssessmentService.searchWorkloadDetails(currentPage, rows, workloadId);
        return new WebResult<PageableContent<WorkloadDetail>>(BizResultCodeEnum.SUCCESS, list);
    }
}
