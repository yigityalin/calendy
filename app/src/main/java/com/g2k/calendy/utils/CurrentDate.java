package com.g2k.calendy.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;

/**
 * helper class to get current time
 * @author Yiğit Yalın
 * @version 2021/04/26
 */
public class CurrentDate {
    private static String[] months = {
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
    };

    /**
     * gets the current date and parses it into Month + Day
     * @return a string containing the current month and day
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getCurrentDate()
    {
        LocalDate now;
        String formattedDate;

        now = LocalDate.now();
        formattedDate = now.getMonth() + " " + now.getDayOfMonth();

        return formattedDate;
    }
}
