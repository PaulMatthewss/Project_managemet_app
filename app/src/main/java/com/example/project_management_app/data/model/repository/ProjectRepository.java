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
    private final ProjectDAO projectDao;
    private final LiveData<List<Project>> allProjects;

    // Конструктор репозитория
    public ProjectRepository(Application application) {
        ProjectManagementDatabase database = ProjectManagementDatabase.getDatabase(application);
        projectDao = database.projectDAO();
        allProjects = projectDao.getAllProjects();
    }

    // Операция вставки проекта
    public void insert(Project project) {

    }

    // Операция обновления проекта
    public void update(Project project) {

    }

    // Операция удаления проекта
    public void delete(Project project) {

    }

    // Получение всех проектов
    public LiveData<List<Project>> getAllProjects() {
        return allProjects;
    }

    private static class InsertProjectAsyncTask extends AsyncTask<Project, Void, Void> {
        private final ProjectDAO projectDao;
        private  InsertProjectAsyncTask(ProjectDAO projectDao){
            this.projectDao = projectDao;
        }
        @Override
        protected Void doInBackground(Project... projects) {
            projectDao.insert(projects[0]);
            return null;
        }
    }
    private static class UpdateProjectAsyncTask extends AsyncTask<Project, Void, Void> {
        private final ProjectDAO projectDao;
        private  UpdateProjectAsyncTask(ProjectDAO projectDao){
            this.projectDao = projectDao;
        }
        @Override
        protected Void doInBackground(Project... projects) {
            projectDao.update(projects[0]);
            return null;
        }
    }
    private static class DeleteProjectAsyncTask extends AsyncTask<Project, Void, Void> {
        private final ProjectDAO projectDao;
        private  DeleteProjectAsyncTask(ProjectDAO projectDao){
            this.projectDao = projectDao;
        }
        @Override
        protected Void doInBackground(Project... projects) {
            projectDao.delete(projects[0]);
            return null;
        }
    }
}
