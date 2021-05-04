package com.g2k.calendy.utils;

public class Reminder extends Task {

    public Reminder(String description, String date) {
        super.taskType = "Reminder";
        super.taskID = DatabaseHelper.getUniqueId();
        super.description = description;
        super.startDate = date;
        super.endDate = date;
    }
}
