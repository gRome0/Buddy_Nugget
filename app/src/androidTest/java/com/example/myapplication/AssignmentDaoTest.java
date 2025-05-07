package com.example.myapplication;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class AssignmentDaoTest {

    private AppDatabase db;
    private AssignmentDao assignmentDao;


    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class) .allowMainThreadQueries().build();
        assignmentDao = db.assignmentDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void testInsertAssignment() {
        Assignment a = new Assignment(0, "HW01", "Finish Unit 1", "2025-12-31");
        long id = assignmentDao.insert(a);

        List<Assignment> allAssignments = assignmentDao.getAllAssignments();
        assertEquals(1, allAssignments.size());
        assertEquals("HW01", allAssignments.get(0).getTitle());
        assertTrue(id > 0);
    }

    @Test
    public void testUpdateAssignment() {
        Assignment a = new Assignment(0, "HW02", "Original Desc", "2025-12-31");
        assignmentDao.insert(a);

        Assignment inserted = assignmentDao.getAllAssignments().get(0);
        inserted.setDescription("Updated Desc");
        assignmentDao.update(inserted);

        Assignment updated = assignmentDao.getAllAssignments().get(0);
        assertEquals("Updated Desc", updated.getDescription());
    }

    @Test
    public void testDeleteAssignment() {
        Assignment a = new Assignment(0, "HW03", "To be deleted", "2025-12-31");
        assignmentDao.insert(a);

        Assignment toDelete = assignmentDao.getAllAssignments().get(0);
        assignmentDao.delete(toDelete);

        List<Assignment> result = assignmentDao.getAllAssignments();
        assertTrue(result.isEmpty());
    }
}
