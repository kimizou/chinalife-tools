package com.chinalife.tools.controller;

import com.chinalife.tools.common.BizResultCodeEnum;
import com.chinalife.tools.common.exception.BizException;
import com.chinalife.tools.dao.entity.QuantitativePrice;
import com.chinalife.tools.service.QuantitativePriceService;
import com.chinalife.tools.util.PageableContent;
import com.chinalife.tools.web.WebResult;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by Administrator on 2016/1/24.
 */
@Controller
@RequestMapping("quantitative-assessment")
public class QuantitativeAssessmentController {
    @Autowired
    private QuantitativePriceService quantitativePriceService;

    @RequestMapping(value = "price", method = RequestMethod.GET)
    public String staffIndex() {
        return "quantitative-assessment/price";
    }

    @RequestMapping(value = "workload", method = RequestMethod.GET)
    public String workloadIndex() {
        return "quantitative-assessment/workload/index";
    }

    @RequestMapping(value = "workload/add", method = RequestMethod.GET)
    public String workloadAdd() {
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
}
