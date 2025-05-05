package com.example.myapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentRosterForTeacherActivity extends AppCompatActivity {

    private RecyclerView studentRecyclerView;
    private NormalUserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_roster_for_teacher);

        studentRecyclerView = findViewById(R.id.studentRecyclerView);
        studentRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        List<User> students = db.userDao().getUsersByRole(User.Role.NORMAL.name());

        adapter = new NormalUserAdapter(students);
        studentRecyclerView.setAdapter(adapter);
    }
}
