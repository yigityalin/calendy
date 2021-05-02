package com.g2k.calendy.utils;

/**
 * CurrentUser singleton to manage current logged in user
 * @author Mehmet Kağan İlbak
 * @version 2021/05/03
 */
public class CurrentUser {
    private static boolean isCreated = false;
    private static User user;

    public static User getInstance() {
        return user;
    }

    public static void initialize(String uid,
                                  String email,
                                  String name,
                                  String university,
                                  String city,
                                  boolean isVisible,
                                  String birthDate) {
        if (!isCreated) {
            user = new User(uid, email, name, university, city, isVisible, birthDate);
            isCreated = true;
        }
    }

    /**
     * Method to set fields of singleton because Firebase methods are async
     * @param userP
     */
    public static void initialize(User userP) {
        if (!isCreated) {
            user = userP;
            isCreated = true;
        }
    }

    /**
     * Need to run this outside of onDataChange() to retrieve User data
     */
    public static void initialize() {
        if (!isCreated) {
            user = new User();
        }
    }
}
