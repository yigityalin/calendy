package com.g2k.calendy.activities;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.g2k.calendy.R;
import com.g2k.calendy.fragments.CalendarsFragment;
import com.g2k.calendy.fragments.HomeFragment;
import com.g2k.calendy.fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * Main activity for fragments and bottom navigation menu
 * @author Mehmet Kağan İlbak
 * @version 2021/04/23
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.nav_bottom);
        bottomNav.setOnNavigationItemSelectedListener(navListener); // add listener
        bottomNav.setSelectedItemId(R.id.nav_home); // set default bottom nav selected to home

        // Set default fragment to home
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();
    }

    // Listener for bottom navigation
    private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_calendars:
                            selectedFragment = new CalendarsFragment();
                            break;
                        case R.id.nav_search:
                            selectedFragment = new SearchFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };
}