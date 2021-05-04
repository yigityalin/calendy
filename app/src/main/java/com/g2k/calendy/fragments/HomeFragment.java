package com.g2k.calendy.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewSwitcher;

import com.g2k.calendy.utils.TaskAdapter;
import com.g2k.calendy.activities.AddNewEventActivity;
import com.g2k.calendy.activities.AddNewGoalActivity;
import com.g2k.calendy.activities.AddNewReminderActivity;
import com.g2k.calendy.R;
import com.g2k.calendy.utils.CurrentUserCalendars;
import com.g2k.calendy.utils.DatePickerButton;
import com.getbase.floatingactionbutton.FloatingActionButton;

/**
 * Home fragment for displaying events & creating new ones
 * using floating action button (fab)
 *
 * @author Mehmet Kağan İlbak, Yiğit Yalın, Mustafa Cem Gülümser
 * @version 2021/05/03
 */
public class HomeFragment extends Fragment {
    private DatePickerButton datePickerButton;
    private RecyclerView tasksView;

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

        datePickerButton = new DatePickerButton(getContext(), view.findViewById(R.id.home_pick_date_button));

        FloatingActionButton fabGoal = view.findViewById(R.id.fab_goal);
        FloatingActionButton fabTask = view.findViewById(R.id.fab_reminder);
        FloatingActionButton fabEvent = view.findViewById(R.id.fab_event);

        fabGoal.setOnClickListener(fabListener);
        fabTask.setOnClickListener(fabListener);
        fabEvent.setOnClickListener(fabListener);

        tasksView = view.findViewById(R.id.recyclerView);

        try {
            datePickerButton.getButton().setText(datePickerButton.getTodaysDate());

            TaskAdapter taskAdapter = new TaskAdapter(this.getContext(),
                    CurrentUserCalendars.getTodaysTasks());
            tasksView.setAdapter(taskAdapter);
            tasksView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        } catch (Exception e) {
            datePickerButton.getButton().setText("Please Reload");
        }

        return view;
    }

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
}