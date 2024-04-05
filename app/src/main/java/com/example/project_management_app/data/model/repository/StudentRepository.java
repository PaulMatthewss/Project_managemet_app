package com.example.project_management_app.data.model.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;

import com.example.project_management_app.data.model.ProjectManagementDatabase;
import com.example.project_management_app.data.model.dao.StudentDAO;
import com.example.project_management_app.data.model.entities.Student;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StudentRepository {
    private StudentDAO studentDao;
    private LiveData<List<Student>> allStudents;
    private ExecutorService executorService;

    // Конструктор репозитория
    public StudentRepository(Application application) {
        ProjectManagementDatabase database = ProjectManagementDatabase.getDatabase(application);
        studentDao = database.studentDAO();
        allStudents = studentDao.getAllStudents();
        executorService = Executors.newFixedThreadPool(3); // Создаем пул из 3 потоков
    }

    // Операция вставки студента
    public void insert(Student student) {
        executorService.execute(() -> studentDao.insert(student));
    }

    // Операция обновления студента
    public void update(Student student) {
        executorService.execute(() -> studentDao.update(student));
    }

    // Операция удаления студента
    public void delete(Student student) {
        executorService.execute(() -> studentDao.delete(student));
    }

    // Получение всех студентов
    public LiveData<List<Student>> getAllStudents() {
        return allStudents;
    }

    // Закрытие ExecutorService
    public void close() {
        executorService.shutdown();
    }
}
