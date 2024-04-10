package com.example.project_management_app.addActivities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project_management_app.MainActivity;
import com.example.project_management_app.databinding.ActivityAddProjectBinding;

public class addProjectActivity extends AppCompatActivity {

    private ActivityAddProjectBinding binding;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddProjectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        projectViewModel = new ViewModelProvider(this).get(ProjectViewModel.class);

        final Button cancel_btn = binding.btnNo;
        final Button confirm_btn = binding.btnYes;
        final EditText projectName = (EditText) binding.projectName;
        final EditText projectDescription = (EditText) binding.projectDescription;
        final EditText projectStart = (EditText) binding.projectStart;
        final EditText projectEnd = (EditText) binding.projectEnd;
        final EditText projectClient = (EditText) binding.projectClient;

//        LoginRepository loginRepository = LoginRepository
//                .getInstance(new LoginDataSource(getApplicationContext()));
//        final LiveData<LoggedInUser> loggedInUserLiveData = loginRepository.getLoggedInUser();

//        loggedInUserLiveData.observe(this, loggedInUser -> {
//            if (loggedInUser != null) {
//                // Пользователь авторизован, можно передать данные в ViewModel
//                confirm_btn.setOnClickListener(v -> {
//                    // Создание объекта Project
//                    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
//                    String dateStartString = projectStart.getText().toString();
//                    String dateEndString = projectEnd.getText().toString();
//                    Date dateStart;
//                    Date dateEnd;
//                    try {
//                        dateStart = formatter.parse(dateStartString);
//                        dateEnd = formatter.parse(dateEndString);
//                    } catch (ParseException e) {
//                        throw new RuntimeException(e);
//                    }
//                    Project newProject = new Project(
//                            loggedInUser.getUserId(),
//                            1,
//                            projectName.getText().toString(),
//                            projectDescription.getText().toString(),
//                            dateToTimestamp(dateStart),
//                            dateToTimestamp(dateEnd),
//                            projectClient.getText().toString()
//                    );
//
//                    // Вызов метода ViewModel для добавления проекта
//                    projectViewModel.insert(newProject);
//                });
//            }
//        });

        confirm_btn.setOnClickListener(view -> saveProject(projectName, projectDescription,
                projectStart, projectEnd, projectClient));

        assert cancel_btn != null;
        cancel_btn.setOnClickListener(v -> {
            Intent intent = new Intent(addProjectActivity.this, MainActivity.class);
            startActivity(intent);
        });

    }

    private void saveProject(@NonNull EditText projectName, EditText projectDescription,
                             EditText projectStart, EditText projectEnd, EditText projectClient){
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
        setResult(RESULT_OK, data);
        finish();
    }
}