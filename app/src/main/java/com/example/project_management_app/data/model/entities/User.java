package com.example.project_management_app.data.model.entities;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.project_management_app.BR;

@Entity(tableName = "Users")
public class User extends BaseObservable {
    @PrimaryKey
    @NonNull
    private String login;
    @ColumnInfo(name = "FirstName")
    private String firstName;
    @ColumnInfo(name = "LastName")
    private String lastName;
    @ColumnInfo(name = "Patronymic")
    private String patronymic;
    @ColumnInfo(name = "Password")
    private String password;

    public User(@NonNull String login, String firstName, String lastName, String patronymic, String password) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.password = password;
    }

    @Bindable
    public String getLogin() { return login; }
    public void setLogin(String login) {
        this.login = login;
        notifyPropertyChanged(BR.login);
    }

    @Bindable
    public String getFirstName() { return firstName; }
    public void setFirstname(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

    @Bindable
    public String getLastName() { return lastName; }
    public void setLastname(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(BR.lastName);
    }

    @Bindable
    public String getPatronymic() { return patronymic; }
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
        notifyPropertyChanged(BR.patronymic);
    }

    @Bindable
    public String getPassword() { return password; }
    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }
}
