package com.example.planpro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBTask extends SQLiteOpenHelper {
    public DBTask(@Nullable Context context) {super(context, "tasks", null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tasks(id INTEGER PRIMARY KEY, task_name TEXT, task_hour TEXT, task_minute TEXT, task_am_pm TEXT, username TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS tasks");
    }

    public Boolean AddTask(String taskName, String taskHour, String taskMinute, String taskAmPm, String username){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contValues = new ContentValues();

        contValues.put("task_name", taskName);
        contValues.put("task_hour", taskHour);
        contValues.put("task_minute", taskMinute);
        contValues.put("task_am_pm", taskAmPm);
        contValues.put("username", username);

        long result = db.insert("tasks", null, contValues);
        if (result == -1){
            return false;
        }else{
            return true;
        }
    }

    public ArrayList<TasksModel> fetchTasks(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tasks", null);
        ArrayList<TasksModel> arrayList = new ArrayList<>();

        while (cursor.moveToNext()){
            TasksModel tasksModel = new TasksModel();
            tasksModel.id = cursor.getInt(0);
            tasksModel.taskName = cursor.getString(1);
            tasksModel.taskHour = cursor.getString(2);
            tasksModel.taskMinute = cursor.getString(3);
            tasksModel.taskAmPm = cursor.getString(4);

            arrayList.add(tasksModel);
        }
        return arrayList;
    }

    public Boolean DeleteTask(String taskDesc){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tasks WHERE task_name = ?", new String[]{taskDesc});

        if (cursor.getCount() > 0){
            long result =  db.delete("tasks", "task_name=?", new String[]{taskDesc});

            if(result == -1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

    public Cursor getTasks(String username){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM tasks WHERE username = ?", new String[]{username});

        return cursor;
    }
}
