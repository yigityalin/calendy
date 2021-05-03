package com.g2k.calendy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.xViewHolder>{

    String data1[];
    Context context;

    public RecyclerViewAdapter(Context ct, String s1[]) {
        context = ct;
        data1 = s1;
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
        holder.text1.setText( data1[position]);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class xViewHolder extends RecyclerView.ViewHolder{

        TextView text1;

        public xViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById( R.id.rv_text);
        }
    }

}
