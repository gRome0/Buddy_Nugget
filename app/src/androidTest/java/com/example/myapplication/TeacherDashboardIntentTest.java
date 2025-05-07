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
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.intent.Intents;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class TeacherDashboardIntentTest {
    private Context context;
    private AppDatabase db;
    private int teacherUserId;

    @Before
    public void setup() {
        Intents.init(); context = ApplicationProvider.getApplicationContext(); db = AppDatabase.getInstance(context);
        User teacher = new User(); teacher.setUsername("testTeacher");
        teacher.setPassword("teachpass"); teacher.setRole(User.Role.TEACHER); teacherUserId = (int) db.userDao().insert(teacher);
        SharedPreferences prefs = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE); prefs.edit().putInt("userId", teacherUserId).apply();
        ActivityScenario.launch(TeacherDashboardActivity.class);
    }
    @After
    public void tearDown()
    {Intents.release(); db.userDao().deleteAll(); SharedPreferences prefs = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE); prefs.edit().clear().apply();
    }
    @Test
    public void testCreateAssigBtns() { onView(withId(R.id.createAssignmentBtn)).perform(click()); intended(hasComponent(CreateAssignmentActivity.class.getName()));
    }
    @Test
    public void testViewStudenRostBtn() { onView(withId(R.id.viewStudentRosterBtn)).perform(click()); intended(hasComponent(StudentRosterForTeacherActivity.class.getName()));
    }
}