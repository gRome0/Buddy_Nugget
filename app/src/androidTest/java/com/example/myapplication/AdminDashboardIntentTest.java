package com.example.myapplication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;

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
public class AdminDashboardIntentTest {

    private Context context;
    private AppDatabase db;
    private int adminUserId;

    @Before
    public void setup() {
        Intents.init();
        context = ApplicationProvider.getApplicationContext(); db = AppDatabase.getInstance(context);
        User adminUser = new User(); adminUser.setUsername("testAdmin"); adminUser.setPassword("pass123"); adminUser.setRole(User.Role.ADMIN); adminUserId = (int) db.userDao().insert(adminUser);
        SharedPreferences prefs = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE); prefs.edit().putInt("userId", adminUserId).apply(); ActivityScenario.launch(AdminDashboardActivity.class);
    }

    @After
    public void tearDown() { Intents.release();
        db.userDao().deleteAll(); SharedPreferences prefs = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);prefs.edit().clear().apply();
    }

    @Test
    public void testCreateUserIntentLaunches() { onView(withId(R.id.createUserBtn)).perform(click()); intended(hasComponent(CreateUserActivity.class.getName()));
    }

    @Test
    public void testCreateAssignmentIntentLaunches()
    {onView(withId(R.id.createAssignmentBtn)).perform(click()); intended(hasComponent(CreateAssignmentActivity.class.getName()));
    }
}
