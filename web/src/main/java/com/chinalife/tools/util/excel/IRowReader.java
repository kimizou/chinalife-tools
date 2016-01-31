package com.chinalife.tools.util.excel;

import java.util.List;

/**
 * Created by Administrator on 2016/1/31.
 */
public interface IRowReader {
    /**
     * 业务逻辑实现方法
     *
     * @param sheetIndex
     * @param curRow
     * @param rowlist
     */
    void getRows(int sheetIndex, int curRow, List<String> rowlist);
}
