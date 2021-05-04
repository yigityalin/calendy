package com.g2k.calendy.utils;

public class Event extends Task {

    public Event(String description, String startDate, String endDate) {
        super.taskType = "Event";
        super.taskID = DatabaseHelper.getUniqueId();
        super.description = description;
        super.startDate = startDate;
        super.endDate = endDate;
    }
}
