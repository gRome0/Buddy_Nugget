package com.example.myapplication;

public class RoleConverter {
    @TypeConverter
    public static User.Role fromString(String value){
        return User.Role.valueOf(value);
    }

    @TypeConverter
    public static String fromRole(User.Role role){
      return role.name();
    }

}
