package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NormalUserAdapter extends RecyclerView.Adapter<NormalUserAdapter.NormalUserViewHolder> {
    private List<User> userList;
    public NormalUserAdapter(List<User> userList) {
        this.userList = userList;
    }
    @NonNull

    @Override
    public NormalUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_row, parent, false);
        return new NormalUserViewHolder(view);}

    @Override
    public void onBindViewHolder(@NonNull NormalUserViewHolder holder, int position)
    {User user = userList.get(position); holder.usernameView.setText("Username: " + user.getUsername()); holder.studentIdView
            ("Student ID: " + (user.getStudentId() != null ? user.getStudentId() : "N/A")); }

    @Override
    public int getItemCount() {
        return userList.size();
    }
    public static class NormalUserViewHolder extends RecyclerView.ViewHolder
    { TextView usernameView, studentIdView; public NormalUserViewHolder(@NonNull View itemView)
    { super(itemView); usernameView = itemView.findViewById(R.id.usernameText); studentIdView = itemView.findViewById(R.id.student)
    }
    }
} //
