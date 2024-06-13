package com.example.planpro;

public class TasksModel {

    int id;
    String taskName;
    String taskHour;
    String taskMinute;
    String taskAmPm;

    public TasksModel() {
        this.taskName = taskName;
        this.taskHour = taskHour;
        this.taskMinute = taskMinute;
        this.taskAmPm = taskAmPm;
        this.id = id;
    }



    public String getTaskName() {
        return taskName;
    }

    public String getTaskHour() {
        return taskHour;
    }

    public String getTaskMinute() {
        return taskMinute;
    }

    public String getTaskAmPm() {
        return taskAmPm;
    }

    public int getId() {
        return id;
    }
}
