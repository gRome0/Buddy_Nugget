package com.example.myapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class StudentRosterForTeacherActivity extends AppCompatActivity {

    private RecyclerView studentRecyclerView;
    private NormalUserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView();
    }
}
