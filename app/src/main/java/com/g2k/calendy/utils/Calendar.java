package com.g2k.calendy.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Calendar to hold ArrayList of Tasks
 * Each calendar has one owner that can add or delete tasks
 * Subscribers can only access the tasks
 * UID is generated by Firebase upon registration
 * @author Mehmet Kağan İlbak
 * @version 2021/05/03
 */
public class Calendar {
    private HashMap<String, Task> tasks;
    private ArrayList<String> subscriberUIDs;
    private String ownerUID;
    private String name;
    private boolean isPublic;

    {
        tasks = new HashMap<>();
    }

    public Calendar(String name, boolean isPublic, String ownerUID) {
        subscriberUIDs = new ArrayList<>();
        this.name = name;
        this.ownerUID = ownerUID;
        this.isPublic = isPublic;
    }

    public Calendar() {
        // Required for database
    }

    public void addTask(Task task) {
        tasks.put(task.getTaskID(), task);
    }

    public String getOwnerUID() {
        return ownerUID;
    }

    public String getName() {
        return name;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public HashMap<String, Task> getTasks() {
        return tasks;
    }

    public void setTasks(HashMap<String, Task> tasks) {
        this.tasks = tasks;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    @Override
    public String toString() {
        return name;
    }
}