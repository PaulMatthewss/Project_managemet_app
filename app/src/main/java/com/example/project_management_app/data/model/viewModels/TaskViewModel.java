package com.example.project_management_app.data.model.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.project_management_app.data.model.entities.Project;
import com.example.project_management_app.data.model.entities.Task;
import com.example.project_management_app.data.model.repository.ProjectRepository;
import com.example.project_management_app.data.model.repository.TaskRepository;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private final TaskRepository taskRepository;
    private final LiveData<List<Task>> allTasks;
    public TaskViewModel(@NonNull Application application) {
        super(application);
        taskRepository = new TaskRepository(application);
        allTasks = taskRepository.getAllTasks();
    }
    public void insert(Task task){
        taskRepository.insert(task);
    }
    public void update(Task task){
        taskRepository.update(task);
    }
    public void delete(Task task){
        taskRepository.delete(task);
    }
    public LiveData<List<Task>> getAllTasks(){
        return allTasks;
    }
}
