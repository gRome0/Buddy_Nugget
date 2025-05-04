package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class UserDashboardActivity extends AppCompatActivity {
    private Button viewAssignmentBtn, viewAssignmentsBtn, viewGradesBtn, logoutBtn;

    @Override
    protected void Oncreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);

        SharedPreferencesprefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        int userId = prefs.getInt("userId", -1);
        if(userId == -1) { finish (); return; }

        User user = AppDatabase.getInstance(getApplicationContext()).userDao().getUserById(userId);
        if (user.getRole() != User.Role.NORMAL) { finish(); return; }

        setContentView(R.layout.activity_admin_dashboard);
        viewAssignmentBtn = findViewById(R.id.viewAssignmentsBtn);
        viewGradesBtn = findViewById(R.id.viewGradeBtn);
        logoutBtn = findViewById(R.id.logoutBtn);

        viewAssignmentBtn.setOnClickListener(new View.OnClickListener()) {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(UserDashboardActivity.this, ViewAssignmentsActivity.class); startActivity();
            });
            viewGradesBtn.setOnClickListener(new View.OnClickListener()) {
                @Override
                        public void onClick(View v)
                {
                    Intent intent = new Intent(UserDashboardActivity.this, ViewGradesActivity.class);
                    startActivity(intent);
                });


            }

        }

    }
}
