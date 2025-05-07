package com.example.myapplication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LandingPageIntentTest {

    private Context context;
    private AppDatabase db;

    @Before
    public void setup() {
        context = ApplicationProvider.getApplicationContext(); db = AppDatabase.getInstance(context); db.userDao().deleteAll(); Intents.init();
    }

    @After
    public void  userPrefff()
    {db.userDao().deleteAll(); SharedPreferences prefs = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE); prefs.edit().clear().apply();Intents.release();
    }

    private void setupUser(User.Role role) {
        User user = new User(0, "test_" + role.name(), "password", role, "SID" + role.name());
        long userId = db.userDao().insertReturnId(user); SharedPreferences prefs = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE); prefs.edit().putInt("userId", (int) userId).apply();
    }

    @Test
    public void testAdminAdminDashboard() {setupUser(User.Role.ADMIN);
        ActivityScenario.launch(LandingPage.class); onView(withId(R.id.continueBtn)).perform(click());intended(hasComponent(AdminDashboardActivity.class.getName()));
    }

    @Test
    public void testTeacherTeacherDashboard() {
        setupUser(User.Role.TEACHER); ActivityScenario.launch(LandingPage.class); onView(withId(R.id.continueBtn)).perform(click());
        intended(hasComponent(TeacherDashboardActivity.class.getName()));
    }

    @Test
    public void testNormalToUserDashboard() {
        setupUser(User.Role.NORMAL); ActivityScenario.launch(LandingPage.class); onView(withId(R.id.continueBtn)).perform(click());
        intended(hasComponent(UserDashboardActivity.class.getName()));
    }
}
