package com.g2k.calendy.utils;

import java.util.ArrayList;

/**
 * Singleton-like class for current calendars of CurrentUser
 */
public class CurrentUserCalendars {
    private static ArrayList<Calendar> calendars;

    public static ArrayList<Calendar> getCalendars() {
        return calendars;
    }

    public static void initialize() {
        calendars = new ArrayList<>();
    }

    public static Calendar getCalendar(String name) {
        for (Calendar c : calendars) {
            if (c.getName().equals(name)) {
                return c;
            }
        }

        return null;
    }
}
