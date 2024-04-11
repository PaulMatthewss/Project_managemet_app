package com.example.project_management_app;

import static com.example.project_management_app.data.model.DateConverter.dateToTimestamp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.project_management_app.addActivities.addProjectActivity;
import com.example.project_management_app.data.LoginDataSource;
import com.example.project_management_app.data.LoginRepository;
import com.example.project_management_app.data.model.LoggedInUser;
import com.example.project_management_app.data.model.adapters.ProjectPowAdapter;
import com.example.project_management_app.data.model.entities.Project;
import com.example.project_management_app.data.model.viewModels.ProjectViewModel;
import com.example.project_management_app.databinding.ActivityLoginBinding;
import com.example.project_management_app.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String PROJECT_TO_PARSE =
            "com.example.project_management_app.PROJECT_TO_PARSE";
    private ActivityMainBinding binding;
    RecyclerView recyclerView_projects;
    ProjectPowAdapter projectRowAdapter;
    private ProjectViewModel projectViewModel;
    public static final int ADD_PROJECT_REQUEST = 1;
    public static String LOGGED_IN_USER;

    ActivityResultLauncher<Intent> addProjectResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                if (data != null) {
                    Project project;
                    try {
                        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
                        String dateStartString = data.getStringExtra(addProjectActivity.EXTRA_START);
                        String dateEndString = data.getStringExtra(addProjectActivity.EXTRA_END);
                        Date dateStart = formatter.parse(dateStartString);
                        Date dateEnd = formatter.parse(dateEndString);
                        String name = data.getStringExtra(addProjectActivity.EXTRA_NAME);
                        String desc = data.getStringExtra(addProjectActivity.EXTRA_DESC);
                        Integer stage = data.getIntExtra(addProjectActivity.EXTRA_STAGE_ID, 1);
                        long start = dateToTimestamp(dateStart);
                        long end = dateToTimestamp(dateEnd);
                        String client = data.getStringExtra(addProjectActivity.EXTRA_CLIENT);
                        project = new Project(LOGGED_IN_USER, stage, name, desc, start, end, client);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    projectViewModel.insert(project);
                    recreate();
                    Toast.makeText(this, "Запись добавлена", Toast.LENGTH_SHORT).show();
                }
                } else {
                    Toast.makeText(this, "Ошибка записи", Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final FloatingActionButton add_project = binding.addProject;
        final RecyclerView recyclerView = binding.recyclerView;
        final ImageButton img_projects_btn = binding.projects;
        final ImageButton img_allStudents_btn = binding.allStudents;
        final ImageButton img_profile_btn = binding.profile;

        LoginRepository loginRepository = LoginRepository
                .getInstance(new LoginDataSource(getApplicationContext()));
        final LiveData<LoggedInUser> loggedInUserLiveData = loginRepository.getLoggedInUser();

        loggedInUserLiveData.observe(this, loggedInUser -> {
            if (loggedInUser != null) {
                LOGGED_IN_USER = loggedInUser.getUserId();
            }
        });

        img_allStudents_btn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AllStudentsActivity.class);
            startActivity(intent);
        });

        img_profile_btn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

        add_project.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, addProjectActivity.class);
            addProjectResultLauncher.launch(intent);
        });

        recyclerView_projects = binding.recyclerView;
        recyclerView_projects.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView_projects.setHasFixedSize(true);
        projectRowAdapter = new ProjectPowAdapter();
        recyclerView_projects.setAdapter(projectRowAdapter);
        projectViewModel = new ViewModelProvider(this).get(ProjectViewModel.class);
        projectViewModel.getAllProjectsForUser(LOGGED_IN_USER).observe(this, projects -> projectRowAdapter.setProjects(projects));

        projectRowAdapter.setOnItemClickListener(new ProjectPowAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Project project) {
                Intent intent = new Intent(MainActivity.this, StudentActivity.class);
                intent.putExtra(PROJECT_TO_PARSE, project.getProjectID());
                startActivity(intent);
            }

            @Override
            public void onEditClick(Project project) {
                // Здесь код для обработки нажатия на кнопку редактирования
                // Например, открыть активность редактирования с передачей ID проекта
            }

            @Override
            public void onDeleteClick(Project project) {
                projectViewModel.delete(project);
            }
        });

    }
}