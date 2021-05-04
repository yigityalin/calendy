package com.g2k.calendy.utils;

/**
 * Event that takes place in one day in selected time frame
 * @author Mehmet Kağan İlbak
 * @version 2021/05/04
 */
public class Event extends Task {

    public Event(String description, String startDate, String endDate) {
        super.taskType = "Event";
        super.taskID = DatabaseHelper.getUniqueId();
        super.description = description;
        super.startDate = startDate;
        super.endDate = endDate;
    }
}
