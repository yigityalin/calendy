package com.g2k.calendy.utils;

import java.util.Date;

/**
 * Abstract Task class for Event, Reminder, Goal
 * @author Mehmet Kağan İlbak
 * @version 2021/05/30
 */
public class Task {
    protected String taskID;
    protected String description;
    protected String startDate;
    protected String endDate;

    public Task() {

    }
    public String getTaskID() {
        return taskID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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