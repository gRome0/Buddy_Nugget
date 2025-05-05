package com.example.myapplication;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity
@TypeConverters(com.example.myapplication.RoleConverter.class)
public class User {
    public enum Role {
        NORMAL, TEACHER, ADMIN;

        /// ///toString
        @Override
        public String toString() {
            return name();
        }
    }

    @PrimaryKey(autoGenerate = true)
    private int userId;
    private String username;
    private String password;
    private Role role;
    private String studentId;


    public User(int userId, String username, String password, Role role, String studentId) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.studentId = studentId;
    }


    @Ignore
    public User(int userId, String username, String password, Role role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.studentId = null; // Default
    }

    /// setters and getters
    public int getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public Role getRole() { return role; }
    public String getStudentId() { return studentId; }

    public void setUserId(int userId) { this.userId = userId; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setRole(Role role) { this.role = role; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
}

