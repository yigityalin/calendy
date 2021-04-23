package com.g2k.calendy.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.g2k.calendy.AddNewEventActivity;
import com.g2k.calendy.AddNewReminderActivity;
import com.g2k.calendy.R;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

/**
 * Home fragment for displaying events & creating new ones
 * using floating action button (fab)
 * @author Mehmet Kağan İlbak
 * @version 2021/04/23
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        FloatingActionButton fabGoal = view.findViewById(R.id.fab_goal);
        FloatingActionButton fabTask = view.findViewById(R.id.fab_reminder);
        FloatingActionButton fabEvent = view.findViewById(R.id.fab_event);

        fabGoal.setOnClickListener(fabListener);
        fabTask.setOnClickListener(fabListener);
        fabEvent.setOnClickListener(fabListener);

        return view;
    }

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