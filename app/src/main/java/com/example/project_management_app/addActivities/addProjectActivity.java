package com.example.project_management_app.addActivities;

import static com.example.project_management_app.data.model.DateConverter.dateToTimestamp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.project_management_app.MainActivity;
import com.example.project_management_app.ProjectViewModel;
import com.example.project_management_app.R;
import com.example.project_management_app.StudentActivity;
import com.example.project_management_app.data.LoginDataSource;
import com.example.project_management_app.data.LoginRepository;
import com.example.project_management_app.data.model.LoggedInUser;
import com.example.project_management_app.data.model.entities.Project;
import com.example.project_management_app.databinding.ActivityAddProjectBinding;
import com.example.project_management_app.data.model.DateConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class addProjectActivity extends AppCompatActivity {

    private ActivityAddProjectBinding binding;
    private ProjectViewModel projectViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddProjectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        projectViewModel = new ViewModelProvider(this).get(ProjectViewModel.class);

        final Button cancel_btn = binding.btnNo;
        final Button confirm_btn = binding.btnYes;
        final EditText projectName = (EditText) binding.projectName;
        final EditText projectDescription = (EditText) binding.projectDescription;
        final EditText projectStart = (EditText) binding.projectStart;
        final EditText projectEnd = (EditText) binding.projectEnd;
        final EditText projectClient = (EditText) binding.projectClient;

        LoginRepository loginRepository = LoginRepository
                .getInstance(new LoginDataSource(getApplicationContext()));
        final LiveData<LoggedInUser> loggedInUserLiveData = loginRepository.getLoggedInUser();

        loggedInUserLiveData.observe(this, loggedInUser -> {
            if (loggedInUser != null) {
                // Пользователь авторизован, можно передать данные в ViewModel
                confirm_btn.setOnClickListener(v -> {
                    // Создание объекта Project
                    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
                    String dateStartString = projectStart.getText().toString();
                    String dateEndString = projectEnd.getText().toString();
                    Date dateStart;
                    Date dateEnd;
                    try {
                        dateStart = formatter.parse(dateStartString);
                        dateEnd = formatter.parse(dateEndString);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    Project newProject = new Project(
                            loggedInUser.getUserId(),
                            1,
                            projectName.getText().toString(),
                            projectDescription.getText().toString(),
                            dateToTimestamp(dateStart),
                            dateToTimestamp(dateEnd),
                            projectClient.getText().toString()
                    );

                    // Вызов метода ViewModel для добавления проекта
                    projectViewModel.insert(newProject);
                });
            }
        });

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