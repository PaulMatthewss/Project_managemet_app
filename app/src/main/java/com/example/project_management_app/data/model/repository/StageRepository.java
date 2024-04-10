package com.example.project_management_app.data.model.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.project_management_app.data.model.ProjectManagementDatabase;
import com.example.project_management_app.data.model.dao.StageDAO;
import com.example.project_management_app.data.model.entities.Project;
import com.example.project_management_app.data.model.entities.Stage;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class StageRepository {
    private final StageDAO stageDao;
    private final LiveData<List<Stage>> allStages;
    public StageRepository(Application application) {
        ProjectManagementDatabase database = ProjectManagementDatabase.getDatabase(application);
        stageDao = database.stageDAO();
        allStages = stageDao.getAllStages();
    }

    public LiveData<List<Stage>> getAllStages() {
        return allStages;
    }

    public void insert(Stage stage) {
        new InsertStageAsyncTask(stageDao).execute(stage);
    }

    public void update(Stage stage) {
        new UpdateStageAsyncTask(stageDao).execute(stage);
    }

    public void delete(Stage stage) {
        new DeleteStageAsyncTask(stageDao).execute(stage);
    }
    private static class InsertStageAsyncTask extends AsyncTask<Stage, Void, Void> {
        private final StageDAO stageDao;
        private  InsertStageAsyncTask(StageDAO stageDao){
            this.stageDao = stageDao;
        }
        @Override
        protected Void doInBackground(Stage... stages) {
            stageDao.insert(stages[0]);
            return null;
        }
    }
    private static class UpdateStageAsyncTask extends AsyncTask<Stage, Void, Void> {
        private final StageDAO stageDao;
        private  UpdateStageAsyncTask(StageDAO stageDao){
            this.stageDao = stageDao;
        }
        @Override
        protected Void doInBackground(Stage... stages) {
            stageDao.update(stages[0]);
            return null;
        }
    }
    private static class DeleteStageAsyncTask extends AsyncTask<Stage, Void, Void> {
        private final StageDAO stageDao;
        private  DeleteStageAsyncTask(StageDAO stageDao){
            this.stageDao = stageDao;
        }
        @Override
        protected Void doInBackground(Stage... stages) {
            stageDao.delete(stages[0]);
            return null;
        }
    }
}
