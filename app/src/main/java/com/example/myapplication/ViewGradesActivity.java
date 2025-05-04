package com.example.myapplication;

import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ViewGradesActivity extends AppCompatActivity {
    private RecyclerView gradesRecyclerView;
    private GradeWithAssignmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInsatnceState);
    setContentView(R.layout.activity_view_grades);
    gradesRecyclerView = findViewById(R.id.gradesRecyclerView);
    gradesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        int userId = prefs.getInt("userId", -1);
        if (userId == -1)
        {Toast.makeText(this, "No user logged in.", Toast.LENGTH_SHORT.show()); finish();return; }

    }
}
