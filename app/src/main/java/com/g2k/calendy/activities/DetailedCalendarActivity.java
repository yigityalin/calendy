package com.g2k.calendy.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.g2k.calendy.R;
import com.g2k.calendy.utils.CurrentUserCalendars;
import com.g2k.calendy.utils.DatabaseHelper;
import com.g2k.calendy.utils.Event;
import com.g2k.calendy.utils.Goal;
import com.g2k.calendy.utils.Reminder;
import com.g2k.calendy.utils.Task;
import com.g2k.calendy.utils.TaskAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class DetailedCalendarActivity extends AppCompatActivity {
    private String calendarName;
    private int calendarIndex;
    private TextView calendarNameTextView;
    private RecyclerView recyclerView;
    private ArrayList<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_calendar);

        calendarName = getIntent().getExtras().getString("calendarName");
        calendarIndex = Integer.parseInt(getIntent().getExtras().getString("calendarIndex"));

        calendarNameTextView = findViewById(R.id.detailed_calendar_name);
        recyclerView = findViewById(R.id.detailed_calendar_recycler);

        calendarNameTextView.setText(calendarName);

        tasks = convertToArrayList(CurrentUserCalendars.getCalendars().get(calendarIndex).getTasks());

        TaskAdapter taskAdapter = new TaskAdapter(DetailedCalendarActivity.this, tasks);
        recyclerView.setAdapter(taskAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(DetailedCalendarActivity.this));
    }

    private static ArrayList<Task> convertToArrayList(HashMap<String, Task> map) {
        ArrayList<String> keys = new ArrayList<>(map.keySet());
        ArrayList<Task> tasks = new ArrayList<>();

        for (String k : keys) {
            Task t = map.get(k);

            if ("Event".equals(t.getTaskType())) {
                tasks.add(new Event(t.getDescription(), t.getStartDate(), t.getEndDate()));
            } else if ("Reminder".equals(t.getTaskType())) {
                tasks.add(new Reminder(t.getDescription(), t.getStartDate()));
            } else if ("Goal".equals(t.getTaskType())) {
                tasks.add(new Goal(t.getDescription(), t.getStartDate(), t.getEndDate()));
            }
        }

        Collections.sort(tasks);

        return tasks;
    }

    public void deleteCalendar(View view) {
        DatabaseReference mDatabase;

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("calendars").child(DatabaseHelper.getCurrentUserUID()).child(calendarName).removeValue();
        finish();
    }
}