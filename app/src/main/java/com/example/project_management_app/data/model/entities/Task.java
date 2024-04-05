package com.example.project_management_app.data.model.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Tasks",
        foreignKeys = {
                @ForeignKey(entity = StudentProject.class,
                        parentColumns = "studentProjectID",
                        childColumns = "StudentProjectID",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Grade.class,
                        parentColumns = "gradeID",
                        childColumns = "GradeID",
                        onDelete = ForeignKey.CASCADE)
        })
public class Task {
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
}