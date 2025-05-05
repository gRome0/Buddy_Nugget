package com.example.myapplication;

import androidx.room.*;
import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(com.example.myapplication.User user);

    @Update
    void update(com.example.myapplication.User user);

    @Delete
    void delete(com.example.myapplication.User user);



    @Query ("SELECT * FROM User WHERE username = :username AND password = :password LIMIT 1")
    com.example.myapplication.User login(String username, String password);

    @Query("SELECT * FROM User WHERE username = :username LIMIT 1")
    com.example.myapplication.User findByUsername(String username);

    @Query("SELECT * FROM User WHERE userId = :userId LIMIT 1")
    com.example.myapplication.User getUserById(int userId);

    @Query("SELECT * FROM User")
    List<com.example.myapplication.User> getAllUsers();

    @Query("SELECT * FROM User WHERE role = :role")
    List<com.example.myapplication.User> getUsersByRole(String role);

    @Query("SELECT * FROM User WHERE studentId = :studentId LIMIT 1")
    com.example.myapplication.User findByStudentId(String studentId);
}