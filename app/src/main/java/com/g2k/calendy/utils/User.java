package com.g2k.calendy.utils;

import java.util.Date;

public class User {
    String uid;
    String email;
    String name;
    String university;
    String city;
    boolean isVisible;
    Date birthDate;

    public User(String uid, String email, String name, String university, String city, boolean isVisible, Date birthDate) {
        this.uid = uid;
        this.email = email;
        this.name = name;
        this.university = university;
        this.city = city;
        this.isVisible = isVisible;
        this.birthDate = birthDate;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
