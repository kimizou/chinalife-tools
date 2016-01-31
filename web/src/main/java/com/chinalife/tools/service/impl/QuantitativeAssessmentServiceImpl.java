package com.chinalife.tools.service.impl;

import com.chinalife.tools.dao.entity.Workload;
import com.chinalife.tools.dao.entity.WorkloadDetail;
import com.chinalife.tools.dao.entity.WorkloadExample;
import com.chinalife.tools.dao.mapper.WorkloadDetailMapperExt;
import com.chinalife.tools.dao.mapper.WorkloadMapperExt;
import com.chinalife.tools.dao.util.Page;
import com.chinalife.tools.service.QuantitativeAssessmentService;
import com.chinalife.tools.util.PageableContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/31.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class QuantitativeAssessmentServiceImpl implements QuantitativeAssessmentService {
    @Autowired
    private WorkloadMapperExt workloadMapperExt;

    @Autowired
    private WorkloadDetailMapperExt workloadDetailMapperExt;

    public void saveWorkLoad(String yearMonth, List<WorkloadDetail> workloadDetails) {
        Workload workload = new Workload();
        workload.setYearMonthDate(yearMonth);
        workloadMapperExt.insert(workload);
        workloadDetailMapperExt.insertWorkloads(workload.getId(), workloadDetails);
    }

    public PageableContent<Workload> searchWorkload(int currentPage, int rows, String yearMonth) {
        List<Workload> list = new ArrayList<Workload>();

        WorkloadExample example = new WorkloadExample();
        if (StringUtils.hasText(yearMonth)) {
            example.createCriteria().andYearMonthDateEqualTo(yearMonth);
        }
        int totalCount = workloadMapperExt.countByExample(example);
        if (totalCount > 0) {
            example.setPage(new Page(currentPage, rows));
            list = workloadMapperExt.selectByExample(example);
        }
        return new PageableContent<Workload>(list, currentPage, rows, totalCount);
    }
}
