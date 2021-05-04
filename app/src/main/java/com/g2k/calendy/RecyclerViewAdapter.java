package com.g2k.calendy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Editing the recycle view
 * @author Mustafa Cem Gülümser
 * @version 2021/05/04
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.xViewHolder>{

    String calendarName;
    String taskDesc[];
    String startTime[];
    String endTime[];
    Context context;

    public RecyclerViewAdapter(Context ct, String theCalendarName, String theTaskDesc[], String theStartTime[], String theEndTime[] ) {
        context = ct;
        calendarName = theCalendarName;
        taskDesc = theTaskDesc;
        startTime = theStartTime;
        endTime = theEndTime;
    }

    @NonNull
    @Override
    public xViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from( context);
        View view = inflater.inflate( R.layout.rv_row, parent, false);
        return new xViewHolder( view);
    }

    @Override
    public void onBindViewHolder(@NonNull xViewHolder holder, int position) {
        holder.text1.setText( taskDesc[position]);
        holder.text2.setText( calendarName);
        holder.text3.setText( startTime[position]);
        holder.text4.setText( endTime[position]);
    }

    @Override
    public int getItemCount() {
        return taskDesc.length;
    }

    public class xViewHolder extends RecyclerView.ViewHolder{

        TextView text1;
        TextView text2;
        TextView text3;
        TextView text4;

        public xViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById( R.id.taskD);
            text2 = itemView.findViewById( R.id.calName);
            text3 = itemView.findViewById( R.id.startT);
            text4 = itemView.findViewById( R.id.endT);
        }
    }

}
