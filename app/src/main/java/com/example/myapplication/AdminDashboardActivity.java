package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminDashboardActivity extends AppCompatActivity  {

    private Button createUserBtn, createAssignmentBtn, logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        int userId = prefs.getInt("userId", -1);
        if (userId == -1) {
            finish();
            return;
        }
        User user = AppDatabase.getInstance(getApplicationContext()).userDao().getUserById(userId);
        if (user.getRole() != User.Role.ADMIN) {
            finish();
            return;
        }
        setContentView(R.layout.activity_admin_dashboard);
        createUserBtn = findViewById(R.id.createUserBtn);
        createAssignmentBtn = findViewById(R.id.createAssignmentBtn);
        logoutBtn = findViewById(R.id.logoutBtn);
        createUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboardActivity.this, CreateUserActivity.class));
            }
        });