package com.g2k.calendy.utils;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Locale;

/**
 * Helper for time picker button
 * @author Mehmet Kağan İlbak
 * @version 2021/04/23
 */
public class TimePickerButton implements View.OnClickListener {
    private final Context context;
    private final Button button;

    private TimePickerDialog timePickerDialog;
    private int hour;
    private int minute;

    public TimePickerButton(Context context, Button button) {
        this.button = button;
        this.context = context;

        button.setText(getCurrentTime()); // getCurrentTime() also initializes hour and minute
        initTimePicker();
        button.setOnClickListener(this);
    }

    /**
     * Get hour
     * @return (int) hour
     */
    public int getHour() {
        return hour;
    }

    /**
     * Get minute
     * @return (int) minute
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Get formatted time of now
     * @return (String) "hh mm"
     */
    private String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        return getFormattedHour() + ":" + getFormattedMinute();
    }

    /**
     * Get formatted time as HHMM
     * @return (String) HHMM
     */
    public String getFormattedTime() {
        return getFormattedHour() + getFormattedMinute();
    }

    /**
     * Format hour
     * @return (String) hh
     */
    private String getFormattedHour() {
        if (hour < 10) {
            return "0" + hour;
        } else {
            return "" + hour;
        }
    }

    /**
     * Format minute
     * @return (String) mm
     */
    private String getFormattedMinute() {
        if (minute < 10) {
            return "0" + minute;
        } else {
            return "" + minute;
        }
    }

    /**
     * Initialize TimePicker button
     */
    private void initTimePicker() {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;

                button.setText(getFormattedHour() + ":" + getFormattedMinute());
            }
        };

        timePickerDialog = new TimePickerDialog(context,
                timeSetListener,
                hour,
                minute,
                true);
    }

    /**
     * Shows TimePicker button on click
     * @param v (View)
     */
    @Override
    public void onClick(View v) {
        timePickerDialog.show();
    }
}
