package com.g2k.calendy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.g2k.calendy.utils.TimePickerButton;

public class AddNewGoalActivity extends AppCompatActivity {
    private TimePickerButton startTimeButton;
    private TimePickerButton endTimeButton;
    private EditText description;
    private EditText count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_goal);

        description = findViewById(R.id.add_goal_description);
        count = findViewById(R.id.add_goal_count);
        startTimeButton = new TimePickerButton(getApplicationContext(),
                findViewById(R.id.add_goal_start_time_picker_button));
        endTimeButton = new TimePickerButton(getApplicationContext(),
                findViewById(R.id.add_goal_end_time_picker_button));
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.add_new_goal_button) {
                // TODO add stuff to calender update database etc
                finish();
            }
        }
    };
}