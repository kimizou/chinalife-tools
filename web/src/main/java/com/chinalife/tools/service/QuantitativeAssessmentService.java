package com.chinalife.tools.service;

import com.chinalife.tools.dao.entity.SumResult;
import com.chinalife.tools.dao.entity.Workload;
import com.chinalife.tools.dao.entity.WorkloadDetail;
import com.chinalife.tools.util.PageableContent;

import java.util.List;

/**
 * Created by Administrator on 2016/1/31.
 */
public interface QuantitativeAssessmentService {
    void saveWorkLoad(String yearMonth, List<WorkloadDetail> workloadDetails);

    PageableContent<Workload> searchWorkload(int currentPage, int rows, String yearMonth);

    PageableContent<SumResult> searchSumResult(int currentPage, int rows);
}
