package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewGradesActivity extends AppCompatActivity {
    private RecyclerView gradesRecyclerView;
    private GradeWithAssignmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_grades);
        gradesRecyclerView = findViewById(R.id.gradesRecyclerView);
        gradesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        int userId = prefs.getInt("userId", -1);
        if (userId == -1)

        {Toast.makeText(this, "No user logged in.", Toast.LENGTH_SHORT).show(); finish();return; }

        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        User student = db.userDao().getUserById(userId);
        if (student == null || student.getRole() != User.Role.NORMAL || student.getStudentId() == null)
        { Toast.makeText(this, "Invalid user or missing Student ID.", Toast.LENGTH_SHORT).show(); finish(); return; }
        String studentId = student.getStudentId();

        db.gradeDao().getGradesWithAssignmentsLive(studentId).observe(this, new Observer<List<GradeWithAssignment>>() {
            @Override
            public void onChanged(List<GradeWithAssignment> grades)
            { adapter = new GradeWithAssignmentAdapter(grades); gradesRecyclerView.setAdapter(adapter);
                if (grades.isEmpty()) { Toast.makeText(ViewGradesActivity.this, "No grades found.", Toast.LENGTH_SHORT).show(); }
            }
        });
    }
    public static Intent newIntent(Context context) { return new Intent(context, ViewGradesActivity.class); }
}
