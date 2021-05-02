package com.g2k.calendy.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.g2k.calendy.R;
import com.g2k.calendy.utils.CurrentUser;
import com.g2k.calendy.utils.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

/**
 * Signup Activity
 * @author Mehmet Kağan İlbak & Mustafa Cem Gülümser
 * @version 2021/05/01
 */
public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "SignupActivity";

    private EditText mEmail, mPassword, mName, mPasswordCheck;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mName = findViewById( R.id.userName);
        mEmail = findViewById( R.id.userEmail);
        mPassword = findViewById( R.id.userPassword);
        mPasswordCheck = findViewById( R.id.userPasswordCheck);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.signup_button).setOnClickListener(this);
        findViewById(R.id.signup_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signup_button:
                register();
                break;
            case R.id.signup_back:
                finish();
                break;
        }
    }

    private void register() {
        Log.d(TAG, "signUp");

        String name = ((EditText) findViewById(R.id.userName)).getText().toString();
        String email = ((EditText) findViewById(R.id.userEmail)).getText().toString();
        String password = ((EditText) findViewById(R.id.userPassword)).getText().toString();
        String confirmPassword = ((EditText) findViewById(R.id.userPasswordCheck)).getText().toString();

        if(TextUtils.isEmpty(email)) {
            mEmail.setError( "Email cannot be blank!");
            return;
        }
        if(password.length() < 6) {
            mPassword.setError("Password must be at least 6 characters long!");
            return;
        }
        if(name.length() <= 2) {
            mName.setError("Invalid name!");
            return;
        }
        if( !confirmPassword.equals( password)) {
            mPasswordCheck.setError("Passwords do not match!");
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    try {
                        onAuthSuccess(task.getResult().getUser());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Sign Up Failed",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void onAuthSuccess(FirebaseUser firebaseUser) throws Exception {
        String name = ((EditText) findViewById(R.id.userName)).getText().toString();
        String university = ((EditText) findViewById(R.id.userUniversity)).getText().toString();
        String city = ((EditText) findViewById(R.id.userCity)).getText().toString();
        Date dateOfBirth = new Date(); // TODO fix later
        User user = new User(firebaseUser.getUid(), firebaseUser.getEmail(), name, university, city, true, dateOfBirth);

        CurrentUser.initialize(user);
        writeNewUser(user.getUid(), user);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void writeNewUser(String uid, User user) {
        mDatabase.child("users").child(uid).setValue(user);
    }
}