package com.example.project_management_app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_management_app.databinding.ActivityAllTasksBinding;

public class AllTasksActivity extends AppCompatActivity {
    private ActivityAllTasksBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllTasksBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
