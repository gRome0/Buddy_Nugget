package com.example.myapplication;

import android.view.ViewGroup;
import android.widget.TextView;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class AssignmentAdapterTest {

    @Test
    public void showsTitleDueAndDescription() {
        Assignment a = new Assignment(0, "Project 2", "Create app on Android Studio in 338", "2025-06-01");

        AssignmentAdapter adapter = new AssignmentAdapter(Collections.singletonList(a));
        ViewGroup parent = new android.widget.FrameLayout(ApplicationProvider.getApplicationContext());
        AssignmentAdapter.AssignmentViewHolder holder = adapter.onCreateViewHolder(parent, 0);
        adapter.onBindViewHolder(holder, 0);

        assertEquals("Title: Project 2", holder.title.getText().toString());
        assertEquals("Due: 2025-06-01", holder.due.getText().toString());
        assertEquals("Description: Create app on Android Studio in 338", holder.description.getText().toString());
    }
}

