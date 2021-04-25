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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getCurrentDate()
    {
        LocalDate now;
        String formattedDate;

        now = LocalDate.now();
        formattedDate = String.valueOf(now.getMonth()) + " " + String.valueOf(now.getDayOfMonth());

        return formattedDate;
    }
}
