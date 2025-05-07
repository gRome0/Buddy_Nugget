package com.example.myapplication;

import androidx.room.TypeConverter;
/*
takes care of enum conversion storing the role of user in table
Author: @Gael
 */
public class RoleConverter {
    @TypeConverter
    public static User.Role fromString(String value)
    {return User.Role.valueOf(value); }

    @TypeConverter
    public static String fromRole(User.Role role)
    {return role.name();}

}
