package com.chinalife.tools.dao.mapper;

import com.chinalife.tools.dao.entity.Workload;
import com.chinalife.tools.dao.entity.WorkloadExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WorkloadMapper {
    int countByExample(WorkloadExample example);

    int deleteByExample(WorkloadExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Workload record);

    int insertSelective(Workload record);

    List<Workload> selectByExample(WorkloadExample example);

    Workload selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Workload record, @Param("example") WorkloadExample example);

    int updateByExample(@Param("record") Workload record, @Param("example") WorkloadExample example);

    int updateByPrimaryKeySelective(Workload record);

    int updateByPrimaryKey(Workload record);
}