package com.example.myapplication;
/*
shows normal their assignment
Author: @Imane
 */
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewAssignmentsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AssignmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_assignments);

        recyclerView = findViewById(R.id.assignmentsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());

        db.assignmentDao().getAllAssignmentsLive().observe(this, new Observer<List<Assignment>>() {
            @Override
            public void onChanged(List<Assignment> assignments)
            {adapter = new AssignmentAdapter(assignments); recyclerView.setAdapter(adapter); }
        });
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, ViewAssignmentsActivity.class);
    }
}