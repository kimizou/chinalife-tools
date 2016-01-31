package com.chinalife.tools.dao.mapper;

import com.chinalife.tools.dao.entity.WorkloadDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkloadDetailMapperExt extends WorkloadDetailMapper {
    void insertWorkloads(@Param("workloadId") Long workloadId, @Param("list") List<WorkloadDetail> list);
}