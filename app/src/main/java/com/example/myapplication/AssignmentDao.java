package com.example.myapplication;

/*
Assignment updates and stores assignment
Author: @Javier
 */

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AssignmentDao {

    @Insert
    long insert(Assignment assignment);

    @Update
    void update(Assignment assignment);

    @Delete
    void delete(Assignment assignment);

    @Query("SELECT * FROM Assignment")
    List<Assignment> getAllAssignments();
    @Query("SELECT * FROM Assignment")
    LiveData<List<Assignment>> getAllAssignmentsLive();
}
