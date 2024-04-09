package com.example.project_management_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project_management_app.databinding.ActivityMainBinding;
import com.example.project_management_app.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final ImageButton img_projects_btn = binding.projects;
        final ImageButton img_allStudents_btn = binding.allStudents;
        final ImageButton img_profile_btn = binding.profile;

        img_projects_btn.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(intent);
        });

        img_allStudents_btn.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, AllStudentsActivity.class);
            startActivity(intent);
        });
    }
}