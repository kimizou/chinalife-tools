package com.chinalife.tools.dao.entity;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/1/31.
 */
public class SumResult {
    private String employeeNum;
    private String employeeName;
    private BigDecimal amount;

    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
