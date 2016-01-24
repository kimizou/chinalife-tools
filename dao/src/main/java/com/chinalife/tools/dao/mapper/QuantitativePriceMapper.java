package com.chinalife.tools.dao.mapper;

import com.chinalife.tools.dao.entity.QuantitativePrice;
import com.chinalife.tools.dao.entity.QuantitativePriceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QuantitativePriceMapper {
    int countByExample(QuantitativePriceExample example);

    int deleteByExample(QuantitativePriceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(QuantitativePrice record);

    int insertSelective(QuantitativePrice record);

    List<QuantitativePrice> selectByExample(QuantitativePriceExample example);

    QuantitativePrice selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") QuantitativePrice record, @Param("example") QuantitativePriceExample example);

    int updateByExample(@Param("record") QuantitativePrice record, @Param("example") QuantitativePriceExample example);

    int updateByPrimaryKeySelective(QuantitativePrice record);

    int updateByPrimaryKey(QuantitativePrice record);
}