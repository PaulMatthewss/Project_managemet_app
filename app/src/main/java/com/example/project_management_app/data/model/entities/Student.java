package com.example.project_management_app.data.model.entities;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.Entity;

import com.example.project_management_app.BR;

@Entity(tableName = "Students")
public class Student extends BaseObservable {
    private int studNum;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String studGroup;

    public Student(int studNum, String firstName, String lastName,
                   String patronymic, String studGroup) {
        this.studNum = studNum;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.studGroup = studGroup;
    }

    @Bindable
    public int getStudNum() { return studNum; }
    public void setStudNum(int studNum) {
        this.studNum = studNum;
        notifyPropertyChanged(BR.studNum);
    }

    @Bindable
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

    @Bindable
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) {
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
    public String getStudGroup() { return studGroup; }
    public void setStudGroup(String studGroup) {
        this.studGroup = studGroup;
        notifyPropertyChanged(BR.studGroup);
    }
}
