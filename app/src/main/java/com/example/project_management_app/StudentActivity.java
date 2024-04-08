package com.example.project_management_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_management_app.addActivities.addProjectActivity;
import com.example.project_management_app.databinding.ActivityMainBinding;
import com.example.project_management_app.databinding.ActivityStudentsBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class StudentActivity extends AppCompatActivity {
    private ActivityStudentsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
