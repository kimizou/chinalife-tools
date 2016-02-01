package com.chinalife.tools.util.excel;

import com.chinalife.tools.dao.entity.QuantitativePrice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PriceRowReader implements IRowReader {
    private List<QuantitativePrice> quantitativePrices = new ArrayList<QuantitativePrice>();

    /*
     * 业务逻辑实现方法
     *
     * @see com.eprosun.util.excel.IRowReader#getRows(int, int, java.util.List)
     */
    public void getRows(int sheetIndex, int curRow, List<String> rowlist) {
        QuantitativePrice p = new QuantitativePrice();
        p.setTaskName(rowlist.get(0));
        p.setPrice(BigDecimal.valueOf(Double.valueOf(rowlist.get(1))));
        quantitativePrices.add(p);
    }

    public List<QuantitativePrice> getQuantitativePrices() {
        return quantitativePrices;
    }

}