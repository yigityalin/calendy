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
import com.g2k.calendy.utils.Goal;
import com.g2k.calendy.utils.TimePickerButton;

/**
 * Adding new goal
 * @author Mehmet Kağan İlbak
 * @version 2021/05/01
 */
public class AddNewGoalActivity extends AppCompatActivity {
    private ArrayAdapter<Calendar> spinnerAdapter;
    private DatePickerButton startDateButton;
    private DatePickerButton endDateButton;
    private TimePickerButton startTimeButton;
    private TimePickerButton endTimeButton;
    private EditText description;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_goal);
        System.out.println("Hey!" + CurrentUserCalendars.getTasksOnDate("20210504"));
        spinner = findViewById(R.id.add_goal_calendar_spinner);
        description = findViewById(R.id.add_goal_description);
        startDateButton = new DatePickerButton(getApplicationContext(),
                findViewById(R.id.add_goal_start_date_picker_button));
        endDateButton = new DatePickerButton(getApplicationContext(),
                findViewById(R.id.add_goal_end_date_picker_button));
        startTimeButton = new TimePickerButton(getApplicationContext(),
                findViewById(R.id.add_goal_start_time_picker_button));
        endTimeButton = new TimePickerButton(getApplicationContext(),
                findViewById(R.id.add_goal_end_time_picker_button));

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
            if (v.getId() == R.id.add_new_goal_button) {
                Calendar selectedCalendar;
                Goal goal = new Goal(description.getText().toString(),
                        startDateButton.getFormattedDate() + startTimeButton.getFormattedTime(),
                        endDateButton.getFormattedDate() + endTimeButton.getFormattedTime());

                selectedCalendar = CurrentUserCalendars.getCalendar(((Calendar) spinner.getSelectedItem()).getName());

                selectedCalendar.addTask(goal);
                DatabaseHelper.updateCurrentUserCalendars();

                finish();
            }
        }
    };
}