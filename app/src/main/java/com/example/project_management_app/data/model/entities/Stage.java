package com.example.project_management_app.data.model.entities;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.project_management_app.BR;

@Entity(tableName = "Stages")
public class Stage extends BaseObservable{
    @PrimaryKey(autoGenerate = true)
    private int stageID;
    @ColumnInfo(name = "StageName")
    private String stageName;
    @ColumnInfo(name = "Description")
    private String description;

    public Stage(String stageName, String description) {
        this.stageName = stageName;
        this.description = description;
    }

    @Ignore
    public Stage() {}

    @Bindable
    public int getStageID() { return stageID; }
    public void setStageID(int stageID) {
        this.stageID = stageID;
        notifyPropertyChanged(BR.stageID);
    }

    @Bindable
    public String getStageName() { return stageName; }
    public void setStageName(String stageName) {
        this.stageName = stageName;
        notifyPropertyChanged(BR.stageName);
    }

    @Bindable
    public String getDescription() { return description; }
    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }
}
