package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GradeWithAssignmentAdapter extends RecyclerView.Adapter<GradeWithAssignmentAdapter.ViewHolder> {
    private List<GradeWithAssignment> gradeList;
    public GradeWithAssignmentAdapter(List<GradeWithAssignment> gradeList) { this.gradeList = gradeList; }

    @Override
    public void onBindViewHolder(@NonNull GradeWithAssignmentAdapter.ViewHolder holder, int position) {
        GradeWithAssignment gwa = gradeList.get(position);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @NonNull
    @Override
    public GradeWithAssignmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()) .inflate(android.R.layout.simple_list_item_2,parent, false);
        return new ViewHolder(view);
    }
}
