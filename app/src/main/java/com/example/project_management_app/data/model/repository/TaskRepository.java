package com.example.project_management_app.data.model.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;

import com.example.project_management_app.data.model.ProjectManagementDatabase;
import com.example.project_management_app.data.model.dao.TaskDAO;
import com.example.project_management_app.data.model.entities.Task;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskRepository {
    private TaskDAO taskDao;
    private LiveData<List<Task>> allTasks;
    private ExecutorService executorService;

    // Конструктор репозитория
    public TaskRepository(Application application) {
        ProjectManagementDatabase database = ProjectManagementDatabase.getDatabase(application);
        taskDao = database.taskDAO();
        allTasks = taskDao.getAllTasks();
        executorService = Executors.newFixedThreadPool(3); // Создаем пул из 3 потоков
    }

    // Операция вставки задачи
    public void insert(Task task) {
        executorService.execute(() -> taskDao.insert(task));
    }

    // Операция обновления задачи
    public void update(Task task) {
        executorService.execute(() -> taskDao.update(task));
    }

    // Операция удаления задачи
    public void delete(Task task) {
        executorService.execute(() -> taskDao.delete(task));
    }

    // Получение всех задач
    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    // Закрытие ExecutorService
    public void close() {
        executorService.shutdown();
    }
}
