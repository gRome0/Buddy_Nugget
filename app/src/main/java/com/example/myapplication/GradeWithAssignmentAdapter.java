package com.example.myapplication;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GradeWithAssignmentAdapter extends RecyclerView.Adapter<GradeWithAssignmentAdapter.ViewHolder> {
    private List<GradeWithAssignment> gradeList;
    public GradeWithAssignmentAdapter(List<GradeWithAssignment> gradeList) { this.gradeList = gradeList; }

    @Override
    public void onBindViewHolder(@NonNull GradeWithAssignmentAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @NonNull
    @Override
    public GradeWithAssignmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }
}
