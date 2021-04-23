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
    private final static int BUTTON_STYLE = AlertDialog.THEME_DEVICE_DEFAULT_LIGHT;
    private final Context context;
    private final Button button;

    private TimePickerDialog timePickerDialog;
    private int hour;
    private int minute;

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public TimePickerButton(Context context, Button button) {
        this.button = button;
        this.context = context;

        button.setText(getCurrentTime()); // getCurrentTime() also initializes hour and minute
        initTimePicker();
        button.setOnClickListener(this);
    }

    private String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        return hour + ":" + minute;
    }

    private void initTimePicker() {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;

                button.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
            }
        };

        timePickerDialog = new TimePickerDialog(context,
                BUTTON_STYLE,
                timeSetListener,
                hour,
                minute,
                true);
    }

    @Override
    public void onClick(View v) {
        timePickerDialog.show();
    }
}
