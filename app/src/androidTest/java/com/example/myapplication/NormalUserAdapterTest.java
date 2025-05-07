package com.example.myapplication;

import android.view.ViewGroup;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class NormalUserAdapterTest {

    @Test
    public void showsUsernameAndStudentId() {
        User user = new User(0, "johnPork", "pass", User.Role.NORMAL, "SID007");

        NormalUserAdapter adapter = new NormalUserAdapter(Collections.singletonList(user));
        ViewGroup parent = new android.widget.FrameLayout(ApplicationProvider.getApplicationContext());
        NormalUserAdapter.NormalUserViewHolder holder = adapter.onCreateViewHolder(parent, 0);
        adapter.onBindViewHolder(holder, 0);

        assertEquals("Username: johnPork", holder.usernameView.getText().toString());
        assertEquals("Student ID: SID007", holder.studentIdView.getText().toString());
    }

}
