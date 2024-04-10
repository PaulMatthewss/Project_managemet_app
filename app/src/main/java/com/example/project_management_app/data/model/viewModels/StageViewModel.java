package com.example.project_management_app.data.model.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.project_management_app.data.model.entities.Stage;
import com.example.project_management_app.data.model.entities.Task;
import com.example.project_management_app.data.model.repository.StageRepository;
import com.example.project_management_app.data.model.repository.TaskRepository;

import java.util.List;

public class StageViewModel extends AndroidViewModel {
    private final StageRepository stageRepository;
    private final LiveData<List<Stage>> allStages;
    public StageViewModel(@NonNull Application application) {
        super(application);
        stageRepository = new StageRepository(application);
        allStages = stageRepository.getAllStages();
    }
    public void insert(Stage task){
        stageRepository.insert(task);
    }
    public void update(Stage task){
        stageRepository.update(task);
    }
    public void delete(Stage task){
        stageRepository.delete(task);
    }
    public LiveData<List<Stage>> getAllStages(){
        return allStages;
    }
}
