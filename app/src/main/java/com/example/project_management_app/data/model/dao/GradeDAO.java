package com.example.project_management_app.data.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project_management_app.data.model.entities.Grade;

@Dao
public interface GradeDAO {
    @Insert
    void insert(Grade grade);
    @Update
    void update(Grade grade);
    @Delete
    void delete(Grade grade);
}
