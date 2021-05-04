package com.g2k.calendy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.g2k.calendy.utils.Calendar;
import com.g2k.calendy.utils.Task;
import com.g2k.calendy.utils.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserProfileActivity extends AppCompatActivity
        implements CalendarAdapter.CalendarClickListener {
    private String uid;
    private String name;
    private TextView fullName;
    private ArrayList<Calendar> calendars;
    private RecyclerView recyclerView;
    private CalendarAdapter adapter;
    private boolean dataReadCompleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        dataReadCompleted = false;

        uid = getIntent().getExtras().getString("userId");
        name = getIntent().getExtras().getString("name");

        fullName = findViewById(R.id.user_profile_full_name);
        fullName.setText(name);

        calendars = new ArrayList<>();
        readCalendars();

        recyclerView = findViewById(R.id.user_profile_recycler);
        adapter = new CalendarAdapter(calendars, createBackgroundsArray());
        adapter.setCalendarClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    private void readCalendars() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        ref.child("calendars").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot s : snapshot.getChildren()) {
                    Calendar calendar = s.getValue(Calendar.class);
                    if (calendar.isPublic()) {
                        calendars.add(calendar);
                    }
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private ArrayList<Task> readTasksFromCalendar(Calendar calendar) {
        return new ArrayList<Task>(calendar.getTasks().values());
    }

    private Drawable[] createBackgroundsArray()
    {
        Resources resources = getResources();

        Drawable backgroundBlack = ResourcesCompat.getDrawable(
                resources,
                R.drawable.calendars_list_background_black,
                null
        );

        Drawable backgroundBlue = ResourcesCompat.getDrawable(
                resources,
                R.drawable.calendars_list_background_blue,
                null
        );

        Drawable backgroundRed = ResourcesCompat.getDrawable(
                resources,
                R.drawable.calendars_list_background_red,
                null
        );

        Drawable backgroundPurple = ResourcesCompat.getDrawable(
                resources,
                R.drawable.calendars_list_background_purple,
                null
        );

        Drawable backgroundCoral = ResourcesCompat.getDrawable(
                resources,
                R.drawable.calendars_list_background_coral,
                null
        );

        Drawable backgroundGreen = ResourcesCompat.getDrawable(
                resources,
                R.drawable.calendars_list_background_green,
                null
        );

        Drawable[] backgrounds = {
                backgroundBlack,
                backgroundBlue,
                backgroundCoral,
                backgroundGreen,
                backgroundRed,
                backgroundPurple
        };

        return backgrounds;
    }

    @Override
    public void onCalendarClick(View view, int position) {
        Intent intent = new Intent(view.getContext(), DetailedCalendarActivity.class);
        intent.putExtra("calendarName", calendars.get(position).getName());
        intent.putExtra("calendarIndex", "" + position);
        startActivity(intent);
    }
}