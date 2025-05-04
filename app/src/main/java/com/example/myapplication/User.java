package com.example.myapplication;




@Entity

public class User {
    public enum Role { NORMAL, TEACHER, ADMIN;
     @Override
     public String toString(){return name();}
    }

    @PrimaryKey(autoGenerate = true)
    private int userID;
    private String username;
    private String password;
    private Role role;
    private String studentID;




}
