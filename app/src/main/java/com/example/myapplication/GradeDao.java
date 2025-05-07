package com.example.myapplication;


/*
for inserting, updating, and store grades with LiveData
Author: Joseph
 */

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface GradeDao {

    @Insert
    void insert(Grade grade);

    @Update
    void update(Grade grade);

    @Delete
    void delete(Grade grade);

    @Query("SELECT * FROM Grade WHERE studentId = :studentId")
    List<Grade> getGradesByStudent(String studentId);

    @Query("SELECT * FROM Grade WHERE assignmentId = :assignmentId")
    List<Grade> getGradesByAssignment(int assignmentId);

    @Query("SELECT * FROM Grade")
    List<Grade> getAllGrades();

    @Transaction
    @Query("SELECT * FROM Grade WHERE studentId = :studentId")
    List<GradeWithAssignment> getGradesWithAssignmentsByStudent(String studentId);

    @Transaction
    @Query("SELECT * FROM Grade WHERE studentId = :studentId")
    LiveData<List<GradeWithAssignment>> getGradesWithAssignmentsLive(String studentId);

}