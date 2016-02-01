package com.chinalife.tools.dao.mapper;

import com.chinalife.tools.dao.entity.QuantitativePrice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuantitativePriceMapperExt extends QuantitativePriceMapper {
    void insertPrices(@Param("list") List<QuantitativePrice> list);
}