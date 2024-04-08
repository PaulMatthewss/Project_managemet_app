package com.example.project_management_app.addActivities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project_management_app.AllStudentsActivity;
import com.example.project_management_app.MainActivity;
import com.example.project_management_app.R;
import com.example.project_management_app.databinding.ActivityAddProjectBinding;
import com.example.project_management_app.databinding.ActivityAddStudentBinding;

public class addStudentActivity extends AppCompatActivity {

    private ActivityAddStudentBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddStudentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final Button cancel_btn = binding.btnNo;

        cancel_btn.setOnClickListener(v -> {
            Intent intent = new Intent(addStudentActivity.this, AllStudentsActivity.class);
            startActivity(intent);
        });

        /*EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_student);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
    }
}