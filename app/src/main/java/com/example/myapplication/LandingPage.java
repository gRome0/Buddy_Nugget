package com.example.myapplication;

/*
welcomes the user when ipens app
Author: @Gael
 */


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LandingPage extends AppCompatActivity {

    private TextView welcomeText;
    private Button adminBtn, logoutBtn, continueBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        welcomeText = findViewById(R.id.welcomeText);
        adminBtn = findViewById(R.id.adminBtn);
        logoutBtn = findViewById(R.id.logoutBtn);
        continueBtn = findViewById(R.id.continueBtn);

        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        int userId = prefs.getInt("userId", -1);

        if (userId != -1) {
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            User user = db.userDao().getUserById(userId);

            if (user != null) {
                welcomeText.setText("Welcome, " + user.getUsername());

                if (user.getRole() == User.Role.ADMIN) {
                    adminBtn.setVisibility(View.VISIBLE);
                } else {
                    adminBtn.setVisibility(View.INVISIBLE);
                }

                continueBtn.setVisibility(View.VISIBLE);
                continueBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (user.getRole()) {
                            case ADMIN:
                                startActivity(new Intent(LandingPage.this, AdminDashboardActivity.class));
                                break;
                            case TEACHER:
                                startActivity(new Intent(LandingPage.this, TeacherDashboardActivity.class));
                                break;
                            case NORMAL:
                                startActivity(new Intent(LandingPage.this, UserDashboardActivity.class));
                                break;
                        }
                    }
                });

                adminBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(LandingPage.this, AdminDashboardActivity.class));
                    }
                });
            }
        }

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {prefs.edit().remove("userId").apply();
                startActivity(new Intent(LandingPage.this, MainActivity.class)); finish(); }
        });
    }

    public static Intent newIntent(Context context)
    { return new Intent(context, LandingPage.class); }
}
















































