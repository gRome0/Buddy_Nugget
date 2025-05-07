package com.example.myapplication;

/*
Assignment stores assignment title, description, and due date.
Author: @Javier
 */


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Assignment {
    @PrimaryKey(autoGenerate = true)
    private int assignmentId;

    private String title;
    private String description;
    private String dueDate;

    //TODO: might need to add courseId or Date type for dueDate, implement getDescription
    public Assignment(int assignmentId, String title, String description, String dueDate) {
        this.assignmentId = assignmentId;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }

    public int getAssignmentId() {
        return assignmentId;
    }
    public String getTitle() {

        return title;
    }
    public String getDescription() {

        return description;
    }
    public String getDueDate() {

        return dueDate;
    }
    public void setAssignmentId(int assignmentId) {

        this.assignmentId = assignmentId;
    }
    public void setTitle(String title) {

        this.title = title;}
    public void setDescription(String description) { this.description = description; }
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}

