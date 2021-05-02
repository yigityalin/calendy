package com.g2k.calendy.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.g2k.calendy.R;
import com.g2k.calendy.utils.CurrentUser;
import com.g2k.calendy.utils.DatabaseHelper;
import com.g2k.calendy.utils.User;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Start Activity, if user is logged in via firebase, MainActivity starts
 * @author Mehmet Kağan İlbak
 * @version 2021/05/01
 */
public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        findViewById(R.id.start_login_button).setOnClickListener(this);
        findViewById(R.id.start_signup_button).setOnClickListener(this);

        if (DatabaseHelper.isLoggedIn()) {
            CurrentUser.initialize();
            DatabaseHelper.initCurrentUser();

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_login_button:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.start_signup_button:
                startActivity(new Intent(this, SignupActivity.class));
                break;
        }
    }
}