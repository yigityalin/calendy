package com.g2k.calendy.utils;

public class Goal extends Task {

    public Goal(String description, String startDate, String endDate) {
        super.taskType = "Goal";
        super.taskID = DatabaseHelper.getUniqueId();
        super.description = description;
        super.startDate = startDate;
        super.endDate = endDate;
    }

}
