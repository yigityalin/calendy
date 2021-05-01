package com.g2k.calendy.utils;

import java.util.Date;

/**
 * Abstract Task class for Event, Reminder, Goal
 * @author Mehmet Kağan İlbak
 * @version 2021/05/30
 */
public abstract class Task {
    String description;
    Date startDate;
    Date endDate;

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