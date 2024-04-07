package com.example.project_management_app.data.model.entities;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.project_management_app.BR;

@Entity(tableName = "Tasks",
        foreignKeys = {
                @ForeignKey(entity = StudentProject.class,
                        parentColumns = "studentProjectID",
                        childColumns = "studentProjectID",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Grade.class,
                        parentColumns = "gradeID",
                        childColumns = "gradeID",
                        onDelete = ForeignKey.CASCADE)
        })
public class Task extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    public int taskID;
    @ColumnInfo(index = true)
    public int studentProjectID;
    @ColumnInfo(index = true)
    public int gradeID;
    public String taskName;
    public String description;
    public String status;
    public String note;
    public long startDate;
    public long endDate;

    public Task(int studentProjectID, int gradeID, String taskName,
                String description, String status, String note, long startDate, long endDate) {
        this.studentProjectID = studentProjectID;
        this.gradeID = gradeID;
        this.taskName = taskName;
        this.description = description;
        this.status = status;
        this.note = note;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Bindable
    public int getTaskID() { return taskID; }
    public void setTaskID(int taskID) {
        this.taskID = taskID;
        notifyPropertyChanged(BR.taskID);
    }

    @Bindable
    public int getStudentProjectID() { return studentProjectID; }
    public void setStudentProjectID(int studentProjectID) {
        this.studentProjectID = studentProjectID;
        notifyPropertyChanged(BR.studentProjectID);
    }

    @Bindable
    public int getGradeID() { return gradeID; }
    public void setGradeID(int gradeID) {
        this.gradeID = gradeID;
        notifyPropertyChanged(BR.gradeID);
    }

    @Bindable
    public String getTaskName() { return taskName; }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
        notifyPropertyChanged(BR.taskName);
    }

    @Bindable
    public String getDescription() { return description; }
    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }

    @Bindable
    public long getStartDate() { return startDate; }
    public void setStartDate(long startDate) {
        this.startDate = startDate;
        notifyPropertyChanged(BR.startDate);
    }

    @Bindable
    public long getEndDate() { return endDate; }
    public void setEndDate(long endDate) {
        this.endDate = endDate;
        notifyPropertyChanged(BR.endDate);
    }

    @Bindable
    public String getStatus() { return status; }
    public void setStatus(String status) {
        this.status = status;
        notifyPropertyChanged(BR.status);
    }

    @Bindable
    public String getNote() {return note;}
    public void setNote(String note) {
        this.note = note;
        notifyPropertyChanged(BR.note);
    }
}