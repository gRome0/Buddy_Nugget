package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GradeAssignmentActivity extends AppCompatActivity {
    private Spinner assignmentSpinner;
    private EditText studentIdField, scoreField;
    private Button gradeBtn;
    private Map<String, Integer> assignmentMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_assignment);
        assignmentSpinner = findViewById(R.id.gradeScore); gradeBtn = findViewById(R.id.submitGradeBtn);
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        List<Assignment> assignments = db.assignmentDao().getAllAssignments();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        for (Assignment a : assignments) {
            String label = "ID: " + a.getAssignmentId() + " - " + a.getTitle();
            adapter.add(label);
            assignmentMap.put(label, a.getAssignmentId());
        }

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assignmentSpinner.setAdapter(adapter);

        gradeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String studentIdText = studentIdField.getText().toString().trim();
                String scoreText = scoreField.getText().toString().trim();
                String selectedLabel = (String) assignmentSpinner.getSelectedItem();
                if (studentIdText.isEmpty() || scoreText.isEmpty() || selectedLabel == null){
                Toast.makeText(GradeAssignmentActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
                }
                try {
                    int assignmentId = assignmentMap.get(selectedLabel);
                    float score = Float.parseFloat(scoreText);
                    User student = db.userDao().findByStudentId(studentIdText);
                    if (student == null) {
                        Toast.makeText(GradeAssignmentActivity.this, "Student ID not found", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Grade grade = new Grade(0, assignmentId, studentIdText, score);
                    db.gradeDao().insert(grade);
                    Toast.makeText(GradeAssignmentActivity.this, "Grade submitted for " + student.getUsername(), Toast.LENGTH_SHORT).show();
                    finish();
                }
                catch (NumberFormatException e) {
                    Toast.makeText(GradeAssignmentActivity.this, "Invalid score format", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public static Intent newIntent(android.content.Context context) {
        return new Intent(context, GradeAssignmentActivity.class);
    }
}
