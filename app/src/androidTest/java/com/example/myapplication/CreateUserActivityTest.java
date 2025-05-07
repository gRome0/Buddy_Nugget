package com.example.myapplication;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

@RunWith(AndroidJUnit4.class)
public class CreateUserActivityTest {

    @Test
    public void needsStudentPassAndUser() {
        try (ActivityScenario<CreateUserActivity> scenario = ActivityScenario.launch(new Intent(
                androidx.test.core.app.ApplicationProvider.getApplicationContext(), CreateUserActivity.class))) {
            onView(withId(R.id.newUsername)).perform(typeText("newuser"), closeSoftKeyboard());
            onView(withId(R.id.newPassword)).perform(typeText("pass123"), closeSoftKeyboard());
            onView(withId(R.id.roleSpinner)).perform(click());
            onView(withText("NORMAL")).perform(click());
            onView(withId(R.id.createUserBtn)).perform(click());
        }
    }
}
