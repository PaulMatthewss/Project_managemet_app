package com.example.project_management_app.data.model.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_management_app.R;
import com.example.project_management_app.data.model.entities.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectPowAdapter extends RecyclerView.Adapter<ProjectPowAdapter.MyViewHolder> {
    private List<Project> projects = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public ProjectPowAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout_main, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectPowAdapter.MyViewHolder holder, int position) {
        Project currentProject = projects.get(position);
        holder.project_id.setText(String.valueOf(currentProject.getProjectID()));
        holder.project_name.setText(currentProject.getProjectName());
        holder.project_client.setText(currentProject.getClient());
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setProjects(List<Project> projects){
        this.projects = projects;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView project_id, project_name, project_client;
        ImageButton edit_btn, delete_btn;
        LinearLayout row_element;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            project_id = itemView.findViewById(R.id.project_id);
            project_name = itemView.findViewById(R.id.project_name);
            project_client = itemView.findViewById(R.id.project_client);
            edit_btn = itemView.findViewById(R.id.edit_btn);
            delete_btn = itemView.findViewById(R.id.delete_btn);
            row_element = itemView.findViewById(R.id.row_element);
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if(listener != null && position != RecyclerView.NO_POSITION){
                    listener.onItemClick(projects.get(position));
                }
            });
        }
    }
    public interface  OnItemClickListener{
        void onItemClick(Project project);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

}