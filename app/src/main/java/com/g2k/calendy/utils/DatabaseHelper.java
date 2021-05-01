package com.g2k.calendy.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.g2k.calendy.R;
import com.g2k.calendy.activities.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

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
}