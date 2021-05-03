package com.g2k.calendy.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.g2k.calendy.R;
import com.g2k.calendy.activities.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Database helper to change and get
 *     String uid
 *     String email
 *     String name
 *     String university
 *     String city
 *     boolean isVisible
 *     Date birthDate
 *     from Firebase
 * @author Mehmet Kağan İlbak
 * @version 2021/05/03
 */
public class DatabaseHelper {
    private static FirebaseAuth mAuth;
    private static DatabaseReference mDatabase;

    static {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public static boolean isLoggedIn() {
        return mAuth.getCurrentUser() != null;
    }

    public static void createNewCalendar(Calendar calendar) {
        mDatabase.child("calendars").child(calendar.getOwnerUID()).child(calendar.getName()).setValue(calendar);
    }

    public static FirebaseUser getFirebaseUser() {
        return mAuth.getCurrentUser();
    }

    /**
     * This will initialize CurrentUser singleton and will update it in realtime
     */
    public static void initCurrentUser() {
        String uid = mAuth.getCurrentUser().getUid();

        mDatabase.child("users").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);

                CurrentUser.initialize(user); // initialize fields
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void updateCurrentUser() {
        mDatabase.child("users").child(CurrentUser.getInstance().getUid()).setValue(CurrentUser.getInstance());
    }

    public static void initCurrentUserCalendars() {
        String uid = mAuth.getCurrentUser().getUid();

        mDatabase.child("calendars").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                CurrentUserCalendars.getCalendars().clear(); // Remove all old calendars

                // Add new ones from the database
                for (DataSnapshot eachCalendar : snapshot.getChildren()) {
                    Calendar calendar = eachCalendar.getValue(Calendar.class);
                    CurrentUserCalendars.getCalendars().add(calendar);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void initCurrentUserCalendarsTasks() {
        String uid = mAuth.getCurrentUser().getUid();

        for (Calendar c : CurrentUserCalendars.getCalendars()) {
            mDatabase.child("calendars").child(uid).child(c.getName()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot s : snapshot.getChildren()) {
                        c.addTask(s.getValue(com.g2k.calendy.utils.Task.class));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    public static void updateCurrentUserCalendars() {
        for (Calendar calendar : CurrentUserCalendars.getCalendars()) {
            mDatabase.child("calendars").child(getCurrentUserUID()).child(calendar.getName()).setValue(calendar);
        }
    }

    /**
     * Read tasks of the given calendar and return AL
     * Also updates AL when database changes
     * @param calendar (String) Calendar name to read tasks from
     * @return (ArrayList<Task>) AL of tasks
     */
    public static ArrayList<Task> readCalendarTasks(String calendar) {
        ArrayList<Task> tasks = new ArrayList<>();

        mDatabase.child("calendars").child(getCurrentUserUID())
                .child(calendar)
                .child("tasks")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot task : snapshot.getChildren()) {
                            String taskID = task.getKey();
                            System.out.println("Log here" + taskID);
                            tasks.add(task.child(taskID).getValue(Task.class));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        return tasks;
    }

    /**
     * Write new task to given calendar in database with unique id
     * @param calendarName (String) name of the calendar to write
     * @param task (Task) task to write
     */
    public static void writeNewTask(String calendarName, Task task) {
        mDatabase.child("calendars")
                .child(getCurrentUserUID())
                .child(calendarName)
                .child("tasks")
                .child(task.getTaskID())
                .setValue(task);
    }

    public static String getCurrentUserUID() {
        return mAuth.getCurrentUser().getUid();
    }

    /**
     * Get unique id from system time
     * @return (String) Current system date and time down to milliseconds
     */
    public static String getUniqueId() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return formatter.format(new Date());
    }
}