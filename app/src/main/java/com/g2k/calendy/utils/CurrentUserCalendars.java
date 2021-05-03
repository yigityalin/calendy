package com.g2k.calendy.utils;

import java.util.ArrayList;

/**
 * Singleton class for current calendars of CurrentUser
 */
public class CurrentUserCalendars {
    private static ArrayList<Calendar> calendars;

    public static ArrayList<Calendar> getCalendars() {
        return calendars;
    }

    public static void initialize() {
        calendars = new ArrayList<>();
    }
}
