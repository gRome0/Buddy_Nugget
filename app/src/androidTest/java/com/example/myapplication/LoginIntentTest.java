package com.example.myapplication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

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
public class LoginIntentTest {

    private Context context;
    private AppDatabase db;

    @Before
    public void setup() { context = ApplicationProvider.getApplicationContext(); db = AppDatabase.getInstance(context);
        db.userDao().deleteAll(); Intents.init();
    }
    @After
    public void teardown() {
        Intents.release(); SharedPreferences prefs = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE); prefs.edit().clear().apply(); db.userDao().deleteAll();
    }

    private void performLogin(String username, String password) {
        onView(withId(R.id.username)).perform(replaceText(username)); onView(withId(R.id.password)).perform(replaceText(password));
        onView(withId(R.id.LoginBtn)).perform(click());
    }

    @Test
    public void testNormalUserDahs() {
        User user = new User(0, "normalUser", "pass123", User.Role.NORMAL, "SID100"); db.userDao().insert(user);
        ActivityScenario.launch(LoginActivity.class); performLogin("normalUser", "pass123"); intended(hasComponent(UserDashboardActivity.class.getName()));
    }

    @Test
    public void testTeacherLogin() {
        User user = new User(0, "teacherUser", "teach123", User.Role.TEACHER, "TID200");
        db.userDao().insert(user); ActivityScenario.launch(LoginActivity.class); performLogin("teacherUser", "teach123"); intended(hasComponent(TeacherDashboardActivity.class.getName()));
    }

    @Test
    public void testAdminLoginDassh() {
        User user = new User(0, "adminUser", "adminpass", User.Role.ADMIN, "AID300");
        db.userDao().insert(user); ActivityScenario.launch(LoginActivity.class); performLogin("adminUser", "adminpass"); intended(hasComponent(AdminDashboardActivity.class.getName()));
    }
}