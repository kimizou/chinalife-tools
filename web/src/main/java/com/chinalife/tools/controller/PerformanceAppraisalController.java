package com.chinalife.tools.controller;

import com.chinalife.tools.common.BizResultCodeEnum;
import com.chinalife.tools.dao.entity.PerfApprStaff;
import com.chinalife.tools.service.PerfApprStaffService;
import com.chinalife.tools.util.PageableContent;
import com.chinalife.tools.web.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2016/1/17.
 */
@Controller
@RequestMapping("performance-appraisal")
public class PerformanceAppraisalController {
    @Autowired
    private PerfApprStaffService perfApprStaffService;

    @RequestMapping(value = "staff", method = RequestMethod.GET)
    public String staffIndex() {
        return "performance-appraisal/staff";
    }

    @ResponseBody
    @RequestMapping(value = "staff/search", method = RequestMethod.POST)
    public WebResult<PageableContent<PerfApprStaff>> staffSearch(int currentPage, int rows) {
        PageableContent<PerfApprStaff> data = perfApprStaffService.search(currentPage, rows);
        return new WebResult<PageableContent<PerfApprStaff>>(BizResultCodeEnum.SUCCESS, data);
    }
}
