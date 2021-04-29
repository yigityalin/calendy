package com.g2k.calendy.utils;

import java.util.Date;

public class CurrentUser extends User {
    private static boolean isCreated;
    private static CurrentUser user;

    private CurrentUser(String uid, String email, String name, String university, String city, boolean isVisible, Date birthDate) {
        super(uid, email, name, university, city, isVisible, birthDate);
        isCreated = true;
    }

    public static CurrentUser getInstance() {
        return user;
    }

    public static void initialize(String uid,
                                  String email,
                                  String name,
                                  String university,
                                  String city,
                                  boolean isVisible,
                                  Date birthDate) throws Exception {
        if (!isCreated) {
            user = new CurrentUser(uid, email, name, university, city, isVisible, birthDate);
        } else {
            throw new Exception("Current user is already initialized!");
        }
    }
}
