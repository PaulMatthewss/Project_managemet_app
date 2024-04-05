package com.example.project_management_app.data.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project_management_app.data.model.entities.StudentProject;

@Dao
public interface StudentProjectDAO {
    @Insert
    void insert(StudentProject studentProject);

    @Update
    void update(StudentProject studentProject);

    @Delete
    void delete(StudentProject studentProject);

    @Query("SELECT * FROM StudentProject WHERE studentProjectID = :studentProjectID")
    StudentProject getStudentProjectById(int studentProjectID);

}