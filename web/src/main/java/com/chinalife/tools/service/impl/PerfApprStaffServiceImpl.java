package com.chinalife.tools.service.impl;

import com.chinalife.tools.dao.entity.PerfApprStaff;
import com.chinalife.tools.dao.entity.PerfApprStaffExample;
import com.chinalife.tools.dao.mapper.PerfApprStaffMapper;
import com.chinalife.tools.dao.util.Page;
import com.chinalife.tools.service.PerfApprStaffService;
import com.chinalife.tools.util.PageableContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/1/17.
 */
@Service
public class PerfApprStaffServiceImpl implements PerfApprStaffService {
    @Autowired
    private PerfApprStaffMapper perfApprStaffMapper;

    public PageableContent<PerfApprStaff> search(int currentPage, int rows) {
        PerfApprStaffExample example = new PerfApprStaffExample();
        int total = perfApprStaffMapper.countByExample(example);

        example.setPage(new Page(currentPage, rows));

        List<PerfApprStaff> list = perfApprStaffMapper.selectByExample(example);

        return new PageableContent<PerfApprStaff>(list, currentPage, rows, total);
    }
}
