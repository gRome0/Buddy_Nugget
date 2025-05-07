package com.example.myapplication;


/*
RecyclerView adapter that shows assignment title and grade together
Author: Joseph
 */

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class GradeWithAssignmentAdapter extends RecyclerView.Adapter<GradeWithAssignmentAdapter.ViewHolder> {
    private List<GradeWithAssignment> gradeList;
    public GradeWithAssignmentAdapter(List<GradeWithAssignment> gradeList) { this.gradeList = gradeList; }

    @NonNull
    @Override
    public GradeWithAssignmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()) .inflate(android.R.layout.simple_list_item_2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GradeWithAssignmentAdapter.ViewHolder holder, int position) {
        GradeWithAssignment gwa = gradeList.get(position);
        String title = (gwa.assignment != null) ? gwa.assignment.getTitle() : "(Deleted Assignment)";
        holder.assignmentTitle.setText("Assignment: " + title); holder.gradeScore.setText ("Score: " + gwa.grade.getScore()); }

    @Override
    public int getItemCount() { return gradeList.size();}
    static class ViewHolder extends RecyclerView.ViewHolder { TextView assignmentTitle, gradeScore;
        ViewHolder(View itemView) { super(itemView); assignmentTitle = itemView.findViewById(android.R.id.text1);
            gradeScore = itemView.findViewById(android.R.id.text2); } }
}