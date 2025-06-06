package com.example.myapplication;
/*
Form for admin to create new users
Author: @Javier
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateUserActivity extends AppCompatActivity {
    private EditText usernameField, passwordField, studentIdField;
    private Button createBtn;
    private Spinner roleSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        usernameField = findViewById(R.id.newUsername);
        passwordField = findViewById(R.id.newPassword);
        studentIdField = findViewById(R.id.newStudentId);
        createBtn = findViewById(R.id.createUserBtn);
        roleSpinner = findViewById(R.id.roleSpinner);


        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        boolean createFromMain = prefs.getBoolean("createFromMain", false);
        if (createFromMain) {
            prefs.edit().remove("createFromMain").apply(); // Clean up flag
        }


        String[] allowedRoles = createFromMain ? new String[]{"NORMAL"} : new String[]{"NORMAL", "TEACHER", "ADMIN"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                allowedRoles
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roleSpinner.setAdapter(adapter);

        roleSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                String selectedRole = roleSpinner.getSelectedItem().toString();
                if (selectedRole.equals("NORMAL")) {studentIdField.setVisibility(View.VISIBLE);
                } else {
                    studentIdField.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) { }
        });

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameField.getText().toString().trim();
                String password = passwordField.getText().toString().trim();
                String studentId = studentIdField.getText().toString().trim();
                String selectedRole = roleSpinner.getSelectedItem().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(CreateUserActivity.this, "Username and password are required", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (selectedRole.equals("NORMAL") && studentId.isEmpty()) { Toast.makeText(CreateUserActivity.this, "Student ID is required for NORMAL users", Toast.LENGTH_SHORT).show();
                    return;
                }

                AppDatabase db = AppDatabase.getInstance(getApplicationContext());
                UserDao userDao = db.userDao();

                if (userDao.findByUsername(username) != null) {
                    Toast.makeText(CreateUserActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();
                    return;
                }

                User.Role role = User.Role.valueOf(selectedRole); String studentIdToUse = selectedRole.equals("NORMAL") ? studentId : null;
                User newUser = new User(0, username, password, role, studentIdToUse);
                userDao.insert(newUser);

                Toast.makeText(CreateUserActivity.this, role + " account created successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    public static android.content.Intent newIntent(android.content.Context context) {
        return new android.content.Intent(context, CreateUserActivity.class);
    }
}
