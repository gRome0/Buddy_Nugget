package com.example.myapplication;




@Entity
@TypeConverters
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

public User(int userID, String username, String password, Role role, String studentID){
   this.userID = userID;
    this.username = username;
    this.password = password;
    this.role = role;
    this.studentID = studentID; }
}

    @Ignore
    public User (int userId, String username, String password, User.Role role ){
     this.userID = userID;
     this.username = username;
     this.password = password;
    this.role = role;
    this.studentID = null;
}


}
