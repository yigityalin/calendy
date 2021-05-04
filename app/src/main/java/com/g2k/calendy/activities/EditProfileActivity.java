package com.g2k.calendy.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.g2k.calendy.R;
import com.g2k.calendy.utils.CurrentUser;
import com.g2k.calendy.utils.DatabaseHelper;

/**
 * Editing profile and updating the database
 * @author Mehmet Kağan İlbak
 * @version 2021/05/03
 */
public class EditProfileActivity extends AppCompatActivity {
    private EditText name;
    private EditText city;
    private EditText university;
    private TextView email;
    private TextView birthDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        name = findViewById(R.id.edit_profile_name);
        city = findViewById(R.id.edit_profile_city);
        university = findViewById(R.id.edit_profile_university);
        email = findViewById(R.id.edit_profile_email);
        birthDate = findViewById(R.id.edit_profile_birth_date);

        name.setText(CurrentUser.getInstance().getName());
        city.setText(CurrentUser.getInstance().getCity());
        university.setText(CurrentUser.getInstance().getUniversity());
        email.setText(CurrentUser.getInstance().getEmail());
        birthDate.setText(CurrentUser.getInstance().getBirthDate());

        findViewById(R.id.edit_profile_save_button).setOnClickListener(listener);
    }

    private final View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.edit_profile_save_button:
                    CurrentUser.getInstance().setName(name.getText().toString());
                    CurrentUser.getInstance().setCity(city.getText().toString());
                    CurrentUser.getInstance().setUniversity(university.getText().toString());
                    DatabaseHelper.updateCurrentUser();
                    finish();
                    break;
            }
        }
    };
}