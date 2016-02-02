package com.chinalife.tools.util.excel;

import com.chinalife.tools.dao.entity.QuantitativePrice;
import com.chinalife.tools.web.exception.ImportFileParseException;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PriceRowReader implements IRowReader {
    private static final Logger LOGGER = Logger.getLogger(PriceRowReader.class);

    private List<QuantitativePrice> quantitativePrices = new ArrayList<>();

    /*
     * 业务逻辑实现方法
     *
     * @see com.eprosun.util.excel.IRowReader#getRows(int, int, java.util.List)
     */
    public void getRows(int sheetIndex, int curRow, List<String> rowlist) {
        try {
            QuantitativePrice p = new QuantitativePrice();
            p.setTaskName(rowlist.get(0));
            p.setPrice(BigDecimal.valueOf(Double.valueOf(rowlist.get(1))));
            quantitativePrices.add(p);
        } catch (Exception e) {
            LOGGER.error(e);
            throw new ImportFileParseException("解析失败，文件格式错误");
        }
    }

    public List<QuantitativePrice> getQuantitativePrices() {
        return quantitativePrices;
    }

}