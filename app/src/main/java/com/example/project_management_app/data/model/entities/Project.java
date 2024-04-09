package com.example.project_management_app.data.model.entities;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.project_management_app.BR;

@Entity(tableName = "Projects",
        foreignKeys = {
            @ForeignKey(entity = User.class,
                parentColumns = "login",
                childColumns = "userID",
                onDelete = ForeignKey.CASCADE),
            @ForeignKey(entity = Stage.class,
                parentColumns = "stageID",
                childColumns = "stageID",
                onDelete = ForeignKey.CASCADE)
        })
public class Project extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int projectID;
    @ColumnInfo(index = true)
    @NonNull
    private String userID;
    @ColumnInfo(index = true)
    @NonNull
    private int stageID;
    @ColumnInfo(name = "ProjectName")
    private String projectName;
    @ColumnInfo(name = "Description")
    private String description;
    @ColumnInfo(name = "StartDate")
    private long startDate;
    @ColumnInfo(name = "EndDate")
    private long endDate;
    @ColumnInfo(name = "Client")
    private String client;

    public Project(@NonNull String userID, int stageID, String projectName,
                   String description, long startDate, long endDate, String client) {
        this.userID = userID;
        this.stageID = stageID;
        this.projectName = projectName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.client = client;
    }

    @Bindable
    public int getProjectID() { return projectID; }
    public void setProjectID(int projectID) {
        this.projectID = projectID;
        notifyPropertyChanged(BR.projectID);
    }

    @Bindable
    public String getUserID() { return userID; }
    public void setUserID(String userID) {
        this.userID = userID;
        notifyPropertyChanged(BR.userID);
    }

    @Bindable
    public int getStageID() { return stageID; }
    public void setStageID(int stageID) {
        this.stageID = stageID;
        notifyPropertyChanged(BR.stageID);
    }

    @Bindable
    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
        notifyPropertyChanged(BR.projectName);
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
    public String getClient() { return client; }
    public void setClient(String client) {
        this.client = client;
        notifyPropertyChanged(BR.client);
    }
}
