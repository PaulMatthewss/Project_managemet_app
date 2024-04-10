package com.example.project_management_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.project_management_app.addActivities.addProjectActivity;
import com.example.project_management_app.data.model.adapters.ProjectPowAdapter;
import com.example.project_management_app.data.model.entities.Project;
import com.example.project_management_app.data.model.viewModels.ProjectViewModel;
import com.example.project_management_app.databinding.ActivityLoginBinding;
import com.example.project_management_app.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String PROJECT_TO_PARSE =
            "com.example.project_management_app.PROJECT_TO_PARSE";
    private ActivityMainBinding binding;
    RecyclerView recyclerView_projects;
    ProjectPowAdapter projectRowAdapter;
    private ProjectViewModel projectViewModel;
    public static final int ADD_PROJECT_REQUEST = 1;

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
        //final BottomNavigationView menu = binding.appMenu;


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
            startActivityForResult(intent, ADD_PROJECT_REQUEST);
        });
        /*
        recyclerView_projects.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView_projects.setHasFixedSize(true);
        projectRowAdapter = new ProjectPowAdapter();
        recyclerView_projects.setAdapter(projectRowAdapter);
        projectViewModel = new ViewModelProvider(this).get(ProjectViewModel.class);
        projectViewModel.getAllProjects().observe(this, projects -> projectRowAdapter.setProjects(projects));
        projectRowAdapter.setOnItemClickListener(project -> {
            Intent intent = new Intent(MainActivity.this, StudentActivity.class);
            intent.putExtra(PROJECT_TO_PARSE, project.getProjectID());
            startActivity(intent);
        });*/
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_PROJECT_REQUEST && resultCode == RESULT_OK && data != null){
            String name = data.getStringExtra(addProjectActivity.EXTRA_NAME);
            String desc = data.getStringExtra(addProjectActivity.EXTRA_DESC);
            Long start = Long.valueOf(data.getStringExtra(addProjectActivity.EXTRA_START));
            Long end = Long.valueOf(data.getStringExtra(addProjectActivity.EXTRA_END));
            String client = data.getStringExtra(addProjectActivity.EXTRA_CLIENT);
            Project project = new Project("pupkin@mail.ru",1, name, desc, start, end, client);
            projectViewModel.insert(project);
            recreate();
            Toast.makeText(this, "Запись добавлена", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Ошибка записи", Toast.LENGTH_SHORT).show();

        }
    }
}