package com.chinalife.tools.util.excel;

import com.chinalife.tools.common.exception.BizException;
import com.chinalife.tools.dao.entity.WorkloadDetail;
import com.chinalife.tools.web.exception.ImportFileParseException;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class WorkloadRowReader implements IRowReader {
    private static final Logger LOGGER = Logger.getLogger(WorkloadRowReader.class);

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private List<WorkloadDetail> workloadDetails = new ArrayList<>();

    /*
     * 业务逻辑实现方法
     *
     * @see com.eprosun.util.excel.IRowReader#getRows(int, int, java.util.List)
     */
    public void getRows(int sheetIndex, int curRow, List<String> rowlist) {
        try {
            if (curRow < 2) {
                return;
            }
            WorkloadDetail w = new WorkloadDetail();
            w.setType(rowlist.get(0));
            w.setInstitution(rowlist.get(1));
            w.setEmployeeNum(rowlist.get(2));
            w.setOperator(rowlist.get(3));
            w.setSystem(rowlist.get(4));
            w.setProjectCode(rowlist.get(5));
            w.setProjectName(rowlist.get(6));
            try {
                w.setDate(sdf.parse(rowlist.get(7)));
            } catch (ParseException e) {
                throw new BizException(e);
            }
            w.setNum(Integer.valueOf(rowlist.get(9)));
            workloadDetails.add(w);
        } catch (Exception e) {
            LOGGER.error(e);
            throw new ImportFileParseException("解析失败，文件格式错误");
        }
    }

    public List<WorkloadDetail> getWorkloadDetails() {
        return workloadDetails;
    }

}