package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GradeWithAssignmentAdapter extends RecyclerView.Adapter<GradeWithAssignmentAdapter.ViewHolder> {
    private List<GradeWithAssignment> gradeList;
    public GradeWithAssignmentAdapter(List<GradeWithAssignment> gradeList) { this.gradeList + gradeList; }

    @NonNull
    @Override
    public GradeWithAssignmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

    }
}
