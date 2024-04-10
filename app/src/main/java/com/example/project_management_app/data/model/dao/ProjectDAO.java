package com.example.project_management_app.data.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project_management_app.data.model.entities.Project;

import java.util.List;

@Dao
public interface ProjectDAO {
    @Insert
    void insert(Project project);
    @Update
    void update(Project project);
    @Delete
    void delete(Project project);
    @Query("SELECT * FROM Projects WHERE projectID = :projectID")
    Project getProjectById(int projectID);

    @Query("SELECT * FROM Projects")
    LiveData<List<Project>> getAllProjects();

    @Query("SELECT * FROM Projects WHERE userID = :username")
    LiveData<List<Project>> getAllProjectsForUser(String username);
}
