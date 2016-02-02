package com.chinalife.tools.service.impl;

import com.chinalife.tools.dao.entity.QuantitativePrice;
import com.chinalife.tools.dao.entity.QuantitativePriceExample;
import com.chinalife.tools.dao.mapper.QuantitativePriceMapperExt;
import com.chinalife.tools.dao.util.Page;
import com.chinalife.tools.service.QuantitativePriceService;
import com.chinalife.tools.dao.util.PageableContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/24.
 */
@Service
public class QuantitativePriceServiceImpl implements QuantitativePriceService {
    @Autowired
    private QuantitativePriceMapperExt quantitativePriceMapper;

    public PageableContent<QuantitativePrice> search(int currentPage, int rows, String taskName) {
        List<QuantitativePrice> list = new ArrayList<>();
        QuantitativePriceExample example = new QuantitativePriceExample();
        if (StringUtils.hasText(taskName)) {
            example.createCriteria().andTaskNameLike("%" + taskName.trim() + "%");
        }
        int total = quantitativePriceMapper.countByExample(example);
        if (total > 0) {
            example.setPage(new Page(currentPage, rows));
            list = quantitativePriceMapper.selectByExample(example);
        }
        return new PageableContent<>(list, currentPage, rows, total);
    }

    public void save(List<QuantitativePrice> quantitativePrices) {
        quantitativePriceMapper.deleteByExample(null);
        quantitativePriceMapper.insertPrices(quantitativePrices);
    }
}
