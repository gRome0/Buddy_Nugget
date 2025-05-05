package com.example.myapplication;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TeacherDashboardActivity extends AppCompatActivity {
    private Button createAssignmentBtn;
    private Button gradeAssignmentsBtn;
    private Button viewStudentRosterBtn;
    private Button logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);

        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        int userId = prefs.getInt("userId", -1);
        if (userId == -1) {
            finish();
            return;
        }

        User user = AppDatabase.getInstance(getApplicationContext()).userDao().getUserById(userId);
        if (user.getRole() != User.Role.TEACHER) {
            finish();
            return;
        }

        createAssignmentBtn = findViewById(R.id.createAssignmentBtn);
        gradeAssignmentsBtn = findViewById(R.id.gradeAssignmentsBtn);
        viewStudentRosterBtn = findViewById(R.id.viewStudentRosterBtn);
        logoutBtn = findViewById(R.id.logoutBtn);
    }
}
