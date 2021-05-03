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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

    public static void updateCurrentUser() {
        mDatabase.child("users").child(CurrentUser.getInstance().getUid()).setValue(CurrentUser.getInstance());
    }

    public static boolean isLoggedIn() {
        return mAuth.getCurrentUser() != null;
    }

    public static void createNewCalendar(Calendar calendar) {
        calendar.addTask(new Reminder());
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
                System.out.println("smallog" + CurrentUserCalendars.getCalendars().get(0).getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static String getCurrentUserUID() {
        return mAuth.getCurrentUser().getUid();
    }
}