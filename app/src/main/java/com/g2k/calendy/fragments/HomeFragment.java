package com.g2k.calendy.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.g2k.calendy.AddNewEventActivity;
import com.g2k.calendy.AddNewReminderActivity;
import com.g2k.calendy.R;
import com.g2k.calendy.utils.CurrentDate;
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

        switchToMonthViewButton.setOnClickListener(viewSwitchButtonListener);
        switchToDayViewButton.setOnClickListener(viewSwitchButtonListener);

        fabGoal.setOnClickListener(fabListener);
        fabTask.setOnClickListener(fabListener);
        fabEvent.setOnClickListener(fabListener);

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
            Intent intent;

            switch (v.getId()) {
                case R.id.fab_event:
                    intent = new Intent(getContext(), AddNewEventActivity.class);
                    startActivity(intent);
                    break;
                case R.id.fab_reminder:
                    intent = new Intent(getContext(), AddNewReminderActivity.class);
                    startActivity(intent);
                    break;
                case R.id.fab_goal:
                    Toast.makeText(getContext(), "TODO GOAL ACTIVITY", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };


}