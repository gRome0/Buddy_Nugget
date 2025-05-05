package com.example.myapplication;

import androidx.room.Embedded;
import androidx.room.Relation;

public class GradeWithAssignment {
    @Embedded
    public com.example.myapplication.Grade grade;


    @Relation(parentColumn = "assignmentId", entityColumn = "assignmentId")
    public com.example.myapplication.Assignment assignment;
}
