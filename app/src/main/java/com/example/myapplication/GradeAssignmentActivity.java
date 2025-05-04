package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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


    }
}
