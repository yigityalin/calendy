package com.g2k.calendy.utils;

import java.util.Date;

public class Event extends Task {
    private String url; // l8r

    public Event(String description, Date startDate, Date endDate) {
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
