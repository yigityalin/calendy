package com.g2k.calendy.utils;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Calendar to hold ArrayList of Tasks
 * Each calendar has one owner that can add or delete tasks
 * Subscribers can only access the tasks
 * UID is generated by Firebase upon registration
 * @author Mehmet Kağan İlbak
 * @version 2021/04/30
 */
public class Calendar {
    private ArrayList<Task> tasks;
    private ArrayList<String> subscriberUIDs;
    private String ownerUID;
    private String name;

    public Calendar(String name, String ownerUID) {
        tasks = new ArrayList<>();
        this.name = name;
        this.ownerUID = ownerUID;
    }

    public void addTask(Task task) {
        tasks.add(task);
//        Collections.sort(tasks);
    }

    public Task getNextTask() throws Exception {
        if (tasks.size() > 0)
            return tasks.get(0);
        else
            throw new Exception("Empty Calendar");
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public void removeCurrentTask() {
        tasks.remove(0);
    }

    public void removeTask(int index) {
        tasks.remove(index);
    }

    public String getName() {
        return name;
    }
}