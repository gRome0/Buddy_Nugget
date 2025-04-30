package com.example.myapplication;

import android.content.Context;

@Database(entities) = {User.class, Assignment.class, Grade.class}, version = 3;
@TypeConverters({RoleConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;
    // TODO: HAVE TO MAKE userDao
    public abstract UserDao userDao();

    // TODO: HAVE TO MAKE assignmentDao
    public abstract AssignmentDao assignmentDao();

    // TODO: HAVE TO MAKE gradeDao
    public abstract GradeDao gradeDao();


    public static synchronized AppDatabase getInstance(Context context){
        if (instance == null){
        instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "grade_tracker_db")
                .fallbackToDestructiveMigraion()
                .allowMainThreadQueries()
                .build();
        }
    return instance;
    }
}
