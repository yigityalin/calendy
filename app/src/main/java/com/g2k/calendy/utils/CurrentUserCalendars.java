package com.g2k.calendy.utils;

import java.util.ArrayList;
import java.util.Collections;
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

    /**
     * Initialize calendars, must be run outside of Firebase methods
     */
    public static void initialize() {
        calendars = new ArrayList<>();
    }

    /**
     * Get calendar with given name
     * @param name (String) Calendar name
     * @return (Calendar) with given name
     */
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
    public static HashMap<String, ArrayList<Task>> getTasksOnDate(String date) {
        HashMap<String, ArrayList<Task>> returnCalendars = new HashMap<>();

        for (Calendar c : calendars) {
            ArrayList<Task> tasks = new ArrayList<>(c.getTasks().values()); // Get tasks from HashMap
            ArrayList<Task> taskToAdd = new ArrayList<>();

            for (Task t : tasks) {
                if ("Reminder".equals(t.getTaskType())) {
                    String reminderDate = t.getStartDate().substring(0, 8);
                    if (date.compareTo(reminderDate) == 0) {
                        taskToAdd.add(t);
                    }
                } else if ("Event".equals(t.getTaskType())) {
                    String eventDate = t.getStartDate().substring(0, 8);
                    if (date.compareTo(eventDate) == 0) {
                        taskToAdd.add(t);
                    }
                } else if ("Goal".equals(t.getTaskType())) {
                    String goalStartDate = t.getStartDate().substring(0, 8);
                    String goalEndDate = t.getEndDate().substring(0, 8);

                    if ((date.compareTo(goalStartDate) >= 0 && date.compareTo(goalEndDate) <= 0)) {
                        taskToAdd.add(t);
                    }
                } else {
                    System.out.println("Task doesn't have any type!");
                }
            }

            returnCalendars.put(c.getName(), taskToAdd);
        }

        return returnCalendars;
    }

    /**
     * Get today's tasks
     * @return (ArrayList<Task>) today's tasks
     */
    public static ArrayList<Task> getTodaysTasks() {
        return combineIntoOneList(getTasksOnDate(getFormattedTodaysDate()));
    }

    /**
     * Helper for getting todays tasks sorted by start date
     * @param tasksMap
     * @return
     */
    private static ArrayList<Task> combineIntoOneList(HashMap<String, ArrayList<Task>> tasksMap) {
        ArrayList<String> keys;
        ArrayList<Task> tasks;

        tasks = new ArrayList<>();
        keys = new ArrayList<>(tasksMap.keySet());

        for (String k : keys) {
            for (Task t : tasksMap.get(k)) {
                if ("Event".equals(t.getTaskType())) {
                    tasks.add(new Event(t.getDescription(), t.getStartDate(), t.getEndDate()));
                } else if ("Reminder".equals(t.getTaskType())) {
                    tasks.add(new Reminder(t.getDescription(), t.getStartDate()));
                } else if ("Goal".equals(t.getTaskType())) {
                    tasks.add(new Goal(t.getDescription(), t.getStartDate(), t.getEndDate()));
                }
            }
        }

        Collections.sort(tasks); // Sort by startDate

        return tasks;
    }

    /**
     * Getting formatted month
     * @param month (int) month index
     * @return (String) MM
     */
    private static String getFormattedMonth(int month) {
        if (month + 1 < 10) {
            return "0" + (month + 1);
        } else {
            return "" + (month + 1);
        }
    }

    /**
     * Turn 3 to 03
     * @return (String) day added with 0
     */
    private static String getFormattedDay(int day) {
        if (day < 10) {
            return "0" + day;
        } else {
            return "" + day;
        }
    }

    /**
     * Get date formatted as YYYYMMDD
     * @return (String) YYYYMMDD
     */
    private static String getFormattedTodaysDate() {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        int day = calendar.get(java.util.Calendar.DAY_OF_MONTH);
        int month = calendar.get(java.util.Calendar.MONTH);
        int year = calendar.get(java.util.Calendar.YEAR);

        return "" + year + getFormattedMonth(month) + getFormattedDay(day);
    }
}
