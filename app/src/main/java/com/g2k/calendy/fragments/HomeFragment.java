package com.g2k.calendy.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;

import com.g2k.calendy.activities.AddNewEventActivity;
import com.g2k.calendy.activities.AddNewGoalActivity;
import com.g2k.calendy.activities.AddNewReminderActivity;
import com.g2k.calendy.R;
import com.g2k.calendy.utils.CurrentDate;
import com.g2k.calendy.utils.DailyEventsView;
import com.getbase.floatingactionbutton.FloatingActionButton;

/**
 * Home fragment for displaying events & creating new ones
 * using floating action button (fab)
 * @author Mehmet Kağan İlbak, Yiğit Yalın
 * @version 2021/04/26
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private ViewSwitcher viewSwitcher;
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        viewSwitcher = view.findViewById(R.id.view_switcher_home);
        View dayView = view.findViewById(R.id.day_view_home);
        View monthView = view.findViewById(R.id.month_view_home);
        Button switchToMonthViewButton = view.findViewById(R.id.switch_to_month_view);
        Button switchToDayViewButton = view.findViewById(R.id.switch_to_day_view);

        FloatingActionButton fabGoal = view.findViewById(R.id.fab_goal);
        FloatingActionButton fabTask = view.findViewById(R.id.fab_reminder);
        FloatingActionButton fabEvent = view.findViewById(R.id.fab_event);

        switchToMonthViewButton.setText(CurrentDate.getCurrentDate());
        switchToDayViewButton.setText(CurrentDate.getCurrentDate());

        switchToMonthViewButton.setOnClickListener(viewSwitchButtonListener);
        switchToDayViewButton.setOnClickListener(viewSwitchButtonListener);

        fabGoal.setOnClickListener(fabListener);
        fabTask.setOnClickListener(fabListener);
        fabEvent.setOnClickListener(fabListener);

        initialize(getContext(), view);

        return view;
    }

    private final View.OnClickListener viewSwitchButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.switch_to_month_view:
                    viewSwitcher.showNext();
                    break;
                case R.id.switch_to_day_view:
                    viewSwitcher.showPrevious();
                    break;
            }
        }
    };


    // TODO fab intents
    private final View.OnClickListener fabListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fab_event:
                    startActivity(new Intent(getContext(), AddNewEventActivity.class));
                    break;
                case R.id.fab_reminder:
                    startActivity(new Intent(getContext(), AddNewReminderActivity.class));
                    break;
                case R.id.fab_goal:
                    startActivity(new Intent(getContext(), AddNewGoalActivity.class));
                    break;
            }
        }
    };

    /**
     * TODO: this is a test code. check again when database connection is done
     * initializes the home view by adding the events
     * @param context is the content to initialize
     * @param view is the parent view
     */
    @SuppressLint("SetTextI18n")
    private void initialize(Context context, View view)
    {
        LinearLayout switchMonthViewContent = view.findViewById(R.id.month_scroll_view_content);
        LinearLayout switchDayViewContent = view.findViewById(R.id.day_scroll_view_content);

        DailyEventsView dailyEventsView = new DailyEventsView(
                context,
                "Test Header",
                "00:00",
                "Test Description"
        );

        DailyEventsView dailyEventsView2 = new DailyEventsView(
                context,
                "Test Header 2",
                "00:01",
                "Test Description 2"
        );

        DailyEventsView dailyEventsView3 = new DailyEventsView(
                context,
                "Test Header 3",
                "00:02",
                "Test Description 3"
        );

        DailyEventsView dailyEventsView4 = new DailyEventsView(
                context,
                "Test Header 4",
                "00:03",
                "Test Description 4"
        );

        DailyEventsView dailyEventsView5 = new DailyEventsView(
                context,
                "Test Header 5",
                "00:04",
                "Test Description 5"
        );

        DailyEventsView dailyEventsView6 = new DailyEventsView(
                context,
                "Test Header 6",
                "00:05",
                "Test Description 6"
        );

        DailyEventsView dailyEventsView7 = new DailyEventsView(
                context,
                "Test Header 7",
                "00:06",
                "Test Description 7"
        );

        DailyEventsView dailyEventsView8 = new DailyEventsView(
                context,
                "Test Header 8",
                "00:07",
                "Test Description 8"
        );

        DailyEventsView dailyEventsView9 = new DailyEventsView(
                context,
                "Test Header 9",
                "00:08",
                "Test Description 9"
        );

        DailyEventsView dailyEventsView01 = new DailyEventsView(
                context,
                "Test Header",
                "00:00",
                "Test Description"
        );

        DailyEventsView dailyEventsView02 = new DailyEventsView(
                context,
                "Test Header 2",
                "00:01",
                "Test Description 2"
        );

        DailyEventsView dailyEventsView03 = new DailyEventsView(
                context,
                "Test Header 3",
                "00:02",
                "Test Description 3"
        );

        DailyEventsView dailyEventsView04 = new DailyEventsView(
                context,
                "Test Header 4",
                "00:03",
                "Test Description 4"
        );

        DailyEventsView dailyEventsView05 = new DailyEventsView(
                context,
                "Test Header 5",
                "00:04",
                "Test Description 5"
        );

        DailyEventsView dailyEventsView06 = new DailyEventsView(
                context,
                "Test Header 6",
                "00:05",
                "Test Description 6"
        );

        DailyEventsView dailyEventsView07 = new DailyEventsView(
                context,
                "Test Header 7",
                "00:06",
                "Test Description 7"
        );

        DailyEventsView dailyEventsView08 = new DailyEventsView(
                context,
                "Test Header 8",
                "00:07",
                "Test Description 8"
        );

        DailyEventsView dailyEventsView09 = new DailyEventsView(
                context,
                "Test Header 9",
                "00:08",
                "Test Description 9"
        );

        switchMonthViewContent.addView(dailyEventsView);
        switchMonthViewContent.addView(dailyEventsView2);
        switchMonthViewContent.addView(dailyEventsView3);
        switchMonthViewContent.addView(dailyEventsView4);
        switchMonthViewContent.addView(dailyEventsView5);
        switchMonthViewContent.addView(dailyEventsView6);
        switchMonthViewContent.addView(dailyEventsView7);
        switchMonthViewContent.addView(dailyEventsView8);
        switchMonthViewContent.addView(dailyEventsView9);
        switchDayViewContent.addView(dailyEventsView01);
        switchDayViewContent.addView(dailyEventsView02);
        switchDayViewContent.addView(dailyEventsView03);
        switchDayViewContent.addView(dailyEventsView04);
        switchDayViewContent.addView(dailyEventsView05);
        switchDayViewContent.addView(dailyEventsView06);
        switchDayViewContent.addView(dailyEventsView07);
        switchDayViewContent.addView(dailyEventsView08);
        switchDayViewContent.addView(dailyEventsView09);
    }
}