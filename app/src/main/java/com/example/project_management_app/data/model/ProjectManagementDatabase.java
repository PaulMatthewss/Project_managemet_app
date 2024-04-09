package com.example.project_management_app.data.model;

import static com.example.project_management_app.data.PassUtils.hashPassword;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.project_management_app.data.model.dao.*;
import com.example.project_management_app.data.model.entities.*;

@Database(entities = {Stage.class, Grade.class, Student.class, User.class,
        Project.class, StudentProject.class, Task.class},
        version = 2)
@TypeConverters({DateConverter.class})
public abstract class ProjectManagementDatabase extends RoomDatabase {
    public abstract StudentProjectDAO studentProjectDAO();
    public abstract StageDAO stageDAO();
    public abstract GradeDAO gradeDAO();
    public abstract StudentDAO studentDAO();
    public abstract UserDAO userDAO();
    public abstract ProjectDAO projectDAO();
    public abstract TaskDAO taskDAO();

    private static volatile ProjectManagementDatabase INSTANCE;
    public static ProjectManagementDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ProjectManagementDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ProjectManagementDatabase.class, "project_management_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    //Методы для начального заполнения бд данными при первом создании(тест)
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new Thread(() -> InitializeData(INSTANCE)).start();
        }
    };

    private static void InitializeData(ProjectManagementDatabase db) {
        Log.d("init data","иницировано");
        StudentProjectDAO studentProjectDAO = db.studentProjectDAO();
        StageDAO stageDAO = db.stageDAO();
        GradeDAO gradeDAO = db.gradeDAO();
        StudentDAO studentDAO = db.studentDAO();
        UserDAO userDAO = db.userDAO();
        ProjectDAO projectDAO = db.projectDAO();
        TaskDAO taskDAO = db.taskDAO();
    }
}