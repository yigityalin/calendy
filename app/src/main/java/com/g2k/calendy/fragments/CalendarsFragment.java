package com.g2k.calendy.fragments;

import android.content.Intent;
import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.g2k.calendy.AddCalendarActivity;
import com.g2k.calendy.CalendarAdapter;
import com.g2k.calendy.EditProfileActivity;
import com.g2k.calendy.R;
import com.g2k.calendy.activities.AddNewEventActivity;
import com.g2k.calendy.utils.Calendar;
import com.g2k.calendy.utils.CurrentUser;
import com.g2k.calendy.utils.CurrentUserCalendars;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalendarsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalendarsFragment extends Fragment
        implements CalendarAdapter.CalendarClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    RecyclerView calendarsView;
    CalendarAdapter calendarAdapter;
    ArrayList<Calendar> dataSet;
    private AppCompatButton navigateToProfileButton;

    private String mParam1;
    private String mParam2;

    public CalendarsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalendarsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalendarsFragment newInstance(String param1, String param2) {
        CalendarsFragment fragment = new CalendarsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        dataSet = CurrentUserCalendars.getCalendars();

        calendarAdapter = new CalendarAdapter(dataSet, createBackgroundsArray());
        calendarAdapter.setCalendarClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendars, container, false);

        view.findViewById(R.id.calendars_add_calendar).setOnClickListener(listener);
        view.findViewById(R.id.calendars_top_bar_profile_button).setOnClickListener(listener);
        view.findViewById(R.id.calendars_top_bar_settings_button).setOnClickListener(listener);

        navigateToProfileButton = view.findViewById(R.id.calendars_top_bar_profile_button);
        navigateToProfileButton.setText(CurrentUser.getInstance().getName());
        navigateToProfileButton.setTextColor(Color.WHITE);


        calendarsView = view.findViewById(R.id.calendars_view);
        calendarsView.setAdapter(calendarAdapter);
        calendarsView.setLayoutManager(new GridLayoutManager(
                this.getContext(),
                2,
                RecyclerView.VERTICAL,
                false
        ));

        return view;
    }

    private final View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.calendars_add_calendar:
                    startActivity(new Intent(getContext(), AddCalendarActivity.class));
                    break;
                case R.id.calendars_top_bar_profile_button:
                    startActivity(new Intent(getContext(), EditProfileActivity.class));
                    break;
                case R.id.calendars_top_bar_settings_button:
                    break;
            }
        }
    };

    /**
     * the method which is called when a recycler view element is clicked
     *
     * @param view     is the current view
     * @param position is the position of the element in the recycler view
     */
    @Override
    public void onCalendarClick(View view, int position)
    {
        //TODO: fix activity class
        startActivity(new Intent(view.getContext(), AddNewEventActivity.class));
    }

    private Drawable[] createBackgroundsArray()
    {
        Resources resources = getResources();

        Drawable backgroundBlack = ResourcesCompat.getDrawable(
                resources,
                R.drawable.calendars_list_background_black,
                null
        );

        Drawable backgroundBlue = ResourcesCompat.getDrawable(
                resources,
                R.drawable.calendars_list_background_blue,
                null
        );

        Drawable backgroundRed = ResourcesCompat.getDrawable(
                resources,
                R.drawable.calendars_list_background_red,
                null
        );

        Drawable backgroundPurple = ResourcesCompat.getDrawable(
                resources,
                R.drawable.calendars_list_background_purple,
                null
        );

        Drawable backgroundCoral = ResourcesCompat.getDrawable(
                resources,
                R.drawable.calendars_list_background_coral,
                null
        );

        Drawable backgroundGreen = ResourcesCompat.getDrawable(
                resources,
                R.drawable.calendars_list_background_green,
                null
        );

        Drawable[] backgrounds = {
                backgroundBlack,
                backgroundBlue,
                backgroundCoral,
                backgroundGreen,
                backgroundRed,
                backgroundPurple
        };

        return backgrounds;
    }
}