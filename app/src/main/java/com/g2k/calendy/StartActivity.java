package com.g2k.calendy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void logInAction(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity( intent);
    }

    public void signUpAction(View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity( intent);
    }
}