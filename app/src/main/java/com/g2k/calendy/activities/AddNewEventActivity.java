package com.g2k.calendy.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.g2k.calendy.R;
import com.g2k.calendy.utils.DatePickerButton;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_event);

        description = findViewById(R.id.add_event_description);
        dateButton = new DatePickerButton(this, findViewById(R.id.add_event_date_picker_button));
        startTimeButton = new TimePickerButton(this, findViewById(R.id.add_event_start_time_picker_button));
        endTimeButton = new TimePickerButton(this, findViewById(R.id.add_event_end_time_picker_button));

        findViewById(R.id.add_new_event_button).setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.add_new_event_button:
                    // TODO add stuff to calender update database etc
                    finish();
                    break;
            }
        }
    };
}