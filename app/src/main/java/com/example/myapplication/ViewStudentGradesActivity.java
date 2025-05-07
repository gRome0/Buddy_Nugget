package com.example.myapplication;
/*
shows teach. the students grades from their ids
Author: @Joseph
 */
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewStudentGradesActivity extends AppCompatActivity {

    private EditText inputStudentId;
    private Button fetchGradesBtn;
    private RecyclerView gradesRecyclerView;
    private GradeWithAssignmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_grades);

        inputStudentId = findViewById(R.id.inputStudentId);
        fetchGradesBtn = findViewById(R.id.fetchGradesBtn);
        gradesRecyclerView = findViewById(R.id.gradesRecyclerView);
        gradesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchGradesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sid = inputStudentId.getText().toString().trim();
                if (!sid.isEmpty())
                {AppDatabase db = AppDatabase.getInstance(getApplicationContext());
                    List<GradeWithAssignment> grades =
                            db.gradeDao().getGradesWithAssignmentsByStudent(sid); adapter = new GradeWithAssignmentAdapter(grades);
                    gradesRecyclerView.setAdapter(adapter); }
            }
        });
    }
}