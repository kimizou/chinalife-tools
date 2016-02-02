package com.chinalife.tools.controller;

import com.chinalife.tools.common.BizResultCodeEnum;
import com.chinalife.tools.common.exception.BizException;
import com.chinalife.tools.dao.entity.SumResult;
import com.chinalife.tools.dao.entity.Workload;
import com.chinalife.tools.dao.entity.WorkloadDetail;
import com.chinalife.tools.service.QuantitativeAssessmentService;
import com.chinalife.tools.dao.util.PageableContent;
import com.chinalife.tools.util.excel.ExcelReaderUtil;
import com.chinalife.tools.util.excel.WorkloadRowReader;
import com.chinalife.tools.web.WebResult;
import com.chinalife.tools.web.exception.ImportFileParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/24.
 */
@Controller
@RequestMapping("quantitative-assessment")
public class QuantitativeAssessmentController {
    private static final String importWorkloadData = "importWorkloadData";

    @Autowired
    private QuantitativeAssessmentService quantitativeAssessmentService;

    @RequestMapping(value = "workload", method = RequestMethod.GET)
    public String workloadIndex() {
        return "quantitative-assessment/workload/index";
    }

    @RequestMapping(value = "workload/add", method = RequestMethod.GET)
    public String workloadAdd(HttpSession session) {
        session.removeAttribute(importWorkloadData);
        return "quantitative-assessment/workload/add";
    }

    @ResponseBody
    @RequestMapping(value = "workload/import", method = RequestMethod.POST)
    public WebResult<String> importWorkload(@RequestParam("fileToUpload") MultipartFile file, HttpSession session) {
        WorkloadRowReader rowReader = new WorkloadRowReader();
        try {
            ExcelReaderUtil.readExcel(rowReader, file.getOriginalFilename(), file.getInputStream());
        } catch (IOException e) {
            throw new BizException(e);
        } catch (ImportFileParseException e) {
            return new WebResult<>(null, e.getMessage(), false);
        }
        session.setAttribute(importWorkloadData, rowReader.getWorkloadDetails());
        return new WebResult<>(BizResultCodeEnum.SUCCESS);
    }



    @ResponseBody
    @RequestMapping(value = "workload/import/search", method = RequestMethod.POST)
    public WebResult<PageableContent<WorkloadDetail>> importWorkloadSearch(int currentPage, int rows, HttpSession session) {
        List<WorkloadDetail> list = new ArrayList<>();

        int totalRows = 0;
        @SuppressWarnings("unchecked")
        List<WorkloadDetail> data = (List<WorkloadDetail>) session.getAttribute(importWorkloadData);
        if (!CollectionUtils.isEmpty(data)) {
            totalRows = data.size();
            int startIndex = (currentPage - 1) * rows;
            int endIndex = startIndex + rows - 1;
            for (int i = startIndex; i <= endIndex && i <= data.size() - 1; i++) {
                list.add(data.get(i));
            }
        }

        PageableContent<WorkloadDetail> result = new PageableContent<>(list, currentPage, rows, totalRows);

        return new WebResult<>(BizResultCodeEnum.SUCCESS, result);
    }

    @ResponseBody
    @RequestMapping(value = "workload/import/save", method = RequestMethod.POST)
    public WebResult<String> importWorkloadSave(String yearMonth, HttpSession session) {
        @SuppressWarnings("unchecked")
        List<WorkloadDetail> list = (List<WorkloadDetail>) session.getAttribute(importWorkloadData);
        if (CollectionUtils.isEmpty(list)) {
            throw new BizException("数据不能为空，请先选择文件导入");
        }
        quantitativeAssessmentService.saveWorkLoad(yearMonth, list);
        return new WebResult<>(BizResultCodeEnum.SUCCESS);
    }

    @ResponseBody
    @RequestMapping(value = "workload/search", method = RequestMethod.POST)
    public WebResult<PageableContent<Workload>> workloadSearch(int currentPage, int rows, String yearMonth) {
        PageableContent<Workload> list = quantitativeAssessmentService.searchWorkload(currentPage, rows, yearMonth);
        return new WebResult<>(BizResultCodeEnum.SUCCESS, list);
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
        return new WebResult<>(BizResultCodeEnum.SUCCESS, list);
    }

    @ResponseBody
    @RequestMapping(value = "workload/detail/search", method = RequestMethod.POST)
    public WebResult<PageableContent<WorkloadDetail>> searchWorkloadDetails(int currentPage, int rows, Long workloadId) {
        PageableContent<WorkloadDetail> list = quantitativeAssessmentService.searchWorkloadDetails(currentPage, rows, workloadId);
        return new WebResult<>(BizResultCodeEnum.SUCCESS, list);
    }

}
