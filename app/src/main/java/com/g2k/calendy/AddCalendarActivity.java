package com.g2k.calendy;

import androidx.appcompat.app.AppCompatActivity;

import com.g2k.calendy.utils.Calendar;
import com.g2k.calendy.utils.DatabaseHelper;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * Activity to add new calendar, also adds it to database
 * @author Mehmet Kağan İlbak
 * @version 2021/05/03
 */
public class AddCalendarActivity extends AppCompatActivity implements View.OnClickListener {
    private boolean isPublic;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_calendar);

        findViewById(R.id.add_calendar_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_calendar_button:
                isPublic = ((CheckBox) findViewById(R.id.add_calendar_public_checkbox)).isChecked();
                name = ((EditText) findViewById(R.id.add_calendar_name)).getText().toString();
                DatabaseHelper.createNewCalendar(new Calendar(name, isPublic, DatabaseHelper.getCurrentUserUID()));
                finish();
        }
    }
}