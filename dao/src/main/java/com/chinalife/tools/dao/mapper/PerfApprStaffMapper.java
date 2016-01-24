package com.chinalife.tools.dao.mapper;

import com.chinalife.tools.dao.entity.PerfApprStaff;
import com.chinalife.tools.dao.entity.PerfApprStaffExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PerfApprStaffMapper {
    int countByExample(PerfApprStaffExample example);

    int deleteByExample(PerfApprStaffExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PerfApprStaff record);

    int insertSelective(PerfApprStaff record);

    List<PerfApprStaff> selectByExample(PerfApprStaffExample example);

    PerfApprStaff selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PerfApprStaff record, @Param("example") PerfApprStaffExample example);

    int updateByExample(@Param("record") PerfApprStaff record, @Param("example") PerfApprStaffExample example);

    int updateByPrimaryKeySelective(PerfApprStaff record);

    int updateByPrimaryKey(PerfApprStaff record);
}