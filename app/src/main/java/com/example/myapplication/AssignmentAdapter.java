package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.AssignmentViewHolder> {
    private List<Assignment> assignmentList;
    public AssignmentAdapter(List<Assignment> assignmentList) {
        this.assignmentList = assignmentList;}

    @NonNull
    @Override
    public AssignmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_assignment_row, parent, false);
        return new AssignmentViewHolder(view); }

    @Override
    public void onBindViewHolder(@NonNull AssignmentViewHolder holder, int position) {
        Assignment assignment = assignmentList.get(position);
        holder.title.setText("Title: " + assignment.getTitle());
        holder.due.setText("Due: " + assignment.getDueDate());
        holder.description.setText("Description: " + assignment.getDescription()); }

    @Override
    public int getItemCount() {
        return assignmentList.size();
    }

    static class AssignmentViewHolder extends RecyclerView.ViewHolder {
        TextView title, due, description;
        public AssignmentViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.assignmentTitle);
            due = itemView.findViewById(R.id.assignmentDue);
            description = itemView.findViewById(R.id.assignmentDescription);
        }
    }
}
