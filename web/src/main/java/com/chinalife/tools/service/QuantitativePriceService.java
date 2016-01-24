package com.chinalife.tools.service;

import com.chinalife.tools.dao.entity.QuantitativePrice;
import com.chinalife.tools.util.PageableContent;

/**
 * Created by Administrator on 2016/1/24.
 */
public interface QuantitativePriceService {
    PageableContent<QuantitativePrice> search(int currentPage, int rows, String taskName);
}
