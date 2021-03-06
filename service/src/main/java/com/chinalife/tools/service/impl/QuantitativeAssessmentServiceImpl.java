package com.chinalife.tools.service.impl;

import com.chinalife.tools.dao.entity.SumResult;
import com.chinalife.tools.dao.entity.Workload;
import com.chinalife.tools.dao.entity.WorkloadDetail;
import com.chinalife.tools.dao.entity.WorkloadDetailExample;
import com.chinalife.tools.dao.entity.WorkloadExample;
import com.chinalife.tools.dao.mapper.WorkloadDetailMapperExt;
import com.chinalife.tools.dao.mapper.WorkloadMapperExt;
import com.chinalife.tools.dao.util.Page;
import com.chinalife.tools.service.QuantitativeAssessmentService;
import com.chinalife.tools.dao.util.PageableContent;
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

    @Transactional(readOnly = true)
    public PageableContent<Workload> searchWorkload(int currentPage, int rows, String yearMonth) {
        List<Workload> list = new ArrayList<>();

        WorkloadExample example = new WorkloadExample();
        if (StringUtils.hasText(yearMonth)) {
            example.createCriteria().andYearMonthDateEqualTo(yearMonth);
        }
        int totalCount = workloadMapperExt.countByExample(example);
        if (totalCount > 0) {
            example.setPage(new Page(currentPage, rows));
            example.setOrderByClause("year_month_date desc");
            list = workloadMapperExt.selectByExample(example);
        }
        return new PageableContent<>(list, currentPage, rows, totalCount);
    }

    @Transactional(readOnly = true)
    public PageableContent<SumResult> searchSumResult(int currentPage, int rows, Long workloadId) {
        List<SumResult> list = new ArrayList<>();
        int totalCount = workloadDetailMapperExt.countSumResult(workloadId);
        if (totalCount > 0) {
            list = workloadDetailMapperExt.selectSumResult(workloadId, new Page(currentPage, rows));
        }
        return new PageableContent<>(list, currentPage, rows, totalCount);
    }

    @Transactional(readOnly = true)
    public PageableContent<WorkloadDetail> searchWorkloadDetails(int currentPage, int rows, Long workloadId) {
        List<WorkloadDetail> list = new ArrayList<>();
        WorkloadDetailExample example = new WorkloadDetailExample();
        example.createCriteria().andWorkloadIdEqualTo(workloadId);
        int totalCount = workloadDetailMapperExt.countByExample(example);
        if (totalCount > 0) {
            example.setPage(new Page(currentPage, rows));
            list = workloadDetailMapperExt.selectByExample(example);
        }
        return new PageableContent<>(list, currentPage, rows, totalCount);
    }
}
