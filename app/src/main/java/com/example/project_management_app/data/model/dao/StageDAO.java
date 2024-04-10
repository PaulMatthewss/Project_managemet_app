package com.example.project_management_app.data.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project_management_app.data.model.entities.Stage;

import java.util.List;

@Dao
public interface StageDAO {
    @Insert
    void insert(Stage stage);

    @Update
    void update(Stage stage);

    @Delete
    void delete(Stage stage);

    @Query("SELECT * FROM Stages")
    LiveData<List<Stage>> getAllStages();
}
