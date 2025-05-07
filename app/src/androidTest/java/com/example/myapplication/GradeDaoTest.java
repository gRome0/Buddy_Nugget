package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class GradeDaoTest {
    private AppDatabase db;
    private GradeDao gradeDao;

    @Before
    public void setup() {
        db = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), AppDatabase.class).allowMainThreadQueries().build();gradeDao = db.gradeDao();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void insertGradeAndFindByStudentId() {
        Grade grade = new Grade(0, 101, "S123", 92.5f);gradeDao.insert(grade);
        List<Grade> grades = gradeDao.getGradesByStudent("S123");assertEquals(1, grades.size());assertEquals(92.5f, grades.get(0).getScore(), 0.001);
    }
    @Test
    public void deleteGrade() {
        Grade grade = new Grade(0, 202, "S999", 87.0f);gradeDao.insert(grade);
        List<Grade> grades = gradeDao.getGradesByStudent("S999");assertFalse(grades.isEmpty());
        Grade insertedGrade = grades.get(0);gradeDao.delete(insertedGrade);
        List<Grade> deletedResult = gradeDao.getGradesByStudent("S999");assertTrue(deletedResult.isEmpty());
    }
    @Test
    public void updateGradeValue() {
        Grade grade = new Grade(0, 303, "S456", 70.0f);gradeDao.insert(grade);
        Grade inserted = gradeDao.getGradesByStudent("S456").get(0);inserted.setScore(95.0f);
        gradeDao.update(inserted);Grade updated = gradeDao.getGradesByStudent("S456").get(0);assertEquals(95.0f, updated.getScore(), 0.001);
    }
}

