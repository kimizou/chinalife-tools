package com.chinalife.tools.dao.mapper;

import com.chinalife.tools.dao.entity.SumResult;
import com.chinalife.tools.dao.entity.WorkloadDetail;
import com.chinalife.tools.dao.util.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkloadDetailMapperExt extends WorkloadDetailMapper {
    void insertWorkloads(@Param("workloadId") Long workloadId, @Param("list") List<WorkloadDetail> list);

    int countSumResult();

    List<SumResult> selectSumResult(@Param("page") Page page);
}