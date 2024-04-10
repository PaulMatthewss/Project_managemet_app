package com.example.project_management_app.data.model.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.project_management_app.data.model.ProjectManagementDatabase;
import com.example.project_management_app.data.model.dao.ProjectDAO;
import com.example.project_management_app.data.model.dao.StageDAO;
import com.example.project_management_app.data.model.entities.Project;
import com.example.project_management_app.data.model.entities.Stage;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProjectRepository {
    private ProjectDAO projectDao;
    private LiveData<List<Project>> Projects;
    private ExecutorService executorService;

    // Конструктор репозитория
    public ProjectRepository(Application application) {
        ProjectManagementDatabase database = ProjectManagementDatabase.getDatabase(application);
        projectDao = database.projectDAO();
        Projects = projectDao.getAllProjects();
        executorService = Executors.newFixedThreadPool(3); // Создаем пул из 3 потоков
    }

    // Операция вставки проекта
    public void insert(Project project) {
        executorService.execute(() -> projectDao.insert(project));
    }

    // Операция обновления проекта
    public void update(Project project) {
        executorService.execute(() -> projectDao.update(project));
    }

    // Операция удаления проекта
    public void delete(Project project) {
        executorService.execute(() -> projectDao.delete(project));
    }

    // Получение всех проектов
    public LiveData<List<Project>> getAllProjects() {
        return Projects;
    }

    public LiveData<List<Project>> getAllProjectsForUser(String username) {
        return projectDao.getAllProjectsForUser(username);
    }

    // Закрытие ExecutorService
    public void close() {
        executorService.shutdown();
    }
}
