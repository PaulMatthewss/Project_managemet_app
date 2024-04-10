package com.example.project_management_app.data;

import androidx.core.util.Consumer;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.project_management_app.data.model.LoggedInUser;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;
    private LoginDataSource dataSource;
    private LoggedInUser user = null;

    private final MutableLiveData<LoggedInUser> loggedInUserLiveData = new MutableLiveData<>();

    // private constructor : singleton access
    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if(instance == null){
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
        dataSource.logout();
    }

    public LiveData<LoggedInUser> getLoggedInUser() {
        return loggedInUserLiveData;
    }

    private void setLoggedInUser(LoggedInUser user) {
        this.user = user;
        loggedInUserLiveData.postValue(user);
    }

    public void login(String username, String password, Consumer<Result<LoggedInUser>> onResult) {
        dataSource.login(username, password, result -> {
            if (result instanceof Result.Success) {
                setLoggedInUser(((Result.Success<LoggedInUser>) result).getData());
                onResult.accept(result);
            } else {
                onResult.accept(result);
            }
        });
    }
}