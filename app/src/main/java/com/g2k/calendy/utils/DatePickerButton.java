package com.g2k.calendy.utils;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.g2k.calendy.R;
import com.g2k.calendy.TaskAdapter;

import java.util.Calendar;

/**
 * Helper for date picker button
 * @author Mehmet Kağan İlbak
 * @version 2021/04/23
 */
public class DatePickerButton implements View.OnClickListener {
    private Button button;
    private Context context;
    private TaskAdapter homeFragmentAdapter;

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

    /**
     * Get date formatted as YYYYMMDD
     * @return (String) YYYYMMDD
     */
    public String getFormattedDate() {
        return "" + year + getFormattedMonth() + getFormattedDay();
    }

    /**
     * Turn 3 to 03
     * @return (String) month added with 0
     */
    public String getFormattedMonth() {
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
    public String getFormattedDay() {
        if (day < 10) {
            return "0" + day;
        } else {
            return "" + day;
        }
    }

    public String getTodaysDate() {
        Calendar calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        return getMonthFormat(month) + " " + getFormattedDay() + " " + year;
    }

    private void initDatePicker() {
        Calendar calendar;
        int cYear;
        int cMonth;
        int cDay;

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                year = selectedYear;
                month = selectedMonth;
                day = selectedDay;
                button.setText(makeDateString(selectedDay, selectedMonth, selectedYear));
            }
        };

        calendar = Calendar.getInstance();
        cDay = calendar.get(Calendar.DAY_OF_MONTH);
        cMonth = calendar.get(Calendar.MONTH);
        cYear = calendar.get(Calendar.YEAR);

        datePickerDialog = new DatePickerDialog(context, dateSetListener, cYear, cMonth, cDay);
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

    public Button getButton() {
        return button;
    }
}
