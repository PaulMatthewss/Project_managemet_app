package com.example.project_management_app.data.model.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;

import com.example.project_management_app.data.model.ProjectManagementDatabase;
import com.example.project_management_app.data.model.dao.UserDAO;
import com.example.project_management_app.data.model.entities.User;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserRepository {
    private UserDAO userDao;
    private LiveData<List<User>> allUsers;
    private LiveData<User> user;
    private ExecutorService executorService;

    // Конструктор репозитория
    public UserRepository(Application application) {
        ProjectManagementDatabase database = ProjectManagementDatabase.getDatabase(application);
        userDao = database.userDAO();
        allUsers = userDao.getAllUsers();
        executorService = Executors.newFixedThreadPool(3); // Создаем пул из 3 потоков
    }

    // Операция вставки пользователя
    public void insert(User user) {
        executorService.execute(() -> userDao.insert(user));
    }

    // Операция обновления пользователя
    public void update(User user) {
        executorService.execute(() -> userDao.update(user));
    }

    // Операция удаления пользователя
    public void delete(User user) {
        executorService.execute(() -> userDao.delete(user));
    }

    // Получение всех пользователей
    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }
    public LiveData<User> findUserByUsername() { return user; }

    // Закрытие ExecutorService
    public void close() {
        executorService.shutdown();
    }
}
