package com.example.project_management_app.data.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project_management_app.data.model.entities.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    void insert(User user);
    @Update
    void update(User user);
    @Delete
    void delete(User user);

    @Query("SELECT * FROM Users")
    LiveData<List<User>> getAllUsers();
    @Query("SELECT * FROM Users WHERE login = :username")
    User findUserByUsername(String username);
}
