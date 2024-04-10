package com.example.project_management_app.data.model.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.project_management_app.data.model.entities.Project;
import com.example.project_management_app.data.model.repository.ProjectRepository;

import java.util.List;

public class ProjectViewModel extends AndroidViewModel {
    private final ProjectRepository projectRepository;
    private final LiveData<List<Project>> allProjects;
    public ProjectViewModel(@NonNull Application application) {
        super(application);
        projectRepository = new ProjectRepository(application);
        allProjects = projectRepository.getAllProjects();
    }
    public void insert(Project project){
        projectRepository.insert(project);
    }
    public void update(Project project){
        projectRepository.update(project);
    }
    public void delete(Project project){
        projectRepository.delete(project);
    }
    public LiveData<List<Project>> getAllProjects(){
        return allProjects;
    }
}
