package com.gromeo.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.CreateUserActivity;
import com.example.myapplication.LandingPage;
import com.example.myapplication.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private Button loginBtn, createAccountBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        int userId = prefs.getInt("userId", -1);
        if (userId != -1)
        {startActivity(new Intent(MainActivity.this, LandingPage.class)); finish(); return; }


        loginBtn = findViewById(R.id.loginBtn);
        createAccountBtn = findViewById(R.id.createAccountBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {Intent intent = new Intent(MainActivity.this, LoginActivity.class); startActivity(intent); } });

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            { Intent intent = new Intent(MainActivity.this, CreateUserActivity.class); startActivity(intent); } });
    }
    public static Intent newIntent(Context context)
    { return new Intent(context, MainActivity.class); }
}
