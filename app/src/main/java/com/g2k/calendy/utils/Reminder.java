package com.g2k.calendy.utils;

/**
 * Reminder that takes place in one day in selected time
 * @author Mehmet Kağan İlbak
 * @version 2021/05/04
 */
public class Reminder extends Task {

    public Reminder(String description, String date) {
        super.taskType = "Reminder";
        super.taskID = DatabaseHelper.getUniqueId();
        super.description = description;
        super.startDate = date;
        super.endDate = date;
    }
}
