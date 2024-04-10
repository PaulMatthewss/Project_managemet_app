package com.example.project_management_app.addActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project_management_app.MainActivity;
import com.example.project_management_app.R;
import com.example.project_management_app.StudentActivity;
import com.example.project_management_app.data.model.repository.StageRepository;
import com.example.project_management_app.data.model.viewModels.StageViewModel;
import com.example.project_management_app.databinding.ActivityAddProjectBinding;
import com.example.project_management_app.databinding.ActivityMainBinding;

import java.util.Objects;

public class addProjectActivity extends AppCompatActivity {

    private ActivityAddProjectBinding binding;

    StageViewModel stageViewModel;

    String[] stages_array = {"Стадия 1", "Стадия 2", "Стадия 3"};
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddProjectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final Button cancel_btn = binding.btnNo;
        final Button add_btn = binding.btnYes;
        final EditText project_name = (EditText) binding.projectName;
        final EditText project_desc = (EditText) binding.projectDescription;
        final EditText project_start = (EditText) binding.projectStart;
        final EditText project_end = (EditText) binding.projectEnd;
        final EditText project_client = (EditText) binding.projectClient;
        final Spinner project_stage = binding.projectStage;

        assert cancel_btn != null;
        cancel_btn.setOnClickListener(v -> {
            Intent intent = new Intent(addProjectActivity.this, MainActivity.class);
            startActivity(intent);
        });

        /*add_btn.setOnClickListener(view -> saveProject(project_name, project_desc,
                project_start, project_end, project_client, project_stage));*/

        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter_stage = new ArrayAdapter(this, android.R.layout.simple_spinner_item, stages_array);
        // Определяем разметку для использования при выборе элемента
        adapter_stage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        project_stage.setAdapter(adapter_stage);
    }
    private void saveProject(@NonNull EditText project_name, EditText project_desc,
                             EditText project_start, EditText project_end, EditText project_client,
                             Spinner project_stage){
        if(project_name.getText().toString().trim().isEmpty() ||
                project_desc.getText().toString().trim().isEmpty() ||
                project_start.getText().toString().trim().isEmpty() ||
                project_end.getText().toString().trim().isEmpty() ||
                project_client.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Пожалуйста введите данные", Toast.LENGTH_SHORT).show();
            return;
        }
        String name = project_name.getText().toString().trim();
        String desc = project_desc.getText().toString().trim();
        String start = project_start.getText().toString().trim();
        String end = project_end.getText().toString().trim();
        String client = project_client.getText().toString().trim();
        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, name);
        data.putExtra(EXTRA_DESC, desc);
        data.putExtra(EXTRA_START, start);
        data.putExtra(EXTRA_END, end);
        data.putExtra(EXTRA_CLIENT, client);
        setResult(RESULT_OK, data);
        finish();
    }
}