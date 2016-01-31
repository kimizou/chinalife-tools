package com.chinalife.tools.dao.entity;

import java.io.Serializable;

public class Workload implements Serializable {
    private Long id;

    private String yearMonthDate;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYearMonthDate() {
        return yearMonthDate;
    }

    public void setYearMonthDate(String yearMonthDate) {
        this.yearMonthDate = yearMonthDate == null ? null : yearMonthDate.trim();
    }
}