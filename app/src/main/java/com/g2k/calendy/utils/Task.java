package com.g2k.calendy.utils;

import java.util.Date;

/**
 * Abstract Task class for Event, Reminder, Goal
 * @author Mehmet Kağan İlbak
 * @version 2021/05/30
 */
public abstract class Task {
    String description;
    String startDate;
    String endDate;

    public String getName() {
        return description;
    }

    public void setName(String name) {
        this.description = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

}