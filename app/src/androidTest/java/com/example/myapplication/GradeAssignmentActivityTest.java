package com.example.myapplication;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;

import org.junit.Test;

public class GradeAssignmentActivityTest {
    @Test
    public void NoNoNoEmptiesz() {
        try(ActivityScenario<GradeAssignmentActivity> scenario = ActivityScenario.launch(new Intent(androidx.test)))
    }
}
