package com.example.myapplication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class GradeAssignmentActivityTest {
    @Test
    public void NoNoNoEmptiesz() {
        try (ActivityScenario<GradeAssignmentActivity> scenario = ActivityScenario.launch(
                new Intent(androidx.test.core.app.ApplicationProvider.getApplicationContext(), GradeAssignmentActivity.class))) { onView(withId(R.id.submitGradeBtn)).perform(click());
        } //TODO need to add gradle code for unit test
    }
}
