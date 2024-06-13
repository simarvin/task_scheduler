package com.example.planpro;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Home extends Activity {

    private static final String TAG = "Home";

    private ArrayList<String> mMonths = new ArrayList<>();
    private ArrayList<String> mDates = new ArrayList<>();

    private ArrayList<String> mDays = new ArrayList<>();
    TextView add_tasks_btn;

    ArrayList<TasksModel> tasksModels = new ArrayList<>();
    ArrayList<String> taskDescription, taskHour, taskMinute, taskAmpm;
    RecyclerView recyclerView;

    Tasks_RecyclerViewAdapter adapter;

    public static final String Extra_username = "username";
    DBTask dbTask;
    @Override
    protected void onCreate(Bundle SaveInstanceState) {

        super.onCreate(SaveInstanceState);
        setContentView(R.layout.home);


        add_tasks_btn = (TextView) findViewById(R.id.add_task_btn);

        Intent in = getIntent();
         String username = in.getStringExtra(LogIn.Extra_Username);

        add_tasks_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Home.this, CreateTask.class);
                in.putExtra(Extra_username, username);
                startActivity(in);
            }
        });

        dbTask = new DBTask(this);
        taskDescription = new ArrayList<>();
        taskHour = new ArrayList<>();
        taskMinute =  new ArrayList<>();
        taskAmpm = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.tasks_recyclerView);
        adapter = new Tasks_RecyclerViewAdapter(this, taskHour, taskMinute, taskDescription, taskAmpm);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnItemClickListener(new Tasks_RecyclerViewAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {

               String Task = taskDescription.get(position).toString();
               Boolean deleteData = dbTask.DeleteTask(Task);
               if(deleteData == true){
                   Toast.makeText(Home.this, "Task Deleted", Toast.LENGTH_SHORT).show();
               }else{
                   Toast.makeText(Home.this, "Failed to Delete Task", Toast.LENGTH_SHORT).show();
               }
                taskHour.remove(position);
                taskMinute.remove(position);
                taskDescription.remove(position);
                taskAmpm.remove(position);

                adapter.notifyItemRemoved(position);


            }
        });


        setUpTasksModels();
        setUpMonths();
        setUpdates();
        setUpDays();
    }

    private void setUpTasksModels(){

        Intent in = getIntent();
        String username = in.getStringExtra(LogIn.Extra_Username);

        Cursor cursor = dbTask.getTasks(username);

        if (cursor.getCount() == 0){
            Toast.makeText(Home.this, "No List of Tasks", Toast.LENGTH_SHORT).show();
            return;
        }else{
            while (cursor.moveToNext()){
                taskDescription.add(cursor.getString(1));
                taskHour.add(cursor.getString(2));
                taskMinute.add(cursor.getString(3));
                taskAmpm.add(cursor.getString(4));

            }
        }
    }


    private void setUpMonths(){

        Log.d(TAG, "setUpMonths: preparing months");

        mMonths.add("JANUARY");
        mMonths.add("FEBRUARY");
        mMonths.add("MARCH");
        mMonths.add("APRIL");
        mMonths.add("MAY");
        mMonths.add("JUNE");
        mMonths.add("JULY");
        mMonths.add("AUGUST");
        mMonths.add("SEPTEMBER");
        mMonths.add("OCTOBER");
        mMonths.add("NOVEMBER");
        mMonths.add("DECEMBER");


        initRecylerViewMonth();
    }

     private void initRecylerViewMonth(){
         Log.d(TAG, "initRecylerViewMonth: init recycler view month");

         LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false );
         RecyclerView recyclerView1 = findViewById(R.id.recylcerViewMonth);
         recyclerView1.setLayoutManager(layoutManager);
         Month_RecyclerViewAdapter adapterMonth = new Month_RecyclerViewAdapter(this, mMonths);
         recyclerView1.setAdapter(adapterMonth);
     }


     private void setUpdates(){
         Log.d(TAG, "setUpdates: preparing dates");

         mDates.add("1");
         mDates.add("2");
         mDates.add("3");
         mDates.add("4");
         mDates.add("5");
         mDates.add("6");
         mDates.add("7");
         mDates.add("8");
         mDates.add("9");
         mDates.add("10");
         mDates.add("11");
         mDates.add("12");
         mDates.add("13");
         mDates.add("14");
         mDates.add("15");
         mDates.add("16");
         mDates.add("17");
         mDates.add("18");
         mDates.add("19");
         mDates.add("20");
         mDates.add("21");
         mDates.add("22");
         mDates.add("23");
         mDates.add("24");
         mDates.add("25");
         mDates.add("26");
         mDates.add("27");
         mDates.add("28");
         mDates.add("29");
         mDates.add("30");
         mDates.add("31");

         initRecyclerViewDates();
     }

     private void initRecyclerViewDates(){
         Log.d(TAG, "initRecyclerViewDates: init recycler view dates");

         LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
         RecyclerView recyclerViewDates = findViewById(R.id.recylcerViewDates);
         recyclerViewDates.setLayoutManager(layoutManager);
         Dates_RecyclerViewAdapter adapterDates = new Dates_RecyclerViewAdapter(this, mDates);
         recyclerViewDates.setAdapter(adapterDates);
     }


     private void setUpDays(){
         Log.d(TAG, "setUpDays: preparing days");

        mDays.add("M");
        mDays.add("T");
        mDays.add("W");
        mDays.add("TH");
        mDays.add("F");
        mDays.add("SA");
        mDays.add("SU");
         initRecyclerDays();
     }

     private void initRecyclerDays(){
         Log.d(TAG, "initRecyclerDays: init days");

         LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
         RecyclerView recyclerViewDays = findViewById(R.id.recylcerViewDays);
         recyclerViewDays.setLayoutManager(layoutManager);
         Days_RecyclerViewAdapter adapterDays = new Days_RecyclerViewAdapter(mDays, this);
         recyclerViewDays.setAdapter(adapterDays);
     }

}
