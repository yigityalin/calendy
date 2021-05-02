package com.g2k.calendy.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.g2k.calendy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

/**
 * ForgotPassword Activity
 * @author Mustafa Cem Gülümser
 * @version 2021/05/01
 */

public class ForgotPasswordActivity extends AppCompatActivity {

    private Button mResetButton;
    private EditText mEmailText;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        mAuth = FirebaseAuth.getInstance();
        mResetButton = findViewById( R.id.fPassword_button);
        mEmailText = findViewById( R.id.fP_email);

        mResetButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v) {
                String userEmail = mEmailText.getText().toString();

                if(TextUtils.isEmpty( userEmail)) {
                    Toast.makeText( ForgotPasswordActivity.this, "Please enter your email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    mAuth.sendPasswordResetEmail( userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()) {
                                Toast.makeText( ForgotPasswordActivity.this, "Check your email account to reset your password", Toast.LENGTH_LONG).show();
                            }
                            else {
                                String message = task.getException().getMessage();
                                Toast.makeText( ForgotPasswordActivity.this, "Error, " + message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    public void fPgoBack(View view) {
        finish();
    }
}