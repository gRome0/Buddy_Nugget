package com.example.myapplication;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;



@Entity
public class package com.example.myapplication; Grade {
    @PrimaryKey(autoGenerate = true)
    private int gradeId;
    private int assignmentId;
    private String studentId;
    private float score;


    @Ignore
    public Grade(int gradeId, int assignmentId, String studentId, float score){
        this.gradeId = gradeId;
        this.assignmentId = assignmentId;
        this.studentId = studentId;
        this.score = score;
    }


    public Grade(){}

    public int getGradeId() { return gradeId; }
    public int getAssignmentId() { return assignmentId; }
    public String getStudentId() { return studentId; }
    public float getScore() { return score; }

    public void setGradeId(int gradeId) { this.gradeId = gradeId; }
    public void setAssignmentId(int assignmentId) { this.assignmentId = assignmentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public void setScore(float score) {this.score = score; }

}
