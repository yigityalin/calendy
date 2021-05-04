package com.g2k.calendy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.g2k.calendy.activities.MainActivity;
import com.g2k.calendy.activities.StartActivity;
import com.g2k.calendy.utils.CurrentUser;
import com.g2k.calendy.utils.DatabaseHelper;

public class SettingsActivity extends AppCompatActivity {
    private SwitchCompat privateSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        privateSwitch = findViewById(R.id.settings_private_switch);
        privateSwitch.setChecked(!CurrentUser.getInstance().isVisible());

        privateSwitch.setOnClickListener(listener);
        findViewById(R.id.settings_log_out_button).setOnClickListener(listener);
    }

    private final View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.settings_log_out_button:
                    DatabaseHelper.logOut();

                    Intent intent = new Intent(SettingsActivity.this, StartActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    break;
                case R.id.settings_private_switch:
                    CurrentUser.getInstance().setVisible(!privateSwitch.isChecked());
                    DatabaseHelper.updateCurrentUser();
                    break;
            }
        }
    };
}