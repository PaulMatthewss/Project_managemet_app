package com.example.project_management_app.data;

import static com.example.project_management_app.data.PassUtils.hashPassword;

import android.content.Context;

import androidx.core.util.Consumer;

import com.example.project_management_app.data.model.LoggedInUser;
import com.example.project_management_app.data.model.ProjectManagementDatabase;
import com.example.project_management_app.data.model.dao.UserDAO;
import com.example.project_management_app.data.model.entities.User;
import com.example.project_management_app.ui.login.LoginResult;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */

public class LoginDataSource {

    private ExecutorService executorService;
    private Context context;
    public LoginDataSource(Context context) {
        this.context = context;
        executorService = Executors.newFixedThreadPool(2);
    }

    public void login(String username, String password, Consumer<Result<LoggedInUser>> onResult) {
        executorService.execute(() -> {
            try {
                UserDAO userDAO = ProjectManagementDatabase.getDatabase(context).userDAO();
                User user = userDAO.findUserByUsername(username);
                if (user != null && user.getPassword().equals(hashPassword(password))) {
                    LoggedInUser loggedInUser = new LoggedInUser(user.getLogin(), user.getFirstName());
                    onResult.accept(new Result.Success<>(loggedInUser));
                } else {
                    onResult.accept(new Result.Error(new Exception("Login failed")));
                }
            } catch (Exception e) {
                onResult.accept(new Result.Error(e));
            }
        });
    }

    public void logout() {
        // TODO: revoke authentication
    }
}