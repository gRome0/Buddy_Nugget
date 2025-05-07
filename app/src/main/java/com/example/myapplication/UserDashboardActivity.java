package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class UserDashboardActivity extends AppCompatActivity {
    private Button viewAssignmentsBtn, logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        int userId = prefs.getInt("userId", -1);
        if (userId == -1) {finish();return; }
        User user = AppDatabase.getInstance(getApplicationContext()).userDao().getUserById(userId);
        if (user.getRole() != User.Role.NORMAL) {finish(); return;
        }
        setContentView(R.layout.activity_user_dashboard);
        if (savedInstanceState == null) { FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.userDashboardContainer, new ViewGradesFragment()); ft.commit();
        }
        viewAssignmentsBtn = findViewById(R.id.viewAssignmentsBtn);
        viewAssignmentsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { Intent intent = ViewAssignmentsActivity.newIntent(UserDashboardActivity.this); startActivity(intent);
            }
        });

        logoutBtn = findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {prefs.edit().remove("userId").apply();Intent intent = new Intent(UserDashboardActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); startActivity(intent); finish();
            }
        });
    }
    public static Intent newIntent(android.content.Context context) {
        return new Intent(context, UserDashboardActivity.class);
    }
}

