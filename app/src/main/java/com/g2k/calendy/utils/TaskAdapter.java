package com.g2k.calendy.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.g2k.calendy.R;

import java.util.ArrayList;

/**
 * Adapter Class for RecyclerView in HomeFragment
 * @author Mehmet Kağan İlbak
 * @author Mustafa Cem Gülümser
 * @author Yiğit Yalın
 * @version 2021/05/04
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private final String EVENT_STRING = "Event";
    private final int EVENT_VIEW_TYPE = 0;

    private final String GOAL_STRING = "Goal";
    private final int GOAL_VIEW_TYPE = 1;

    private final String REMINDER_STRING = "Reminder";
    private final int REMINDER_VIEW_TYPE = 2;

    private Context context;
    private ArrayList<Task> tasks;

    public TaskAdapter(Context context, ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        int type;

        switch (tasks.get(position).getClass().getSimpleName()) {
            case EVENT_STRING:
                type = EVENT_VIEW_TYPE;
                break;

            case GOAL_STRING:
                type = GOAL_VIEW_TYPE;
                break;

            case REMINDER_STRING:
                type = REMINDER_VIEW_TYPE;
                break;

            default:
                type = -1;
        }

        return type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;

        switch (viewType) {
            case EVENT_VIEW_TYPE:
                view = inflater.inflate(R.layout.home_frag_rv_row_event, parent, false);
                break;

            case GOAL_VIEW_TYPE:
                view = inflater.inflate(R.layout.home_frag_rv_row_goal, parent, false);
                break;

            case REMINDER_VIEW_TYPE:
                view = inflater.inflate(R.layout.home_frag_rv_row_reminder, parent, false);
                break;

            default:
                view = inflater.inflate(R.layout.home_frag_rv_row_default, parent, false);
        }

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String startTime = tasks.get(position).getStartDate().substring(8, 12);
        String endTime = tasks.get(position).getEndDate().substring(8, 12);
        String formattedStartTime = startTime.substring(0, 2) + ":" + startTime.substring(2, 4);
        String formattedEndTime = endTime.substring(0, 2) + ":" + endTime.substring(2, 4);

        holder.taskDescription.setText(tasks.get(position).getDescription());
        holder.taskType.setText(tasks.get(position).getClass().getSimpleName());
        holder.startTime.setText(formattedStartTime);
        holder.endTime.setText(formattedEndTime);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView taskDescription;
        private final TextView taskType;
        private final TextView startTime;
        private final TextView endTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskDescription = itemView.findViewById(R.id.home_frag_rv_row_description);
            taskType = itemView.findViewById(R.id.home_frag_rv_row_type);
            startTime = itemView.findViewById(R.id.home_frag_rv_row_start_time);
            endTime = itemView.findViewById(R.id.home_frag_rv_row_end_time);
        }
    }
}
