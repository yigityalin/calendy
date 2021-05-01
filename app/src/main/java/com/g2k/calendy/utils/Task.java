package com.g2k.calendy.utils;

import java.util.Date;

/**
 * Abstract Task class for Event, Reminder, Goal
 * @author Mehmet Kağan İlbak
 * @version 2021/04/29
 */
public abstract class Task {
    protected String description;
    protected Date startDate;
    protected Date endDate;

    public String getName() {
        return description;
    }

    public void setName(String name) {
        this.description = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
