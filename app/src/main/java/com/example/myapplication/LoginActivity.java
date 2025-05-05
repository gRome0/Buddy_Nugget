package com.example.myapplication;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity{
private EditText usernameField, passwordField;
private Button loginButton;

private AppDatabase db;
private com.example.myapplication.UserDao userDao;

@Override
protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    usernameField = findViewById(R.id.username);
    passwordField = findViewById(R.id.password);
    loginButton = findViewById(R.id.LoginBtn);

    db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "grade_tracker_db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build();
    userDao = db.userDao();

    /// this is the admin 2 requirement ///
    if (userDao.findByUsername("admin2") == null) {
        User defaultAdmin = new User(0, "admin2", "admin2", User.Role.ADMIN);
        userDao.insert(defaultAdmin);
    }
    if (userDao.findByUsername("testuser1") == null) {
        User defaultNormal = new User(0, "testuser1", "testuser1", User.Role.NORMAL);
        userDao.insert(defaultNormal);
    }
    loginButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String username = usernameField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this,
                        "Please enter both username and password", Toast.LENGTH_SHORT).show();
                return; }
        }

        User user = userDao.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            prefs.edit().putInt("userId", user.getUserId()).apply();

            Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();

            switch (user.getRole()) {
                case ADMIN:
                    startActivity(AdminDashboardActivity.newIntent(LoginActivity.this));
                    break;
                case TEACHER:
                    startActivity(TeacherDashboardActivity.newIntent(LoginActivity.this));
                    break;
                case NORMAL:
                    startActivity(UserDashboardActivity.newIntent(LoginActivity.this));
                    break;
                default:
                    Toast.makeText(LoginActivity.this, "Unknown role", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        }
    });
}
   public static Intent newIntent (Context context) {
     return new Intent(context, LoginActivity.class);
    }
}
