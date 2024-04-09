package com.example.project_management_app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_management_app.databinding.ActivityTasksBinding;

public class TasksActivity extends AppCompatActivity {
    private ActivityTasksBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTasksBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}