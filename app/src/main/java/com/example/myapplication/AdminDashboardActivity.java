package com.example.myapplication;
/*
This is a dashboard for admin options to create users or
assignments.
Author: @Gael
 */


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdminDashboardActivity extends AppCompatActivity {

    private Button createUserBtn, createAssignmentBtn, logoutBtn;

    private RecyclerView allUsersRecyclerView;
    private NormalUserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        int userId = prefs.getInt("userId", -1);
        if (userId == -1) { finish(); return; }
        User user = AppDatabase.getInstance(getApplicationContext()).userDao().getUserById(userId);

        if (user.getRole() != User.Role.ADMIN) {finish(); return; }
        setContentView(R.layout.activity_admin_dashboard);
        createUserBtn = findViewById(R.id.createUserBtn);
        createAssignmentBtn = findViewById(R.id.createAssignmentBtn);
        logoutBtn = findViewById(R.id.logoutBtn);
        allUsersRecyclerView = findViewById(R.id.allUsersRecyclerView);
        allUsersRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        AppDatabase db = AppDatabase.getInstance(getApplicationContext());

        db.userDao().getAllUsersLive().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {userAdapter = new NormalUserAdapter(users);allUsersRecyclerView.setAdapter(userAdapter); }
        });


        createUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {startActivity(CreateUserActivity.newIntent(AdminDashboardActivity.this));}
        });


        createAssignmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(CreateAssignmentActivity.newIntent(AdminDashboardActivity.this)); }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { prefs.edit().remove("userId").apply();
                Intent intent = new Intent(AdminDashboardActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);startActivity(intent);
                finish();
            }
        });
    }

    public static Intent newIntent(android.content.Context context) {
        return new Intent(context, AdminDashboardActivity.class);
    }
}
