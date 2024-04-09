package com.example.project_management_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_management_app.addActivities.addProjectActivity;
import com.example.project_management_app.addActivities.addStudentActivity;
import com.example.project_management_app.databinding.ActivityAllStudentsBinding;
import com.example.project_management_app.databinding.ActivityStudentsBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AllStudentsActivity extends AppCompatActivity {
    private ActivityAllStudentsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllStudentsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final FloatingActionButton add_student = binding.addStudent;
        final ImageButton img_projects_btn = binding.projects;
        final ImageButton img_allStudents_btn = binding.allStudents;
        final ImageButton img_profile_btn = binding.profile;

        img_projects_btn.setOnClickListener(v -> {
            Intent intent = new Intent(AllStudentsActivity.this, MainActivity.class);
            startActivity(intent);
        });

        img_profile_btn.setOnClickListener(v -> {
            Intent intent = new Intent(AllStudentsActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

        add_student.setOnClickListener(v -> {
            Intent intent = new Intent(AllStudentsActivity.this, addStudentActivity.class);
            startActivity(intent);
        });
    }
}