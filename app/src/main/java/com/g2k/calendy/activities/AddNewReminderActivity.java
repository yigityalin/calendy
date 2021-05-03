package com.g2k.calendy.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.g2k.calendy.R;
import com.g2k.calendy.utils.Calendar;
import com.g2k.calendy.utils.CurrentUserCalendars;
import com.g2k.calendy.utils.DatabaseHelper;
import com.g2k.calendy.utils.DatePickerButton;
import com.g2k.calendy.utils.Reminder;
import com.g2k.calendy.utils.TimePickerButton;

/**
 * Reminder activity for fab menu
 * @author Mehmet Kağan İlbak
 * @version 2021.04.23
 */
public class AddNewReminderActivity extends AppCompatActivity {
    private DatePickerButton datePickerButton;
    private TimePickerButton timePickerButton;
    private EditText description;
    private ArrayAdapter<Calendar> spinnerAdapter;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_reminder);

        spinner = findViewById(R.id.add_reminder_calendar_spinner);
        description = findViewById(R.id.add_reminder_description);
        datePickerButton = new DatePickerButton(this, findViewById(R.id.add_reminder_date_picker_button));
        timePickerButton = new TimePickerButton(this, findViewById(R.id.add_reminder_time_picker_button));
        findViewById(R.id.add_new_reminder_button).setOnClickListener(listener);

        // Setting spinner to CurrentUserCalendars
        spinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                CurrentUserCalendars.getCalendars());
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.add_new_reminder_button) {
                Calendar selectedCalendar;
                Reminder reminder = new Reminder(description.getText().toString(),
                        datePickerButton.getFormattedDate() +
                        timePickerButton.getFormattedTime());

                selectedCalendar = (Calendar) spinner.getSelectedItem();

                selectedCalendar.addTask(reminder);
                DatabaseHelper.updateCurrentUserCalendars();
                finish();
            }
        }
    };
}