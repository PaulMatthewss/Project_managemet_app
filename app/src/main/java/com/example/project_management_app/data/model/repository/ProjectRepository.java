package com.example.project_management_app.data.model.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;

import com.example.project_management_app.data.model.ProjectManagementDatabase;
import com.example.project_management_app.data.model.dao.ProjectDAO;
import com.example.project_management_app.data.model.entities.Project;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProjectRepository {
    private ProjectDAO projectDao;
    private LiveData<List<Project>> allProjects;
    private ExecutorService executorService;

    public ProjectRepository(Application application) {
        ProjectManagementDatabase database = ProjectManagementDatabase.getDatabase(application);
        projectDao = database.projectDAO();
        allProjects = projectDao.getAllProjects();
        executorService = Executors.newFixedThreadPool(3); // Создаем пул из 3 потоков
    }
    public void insert(Project project) {
        executorService.execute(() -> projectDao.insert(project));
    }
    public void update(Project project) {
        executorService.execute(() -> projectDao.update(project));
    }
    public void delete(Project project) {
        executorService.execute(() -> projectDao.delete(project));
    }
    public LiveData<List<Project>> getAllProjects() {
        return allProjects;
    }

    public void close() {
        executorService.shutdown();
    }
}
