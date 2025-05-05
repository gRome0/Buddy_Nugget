package com.example.myapplication;
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
    }
}
