package com.example.myapplication;

import static org.junit.Assert.*;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.*;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class UserDaoTest {
    private AppDatabase db;
    private UserDao userDao;

    @Before
    public void setup() {
        db = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(), AppDatabase.class) .allowMainThreadQueries().build(); userDao = db.userDao();
    }

    @After
    public void closeDb()
    {db.close();}

    @Test
    public void findTheName() {
        User user = new User(0, "testuser", "pass123", User.Role.NORMAL, "S001");
        userDao.insert(user); User found = userDao.findByUsername("testuser");assertNotNull(found); assertEquals("S001", found.getStudentId());
    }

    @Test
    public void loginRighInfo() {
        userDao.insert(new User(0, "admin2", "admin2", User.Role.ADMIN, null)); User login = userDao.login("admin2", "admin2");assertNotNull(login);
        assertEquals(User.Role.ADMIN, login.getRole());
    }

    @Test
    public void updateUser() {
        User user = new User(0, "updatableUser", "pass", User.Role.NORMAL, "S123"); userDao.insert(user);
        User fetched = userDao.findByUsername("updatableUser"); fetched.setRole(User.Role.TEACHER); userDao.update(fetched);
        User updated = userDao.findByUsername("updatableUser"); assertEquals(User.Role.TEACHER, updated.getRole());
    }

    @Test
    public void deleteUser() {
        User user = new User(0, "todelete", "delete123", User.Role.NORMAL, "S321"); userDao.insert(user);
        User inserted = userDao.findByUsername("todelete"); assertNotNull(inserted);
        userDao.delete(inserted); User deleted = userDao.findByUsername("todelete");assertNull(deleted);
    }
}

