package com.g2k.calendy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.g2k.calendy.utils.Task;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Adapter Class for RecyclerView in HomeFragment
 * @author Mehmet Kağan İlbak
 * @author Mustafa Cem Gülümser
 * @version 2021/05/04
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private final Context context;
    private final HashMap<String, Task> tasks;

    public TaskAdapter(Context context, HashMap<String, Task> tasks) {
        this.context = context;
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String startTime = tasks.get(position).getStartDate().substring(8, 12);
        String endTime = tasks.get(position).getEndDate().substring(8, 12);
        String formattedStartTime = startTime.substring(2) + ":" + startTime.substring(2, 4);
        String formattedEndTime = endTime.substring(2) + ":" + endTime.substring(2, 4);

        holder.taskDescription.setText(tasks.get(position).getDescription());
        holder.calendarName.setText(tasks.get(position).getTaskType());
        holder.startTime.setText(formattedStartTime);
        holder.endTime.setText(formattedEndTime);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView taskDescription;
        private final TextView calendarName;
        private final TextView startTime;
        private final TextView endTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskDescription = itemView.findViewById(R.id.taskD);
            calendarName = itemView.findViewById(R.id.calName);
            startTime = itemView.findViewById(R.id.startT);
            endTime = itemView.findViewById(R.id.endT);
        }
    }

}
