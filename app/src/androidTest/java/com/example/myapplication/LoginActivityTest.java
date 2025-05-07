package com.example.myapplication;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Before
    public void setup() {
        UserDao dao = AppDatabase.getInstance(ApplicationProvider.getApplicationContext()).userDao();
        if (dao.findByUsername("testuser") == null) {
            dao.insert(new User(0, "testuser", "pass", User.Role.NORMAL, "SID001"));
        }
        Intents.init();
    }

    @Test
    public void loginWorks() {
        Intent i = new Intent(ApplicationProvider.getApplicationContext(), LoginActivity.class);
        try (ActivityScenario<LoginActivity> sc = ActivityScenario.launch(i)) {
            onView(withId(R.id.username)).perform(typeText("testuser"), closeSoftKeyboard());
            onView(withId(R.id.password)).perform(typeText("pass"), closeSoftKeyboard());
            onView(withId(R.id.LoginBtn)).perform(click());
            intended(hasComponent(UserDashboardActivity.class.getName()));
        }
    }
}
