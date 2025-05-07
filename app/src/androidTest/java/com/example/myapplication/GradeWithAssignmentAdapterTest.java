package com.example.myapplication;

import org.junit.Test;

public class GradeWithAssignmentAdapterTest {
    @Test
    public void makeSureItsRightAssign(){
        Grade grade = new Grade(0, 1, "STU123", 84.0f);
        Assignment assignment = new Assignment(1, "Defeat EnderDragon", "You beat the dragon game over jk", "2025-05-20");
    }
}
