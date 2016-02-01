package com.chinalife.tools.service;

import com.chinalife.tools.dao.entity.PerfApprStaff;
import com.chinalife.tools.dao.util.PageableContent;

/**
 * Created by Administrator on 2016/1/17.
 */
public interface PerfApprStaffService {
    PageableContent<PerfApprStaff> search(int currentPage, int rows);
}
