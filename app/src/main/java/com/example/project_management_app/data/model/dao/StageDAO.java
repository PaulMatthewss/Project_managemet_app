package com.example.project_management_app.data.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.project_management_app.data.model.entities.Stage;

@Dao
public interface StageDAO {
    @Insert
    void insert(Stage stage);

    @Update
    void update(Stage stage);

    @Delete
    void delete(Stage stage);
}
