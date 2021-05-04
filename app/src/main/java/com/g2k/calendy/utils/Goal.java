package com.g2k.calendy.utils;

/**
 * Goal that takes place in multiple days in selected time frame
 * @author Mehmet Kağan İlbak
 * @version 2021/05/04
 */
public class Goal extends Task {

    public Goal(String description, String startDate, String endDate) {
        super.taskType = "Goal";
        super.taskID = DatabaseHelper.getUniqueId();
        super.description = description;
        super.startDate = startDate;
        super.endDate = endDate;
    }

}
