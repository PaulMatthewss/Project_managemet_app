package com.example.project_management_app.addActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project_management_app.MainActivity;
import com.example.project_management_app.R;
import com.example.project_management_app.StudentActivity;
import com.example.project_management_app.databinding.ActivityAddProjectBinding;
import com.example.project_management_app.databinding.ActivityMainBinding;

public class addProjectActivity extends AppCompatActivity {

    private ActivityAddProjectBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddProjectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final Button cancel_btn = binding.btnNo;

        assert cancel_btn != null;
        cancel_btn.setOnClickListener(v -> {
            Intent intent = new Intent(addProjectActivity.this, MainActivity.class);
            startActivity(intent);
        });
        /*EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_project);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
    }
}