package com.example.project_management_app;

import androidx.appcompat.app.AppCompatActivity;
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
import com.example.project_management_app.databinding.ActivityLoginBinding;
import com.example.project_management_app.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

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

        add_project.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, addProjectActivity.class);
            startActivity(intent);
        });

        recyclerView.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, StudentActivity.class);
            startActivity(intent);
        });

        /*assert menu != null;
        menu.setOnClickListener(v -> {
            String text = String.valueOf(menu.getId());
            Toast.makeText(this,text,Toast.LENGTH_LONG).show();
            Log.i("Navbar Click",text);
        });*/
    }
}