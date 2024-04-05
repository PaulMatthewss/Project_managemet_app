package com.example.project_management_app.data.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project_management_app.data.model.entities.Task;

import java.util.List;

@Dao
public interface TaskDAO {
    @Insert
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

    @Query("SELECT * FROM Tasks WHERE taskID = :taskID")
    Task getTaskById(int taskID);

    @Query("SELECT * FROM Tasks")
    LiveData<List<Task>> getAllTasks();
}