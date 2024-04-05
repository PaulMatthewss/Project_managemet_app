package com.example.project_management_app.data.model.entities;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.project_management_app.BR;

@Entity(tableName = "StudentProject",
        foreignKeys = {
                @ForeignKey(entity = Student.class,
                        parentColumns = "studNum",
                        childColumns = "StudentID",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Project.class,
                        parentColumns = "projectID",
                        childColumns = "ProjectID",
                        onDelete = ForeignKey.CASCADE)
        })
public class StudentProject extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    public int studentProjectID;
    @ColumnInfo(index = true)
    public int studentID;
    @ColumnInfo(index = true)
    public int projectID;

    public StudentProject(int studentID, int projectID) {
        this.studentID = studentID;
        this.projectID = projectID;
    }

    @Bindable
    public int getStudentProjectID() { return studentProjectID; }
    public void setStudentProjectID(int studentProjectID) {
        this.studentProjectID = studentProjectID;
        notifyPropertyChanged(BR.studentProjectID);
    }

    @Bindable
    public int getStudentID() { return studentID; }
    public void setStudentID(int studentID) {
        this.studentID = studentID;
        notifyPropertyChanged(BR.studentID);
    }

    @Bindable
    public int getProjectID() { return projectID; }
    public void setProjectID(int projectID) {
        this.projectID = projectID;
        notifyPropertyChanged(BR.projectID);
    }
}
