package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class UserDashboardActivity extends AppCompatActivity {
    private Button viewAssignmentsBtn, viewGradesBtn, logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        int userId = prefs.getInt("userId", -1);
        if (userId == -1) { finish(); return; }

        User user = AppDatabase.getInstance(getApplicationContext()).userDao().getUserById(userId);
        if (user.getRole() != User.Role.NORMAL) { finish(); return; }


        setContentView(R.layout.activity_user_dashboard);
        viewAssignmentsBtn = findViewById(R.id.viewAssignmentsBtn);
        viewGradesBtn = findViewById(R.id.viewGradesBtn);
        logoutBtn = findViewById(R.id.logoutBtn);

        viewAssignmentsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {Intent intent = new Intent(UserDashboardActivity.this, ViewAssignmentsActivity.class); startActivity(intent);}
        });


        viewGradesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {Intent intent = new Intent(UserDashboardActivity.this, ViewGradesActivity.class); startActivity(intent); }
        });


        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefs.edit().remove("userId").apply();
                Intent intent = new Intent(UserDashboardActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, UserDashboardActivity.class);
    }
}

