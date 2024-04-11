package com.example.project_management_app.addActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.project_management_app.MainActivity;
import com.example.project_management_app.data.model.entities.Stage;
import com.example.project_management_app.data.model.viewModels.StageViewModel;
import com.example.project_management_app.databinding.ActivityAddProjectBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class addProjectActivity extends AppCompatActivity {

    private ActivityAddProjectBinding binding;
    StageViewModel stageViewModel;
    HashMap<String, Integer> stageMap = new HashMap<>();

    public static final String EXTRA_NAME =
            "com.example.project_management_app.EXTRA_NAME";
    public static final String EXTRA_DESC =
            "com.example.project_management_app.EXTRA_DESC";
    public static final String EXTRA_START =
            "com.example.project_management_app.EXTRA_START";
    public static final String EXTRA_END =
            "com.example.project_management_app.EXTRA_END";
    public static final String EXTRA_CLIENT =
            "com.example.project_management_app.EXTRA_CLIENT";
    public static final String EXTRA_STAGE =
            "com.example.project_management_app.EXTRA_STAGE";
    public static final String EXTRA_USER =
            "com.example.project_management_app.EXTRA_USER";
    public static final String EXTRA_STAGE_ID =
            "com.example.project_management_app.EXTRA_STAGE_ID";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddProjectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        stageViewModel = new ViewModelProvider(this).get(StageViewModel.class);

        final Button cancel_btn = binding.btnNo;
        final Button confirm_btn = binding.btnYes;
        final EditText projectName = (EditText) binding.projectName;
        final EditText projectDescription = (EditText) binding.projectDescription;
        final EditText projectStart = (EditText) binding.projectStart;
        final EditText projectEnd = (EditText) binding.projectEnd;
        final EditText projectClient = (EditText) binding.projectClient;
        final Spinner projectStage = binding.projectStage;

        assert cancel_btn != null;
        cancel_btn.setOnClickListener(v -> {
            Intent intent = new Intent(addProjectActivity.this, MainActivity.class);
            startActivity(intent);
        });

        stageViewModel.getAllStages().observe(this, stages -> {
            List<String> stageNames = new ArrayList<>();
            stageMap.clear(); // Очищаем карту перед заполнением

            for (Stage stage : stages) {
                String name = stage.getStageName();
                Integer id = stage.getStageID();
                stageNames.add(name);
                stageMap.put(name, id);
            }

            ArrayAdapter<String> adapter_stage = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, stageNames);
            adapter_stage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            projectStage.setAdapter(adapter_stage);
        });

        confirm_btn.setOnClickListener(view -> {
            String selectedStageName = (String) projectStage.getSelectedItem();
            Integer selectedStageId = stageMap.get(selectedStageName);
            saveProject(projectName, projectDescription, projectStart, projectEnd, projectClient, selectedStageId);
        });
    }

    private void saveProject(@NonNull EditText projectName, EditText projectDescription,
                             EditText projectStart, EditText projectEnd, EditText projectClient, int StageID){
        if(projectName.getText().toString().trim().isEmpty() ||
                projectDescription.getText().toString().trim().isEmpty() ||
                projectStart.getText().toString().trim().isEmpty() ||
                projectClient.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Пожалуйста введите данные", Toast.LENGTH_SHORT).show();
            return;
        }
        String name = projectName.getText().toString().trim();
        String desc = projectDescription.getText().toString().trim();
        String start = projectStart.getText().toString().trim();
        String end = projectEnd.getText().toString().trim();
        String client = projectClient.getText().toString().trim();
        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, name);
        data.putExtra(EXTRA_DESC, desc);
        data.putExtra(EXTRA_START, start);
        data.putExtra(EXTRA_END, end);
        data.putExtra(EXTRA_CLIENT, client);
        data.putExtra(EXTRA_STAGE_ID, StageID);
        setResult(RESULT_OK, data);
        finish();
    }
}