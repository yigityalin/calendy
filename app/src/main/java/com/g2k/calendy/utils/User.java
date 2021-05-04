package com.g2k.calendy.utils;

import java.util.ArrayList;
import java.util.Date;

/**
 * User class to hold calendars, uid, email, name, university, city, visibility, birthDate
 * @author Mehmet Kağan İlbak
 * @version 2021/05/04
 */
public class User {
    private ArrayList<Calendar> calendars;
    private String uid;
    private String email;
    private String name;
    private String university;
    private String city;
    private boolean isVisible;
    private String birthDate;

    public User(String uid, String email, String name, String university, String city, boolean isVisible, String birthDate) {
        calendars = new ArrayList<>();
        this.uid = uid;
        this.email = email;
        this.name = name;
        this.university = university;
        this.city = city;
        this.isVisible = isVisible;
        this.birthDate = birthDate;
    }

    public ArrayList<Calendar> getCalendars() {
        return calendars;
    }

    public void setCalendars(ArrayList<Calendar> calendars) {
        this.calendars = calendars;
    }

    public User() {
        // Necessary for CurrentUser
    }

    public String getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
