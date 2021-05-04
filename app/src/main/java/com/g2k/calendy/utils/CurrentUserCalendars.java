package com.g2k.calendy.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

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

    /**
     * From the calendars, selects Tasks that occur on given date
     * @param date (String) date to show Tasks on with format YYYYMMDD
     * @return (HashMap<String, ArrayList<Task>>) HashMap with Calendar names and ArrayList<Task> of the date
     */
    public static HashMap<String, Task> getTasksOnDate(String date) {
        HashMap<String, Task> returnCalendars = new HashMap<>();

        for (Calendar c : calendars) {
            ArrayList<Task> tasks = new ArrayList<>(c.getTasks().values()); // Get tasks from HashMap

            for (Task t : tasks) {
                if ("Reminder".equals(t.getTaskType())) {
                    String reminderDate = t.getStartDate().substring(0, 8);
                    if (date.compareTo(reminderDate) == 0) {
                        returnCalendars.put(c.getName(), t);
                    }
                } else if ("Event".equals(t.getTaskType())) {
                    String eventDate = t.getStartDate().substring(0, 8);
                    if (date.compareTo(eventDate) == 0) {
                        returnCalendars.put(c.getName(), t);
                    }
                } else if ("Goal".equals(t.getTaskType())) {
                    String goalStartDate = t.getStartDate().substring(0, 8);
                    String goalEndDate = t.getEndDate().substring(0, 8);

                    if ((date.compareTo(goalStartDate) >= 0 && date.compareTo(goalEndDate) <= 0)) {
                        returnCalendars.put(c.getName(), t);
                    }
                } else {
                    System.out.println("Task doesn't have any type!");
                }
            }
        }

        return returnCalendars;
    }
}
