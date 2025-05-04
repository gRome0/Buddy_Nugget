package com.example.myapplication;

import androidx.lifecycle.LiveData;

public interface AssignmentDao {
    LiveData<Object> getAllAssignmentsLive();
}
