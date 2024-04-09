package com.example.project_management_app.data.model.entities;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.project_management_app.BR;

@Entity(tableName = "Grades")
public class Grade extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    private int gradeID;
    @ColumnInfo(name = "GradeName")
    private String gradeName;

    public Grade(String gradeName) {
        this.gradeName = gradeName;
    }

    @Bindable
    public int getGradeID() { return gradeID; }
    public void setGradeID(int gradeID) {
        this.gradeID = gradeID;
        notifyPropertyChanged(BR.gradeID);
    }

    @Bindable
    public String getGradeName() { return gradeName; }
    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
        notifyPropertyChanged(BR.gradeName);
    }
}
