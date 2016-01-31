package com.chinalife.tools.dao.mapper;

import com.chinalife.tools.dao.entity.WorkloadDetail;
import com.chinalife.tools.dao.entity.WorkloadDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WorkloadDetailMapper {
    int countByExample(WorkloadDetailExample example);

    int deleteByExample(WorkloadDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WorkloadDetail record);

    int insertSelective(WorkloadDetail record);

    List<WorkloadDetail> selectByExample(WorkloadDetailExample example);

    WorkloadDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WorkloadDetail record, @Param("example") WorkloadDetailExample example);

    int updateByExample(@Param("record") WorkloadDetail record, @Param("example") WorkloadDetailExample example);

    int updateByPrimaryKeySelective(WorkloadDetail record);

    int updateByPrimaryKey(WorkloadDetail record);
}