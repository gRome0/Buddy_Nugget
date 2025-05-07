package com.example.myapplication;

import static org.junit.Assert.assertEquals;

import android.view.ViewGroup;
import android.widget.TextView;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Test;

import java.util.Collections;

public class GradeWithAssignmentAdapterTest {
    @Test
    public void makeSureItsRightAssign(){
        Grade grade = new Grade(0, 1, "STU123", 84.0f);
        Assignment assignment = new Assignment(1, "Defeat EnderDragon", "You beat the dragon game over jk", "2025-05-20");
        GradeWithAssignment gwa = new GradeWithAssignment();

        gwa.grade = grade;
        gwa.assignment = assignment;

        GradeWithAssignmentAdapter adapter = new GradeWithAssignmentAdapter(Collections.singletonList(gwa));

        ViewGroup parent = new android.widget.FrameLayout(ApplicationProvider.getApplicationContext());
        GradeWithAssignmentAdapter.ViewHolder holder = adapter.onCreateViewHolder(parent, 0);
        adapter.onBindViewHolder(holder, 0);

        TextView titleView = holder.assignmentTitle;
        TextView scoreView = holder.gradeScore;

        assertEquals("Assignment: Defeat EnderDragon", titleView.getText().toString());
        assertEquals("Score: 84.0", scoreView.getText().toString());
    }
}
