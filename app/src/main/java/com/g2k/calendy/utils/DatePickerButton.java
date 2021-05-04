package com.g2k.calendy.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Helper for date picker button
 * @author Mehmet Kağan İlbak
 * @version 2021/05/04
 */
public class DatePickerButton implements View.OnClickListener {
    private Button button;
    private Context context;
    private TaskAdapter homeFragmentAdapter;

    private DatePickerDialog datePickerDialog;
    private int year;
    private int month;
    private int day;

    public DatePickerButton(Context context, Button button) {
        this.context = context;
        this.button = button;

        button.setText(getTodaysDate()); // getTodaysDate() also initializes year, month, day
        initDatePicker();
        button.setOnClickListener(this);
    }

    /**
     * Get selected year
     * @return (int) year
     */
    public int getYear() {
        return year;
    }

    /**
     * Get selected month (indexted to 0)
     * @return (int) month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Get selected day
     * @return (int) day
     */
    public int getDay() {
        return day;
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

    /**
     * Get todays date formatted as JAN 01 2021
     * @return
     */
    public String getTodaysDate() {
        Calendar calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        return getMonthFormat(month) + " " + getFormattedDay() + " " + year;
    }

    /**
     * Initialize DatePicker button
     */
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

    /**
     * Make date String formatted as JAN 01 2021
     * @param dayOfMonth (int) day
     * @param month (int) month (0 indexed)
     * @param year (int) year
     * @return (String) MMM DD YYYY
     */
    private String makeDateString(int dayOfMonth, int month, int year) {
        return getMonthFormat(month) + " " + dayOfMonth + " " + year;
    }

    /**
     * Convert month index to text
     * @param month (int) month index
     * @return (String) formatted month (such as JAN)
     */
    private String getMonthFormat(int month) {
        String months = "JANFEBMARAPRMAYJUNJULAUGSEPOCTNOVDEC";

        if (month <= 12) {
            return months.substring(month * 3, month * 3 + 3);
        } else {
            return "INV"; // Invalid, should never return this
        }
    }

    /**
     * onClick method that will show dialog
     * @param v
     */
    @Override
    public void onClick(View v) {
        datePickerDialog.show();
    }

    /**
     * Get button to modify it
     * @return (Button) button
     */
    public Button getButton() {
        return button;
    }
}
