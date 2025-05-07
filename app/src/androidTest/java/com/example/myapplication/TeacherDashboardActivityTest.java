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
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class TeacherDashboardActivityTest {

    @Before
    public void setup() {
        Context context = ApplicationProvider.getApplicationContext();
        UserDao dao = AppDatabase.getInstance(context).userDao();

        if (dao.findByUsername("teacher1") == null) {
            dao.insert(new User(0, "teacher1", "teachpass", User.Role.TEACHER, null));
        }
        context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE).edit().putInt("userId", dao.findByUsername("teacher1").getUserId()).apply();
        Intents.init();
    }

    @Test
    public void CreateAssignmentBtnWorks() {
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), TeacherDashboardActivity.class);
        try (ActivityScenario<TeacherDashboardActivity> scenario = ActivityScenario.launch(intent))
        {onView(withId(R.id.createAssignmentBtn)).perform(click());
            intended(hasComponent(CreateAssignmentActivity.class.getName()));
        }
    }

    @Test
    public void ViewRosterBtnWorks() {
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), TeacherDashboardActivity.class);
        try (ActivityScenario<TeacherDashboardActivity> scenario = ActivityScenario.launch(intent))
        {onView(withId(R.id.viewStudentRosterBtn)).perform(click());
            intended(hasComponent(StudentRosterForTeacherActivity.class.getName()));
        }
    }
}
