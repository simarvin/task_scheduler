package com.example.planpro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.temporal.Temporal;
import java.util.ArrayList;

public class Tasks_RecyclerViewAdapter extends RecyclerView.Adapter<Tasks_RecyclerViewAdapter.MyViewHolder> {

    Context context;

    DBTask dbTask;

    public Tasks_RecyclerViewAdapter(Context context, ArrayList taskHour, ArrayList taskMinute, ArrayList taskDesc, ArrayList taskAMPM) {
        this.context = context;
        this.taskHour = taskHour;
        this.taskMinute = taskMinute;
        this.taskDesc = taskDesc;
        this.taskAMPM = taskAMPM;

    }

    private ArrayList taskHour, taskMinute, taskDesc, taskAMPM;
    private onItemClickListener listener;

    public interface onItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener clickListener){
        listener = clickListener;
    }


    @NonNull
    @Override
    public Tasks_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {         

        View view = LayoutInflater.from(context).inflate(R.layout.recyler_tasks, parent, false);
        return new MyViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull Tasks_RecyclerViewAdapter.MyViewHolder holder, int position) {
            holder.task_hour.setText(String.valueOf(taskHour.get(position)));
            holder.task_minute.setText(String.valueOf(taskMinute.get(position)));
            holder.task_desc.setText(String.valueOf(taskDesc.get(position)));
            holder.task_ampm.setText(String.valueOf(taskAMPM.get(position)));

    }

    @Override
    public int getItemCount() {
        return taskHour.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView task_hour, task_minute, task_desc, task_ampm, delete;


        public MyViewHolder(@NonNull View itemView, onItemClickListener listener) {
            super(itemView);

            task_hour = itemView.findViewById(R.id.hour_txt);
            task_minute = itemView.findViewById(R.id.minute_txt);
            task_ampm = itemView.findViewById(R.id.am_pm_txt);
            task_desc = itemView.findViewById(R.id.task_desc_txt);
            delete = itemView.findViewById(R.id.delete_btn);



            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   listener.onItemClick(getAdapterPosition());

                }
            });
        }
    }
}
