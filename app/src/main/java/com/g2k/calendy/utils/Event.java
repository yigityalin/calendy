package com.g2k.calendy.utils;

public class Event extends Task {

    public Event(String description, String startDate, String endDate) {
        super.taskID = DatabaseHelper.getUniqueId();
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
