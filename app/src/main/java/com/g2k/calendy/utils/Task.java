package com.g2k.calendy.utils;

import java.util.Date;

/**
 * Task class for Event, Reminder, Goal
 * @author Mehmet Kağan İlbak
 * @version 2021/05/04
 */
public class Task implements Comparable {
    protected String taskType;
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

    public String toString() {
        return this.getTaskID();
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskType() {
        return taskType;
    }

    /**
     * Compare by startDate
     * @param o
     * @return
     */
    @Override
    public int compareTo(Object o) {
        Task that = (Task) o;

        return this.startDate.compareTo(that.startDate);
    }
}