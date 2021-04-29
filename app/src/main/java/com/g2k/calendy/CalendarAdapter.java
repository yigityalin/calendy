package com.g2k.calendy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Adapter class for recycleview in Calendars fragment
 * @author Yiğit Yalın
 * @version 2021/04/29
 */
public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder>
{
    private ArrayList<String> calendarData;

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView calendarNameTextField;
        private final ImageButton deleteCalendarButton;
        private final ImageButton bottomButton;

        public ViewHolder(View view)
        {
            super(view);

            calendarNameTextField = view.findViewById(R.id.calendar_name);
            deleteCalendarButton = view.findViewById(R.id.delete_calendar_button);
            bottomButton = view.findViewById(R.id.bottom_button);
        }

        public TextView getCalendarNameTextField()
        {
            return calendarNameTextField;
        }

        public ImageButton getDeleteCalendarButton()
        {
            return deleteCalendarButton;
        }

        public ImageButton getBottomButton()
        {
            return bottomButton;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public CalendarAdapter(ArrayList<String> dataSet)
    {
        calendarData = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.calendars_list_element, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position)
    {
        viewHolder.getCalendarNameTextField().setText(calendarData.get(position));
    }

    @Override
    public int getItemCount() {
        return calendarData.size();
    }

    /**
     * gets the data at click position
     * @param id is the click position
     * @return the data at click position
     */
    public String getItem(int id)
    {
        return calendarData.get(id);
    }
}
