package com.g2k.calendy.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.g2k.calendy.R;
import com.g2k.calendy.utils.Calendar;
import com.g2k.calendy.utils.CurrentUserCalendars;
import com.g2k.calendy.utils.DatabaseHelper;
import com.g2k.calendy.utils.DatePickerButton;
import com.g2k.calendy.utils.Event;
import com.g2k.calendy.utils.TimePickerButton;

/**
 * Event activity for fab menu
 * @author Mehmet Kağan İlbak
 * @version 2021/05/01
 */
public class AddNewEventActivity extends AppCompatActivity {
    private DatePickerButton dateButton;
    private TimePickerButton startTimeButton;
    private TimePickerButton endTimeButton;
    private EditText description;
    private ArrayAdapter<Calendar> spinnerAdapter;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_event);

        spinner = findViewById(R.id.add_event_calendar_spinner);
        description = findViewById(R.id.add_event_description);
        dateButton = new DatePickerButton(this, findViewById(R.id.add_event_date_picker_button));
        startTimeButton = new TimePickerButton(this, findViewById(R.id.add_event_start_time_picker_button));
        endTimeButton = new TimePickerButton(this, findViewById(R.id.add_event_end_time_picker_button));

        findViewById(R.id.add_new_event_button).setOnClickListener(listener);

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
            if (v.getId() == R.id.add_new_event_button) {
                Calendar selectedCalendar;
                Event event = new Event(description.getText().toString(),
                        dateButton.getFormattedDate() + startTimeButton.getFormattedTime(),
                        dateButton.getFormattedDate() + endTimeButton.getFormattedTime());

                selectedCalendar = (Calendar) spinner.getSelectedItem();

                selectedCalendar.addTask(event);
                DatabaseHelper.updateCurrentUserCalendars();

                finish();
            }
        }
    };
}