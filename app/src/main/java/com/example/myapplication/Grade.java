package com.example.myapplication;

public class Grade {

    private int gradeId;
    private int assignmentId;
    private String studentId;
    private float score;

    public Grade(int gradeId, int assignmentId, String studentId, float score){
        this.gradeId = gradeId;
        this.assignmentId = assignmentId;
        this.studentId = studentId;
        this.score = score;
    }
}
