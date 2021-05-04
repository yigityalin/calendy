package com.g2k.calendy.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.g2k.calendy.R;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context context;
    private List<User> mUsers;
    private FirebaseUser firebaseUser;
    private UserClickListener userClickListener;

    public UserAdapter(Context context, List<User> mUsers) {
        this.context = context;
        this.mUsers = mUsers;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        User user = mUsers.get(position);
        holder.fullName.setText(user.getName());

        holder.box.setOnClickListener(holder);
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView fullName;
        private ConstraintLayout box;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fullName = itemView.findViewById(R.id.search_full_name);
            box = itemView.findViewById(R.id.search_item);
        }

        @Override
        public void onClick(View v) {
            if (userClickListener != null) {
                userClickListener.onUserClick(v, getAdapterPosition());
            }
        }
    }

    /**
     * parent activity implements this to set what will happen on click
     */
    public interface UserClickListener {
        void onUserClick(View view, int position);
    }

    /**
     * sets the click listener for recycler view elements
     *
     * @param userClickListener is the click listener to be added
     */
    public void setUserClickListener(UserAdapter.UserClickListener userClickListener) {
        this.userClickListener = userClickListener;
    }

}
