package com.example.myapplication;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/*
dash for teacher to creat and assign, look and roster and grades
Author: @javier
 */

public class TeacherDashboardActivity extends AppCompatActivity {
    private Button createAssignmentBtn, gradeAssignmentsBtn, viewStudentRosterBtn, logoutBtn;

    private Button viewGradesByStudentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        int userId = prefs.getInt("userId", -1);
        if (userId == -1) {finish();return;
        }

        User user = AppDatabase.getInstance(getApplicationContext()).userDao().getUserById(userId);
        if (user.getRole() != User.Role.TEACHER) {finish();return;
        }

        setContentView(R.layout.activity_teacher_dashboard);

        createAssignmentBtn = findViewById(R.id.createAssignmentBtn);
        gradeAssignmentsBtn = findViewById(R.id.gradeAssignmentsBtn);
        viewStudentRosterBtn = findViewById(R.id.viewStudentRosterBtn);
        logoutBtn = findViewById(R.id.logoutBtn);


        viewGradesByStudentBtn = findViewById(R.id.viewGradesByStudentBtn);

        createAssignmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherDashboardActivity.this, CreateAssignmentActivity.class));
            }
        });

        gradeAssignmentsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherDashboardActivity.this, GradeAssignmentActivity.class));
            }
        });

        viewStudentRosterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherDashboardActivity.this, StudentRosterForTeacherActivity.class));
            }
        });


        viewGradesByStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherDashboardActivity.this, ViewStudentGradesActivity.class));
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefs.edit().remove("userId").apply();
                Intent intent = new Intent(TeacherDashboardActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, TeacherDashboardActivity.class);
    }
}