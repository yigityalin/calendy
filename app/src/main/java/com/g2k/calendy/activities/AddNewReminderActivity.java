package com.g2k.calendy.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.g2k.calendy.R;
import com.g2k.calendy.utils.DatePickerButton;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_reminder);

        description = findViewById(R.id.add_reminder_description);
        datePickerButton = new DatePickerButton(this, findViewById(R.id.add_reminder_date_picker_button));
        timePickerButton = new TimePickerButton(this, findViewById(R.id.add_reminder_time_picker_button));
        findViewById(R.id.add_new_reminder_button).setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.add_new_reminder_button) {
                // TODO add stuff to calender update database etc
                finish();
            }
        }
    };
}