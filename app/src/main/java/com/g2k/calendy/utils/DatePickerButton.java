package com.g2k.calendy.utils;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.g2k.calendy.R;

import java.util.Calendar;

/**
 * Helper for date picker button
 * @author Mehmet Kağan İlbak
 * @version 2021/04/23
 */
public class DatePickerButton implements View.OnClickListener {
    private final Button button;
    private final Context context;

    private DatePickerDialog datePickerDialog;
    private int year;
    private int month;
    private int day;

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public DatePickerButton(Context context, Button button) {
        this.context = context;
        this.button = button;

        button.setText(getTodaysDate()); // getTodaysDate() also initializes year, month, day
        initDatePicker();
        button.setOnClickListener(this);
    }

    private String getTodaysDate() {
        Calendar calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        return getMonthFormat(month) + " " + day + " " + year;
    }

    private void initDatePicker() {
        Calendar calendar;
        int year;
        int month;
        int day;
        int style;

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                button.setText(makeDateString(selectedDay, selectedMonth, selectedYear));
            }
        };

        calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        datePickerDialog = new DatePickerDialog(context, dateSetListener, year, month, day);
    }

    private String makeDateString(int dayOfMonth, int month, int year) {
        return getMonthFormat(month) + " " + dayOfMonth + " " + year;
    }

    private String getMonthFormat(int month) {
        String months = "JANFEBMARAPRMAYJUNJULAUGSEPOCTNOVDEC";

        if (month <= 12) {
            return months.substring(month * 3, month * 3 + 3);
        } else {
            return "INV"; // Invalid, should never return this
        }
    }

    @Override
    public void onClick(View v) {
        datePickerDialog.show();
    }
}
