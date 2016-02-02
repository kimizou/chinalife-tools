package com.chinalife.tools.controller;

import com.chinalife.tools.common.BizResultCodeEnum;
import com.chinalife.tools.common.exception.BizException;
import com.chinalife.tools.dao.entity.QuantitativePrice;
import com.chinalife.tools.dao.util.PageableContent;
import com.chinalife.tools.service.QuantitativePriceService;
import com.chinalife.tools.util.excel.ExcelReaderUtil;
import com.chinalife.tools.util.excel.PriceRowReader;
import com.chinalife.tools.web.WebResult;
import com.chinalife.tools.web.exception.ImportFileParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
 * @author Kimi Zou
 */
@Controller
@RequestMapping("quantitative-assessment/price")
public class QuantitativePriceController {
    private static final String importPriceData = "importPriceData";

    @Autowired
    private QuantitativePriceService quantitativePriceService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String staffIndex() {
        return "quantitative-assessment/price/index";
    }

    @ResponseBody
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public WebResult<PageableContent<QuantitativePrice>> searchPrice(int currentPage, int rows, String taskName) {
        PageableContent<QuantitativePrice> data = quantitativePriceService.search(currentPage, rows, taskName);
        return new WebResult<>(BizResultCodeEnum.SUCCESS, data);
    }

    @ResponseBody
    @RequestMapping(value = "import", method = RequestMethod.POST)
    public WebResult<String> importPrice(@RequestParam("fileToUpload") MultipartFile file, HttpSession session) {
        PriceRowReader rowReader = new PriceRowReader();
        try {
            ExcelReaderUtil.readExcel(rowReader, file.getOriginalFilename(), file.getInputStream());
        } catch (IOException e) {
            throw new BizException(e);
        } catch (ImportFileParseException e) {
            return new WebResult<>(null, e.getMessage(), false);
        }
        session.setAttribute(importPriceData, rowReader.getQuantitativePrices());
        return new WebResult<>(BizResultCodeEnum.SUCCESS);
    }

    @ResponseBody
    @RequestMapping(value = "import/search", method = RequestMethod.POST)
    public WebResult<PageableContent<QuantitativePrice>> importPriceSearch(int currentPage, int rows, HttpSession session) {
        List<QuantitativePrice> list = new ArrayList<>();

        int totalRows = 0;
        @SuppressWarnings("unchecked")
        List<QuantitativePrice> data = (List<QuantitativePrice>) session.getAttribute(importPriceData);
        if (!CollectionUtils.isEmpty(data)) {
            totalRows = data.size();
            int startIndex = (currentPage - 1) * rows;
            int endIndex = startIndex + rows - 1;
            for (int i = startIndex; i <= endIndex && i <= data.size() - 1; i++) {
                list.add(data.get(i));
            }
        }

        PageableContent<QuantitativePrice> result = new PageableContent<>(list, currentPage, rows, totalRows);

        return new WebResult<>(BizResultCodeEnum.SUCCESS, result);
    }

    @ResponseBody
    @RequestMapping(value = "import/save", method = RequestMethod.POST)
    public WebResult<String> importPriceSave(HttpSession session) {
        @SuppressWarnings("unchecked")
        List<QuantitativePrice> list = (List<QuantitativePrice>) session.getAttribute(importPriceData);
        if (CollectionUtils.isEmpty(list)) {
            throw new BizException("工作量数据不能为空，请先选择文件导入");
        }
        quantitativePriceService.save(list);
        return new WebResult<>(BizResultCodeEnum.SUCCESS);
    }

    @RequestMapping(value = "import", method = RequestMethod.GET)
    public String priceImport(HttpSession session) {
        session.removeAttribute(importPriceData);
        return "quantitative-assessment/price/import";
    }
}
