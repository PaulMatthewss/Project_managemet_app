package com.example.project_management_app.data.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project_management_app.data.model.entities.Student;

import java.util.List;

@Dao
public interface StudentDAO {
    @Insert
    void insert(Student student);

    @Update
    void update(Student student);

    @Delete
    void delete(Student student);

    @Query("SELECT * FROM Students")
    LiveData<List<Student>> getAllStudents();
}
