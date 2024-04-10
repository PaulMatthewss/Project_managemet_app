package com.example.project_management_app.data.model.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.project_management_app.data.model.entities.Student;
import com.example.project_management_app.data.model.repository.StudentRepository;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {
    private final StudentRepository studentRepository;
    private final LiveData<List<Student>> allStudents;
    public StudentViewModel(@NonNull Application application) {
        super(application);
        studentRepository = new StudentRepository(application);
        allStudents = studentRepository.getAllStudents();
    }
    public void insert(Student student){
        studentRepository.insert(student);
    }
    public void update(Student student){
        studentRepository.update(student);
    }
    public void delete(Student student){
        studentRepository.delete(student);
    }
    public LiveData<List<Student>> getAllStudents(){
        return allStudents;
    }
}
