package com.example.myapplication;



import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity{
private EditText usernameField, passwordField;
private Button loginButton;

private AppDatabase db;
private UserDao userDao;

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


}



































}
