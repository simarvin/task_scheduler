package com.example.planpro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CreateTask extends Activity {

    private static final String TAG = "CreateTask";
    private ArrayList<String> mMonths = new ArrayList<>();
    private ArrayList<String> mDates = new ArrayList<>();

    private ArrayList<String> mDays = new ArrayList<>();
    EditText task_name, task_hour, task_minute;
    RadioButton task_am, task_pm;
    Button done_btn;

    DBTask dbTask;

    @Override
    protected void onCreate(Bundle SaveInstanceState) {
        super.onCreate(SaveInstanceState);
        setContentView(R.layout.create_task);

        dbTask = new DBTask(this);

        task_name = (EditText) findViewById(R.id.task_name);
        task_hour = (EditText) findViewById(R.id.task_hour);
        task_minute = (EditText) findViewById(R.id.task_minute);
        task_am = (RadioButton) findViewById(R.id.rb_task_am);
        task_pm = (RadioButton) findViewById(R.id.rb_task_pm);

        done_btn = (Button) findViewById(R.id.done_btn);

        Intent in = getIntent();
        String username = in.getStringExtra(LogIn.Extra_Username);

        done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taskName = task_name.getText().toString();
                String taskHour = task_hour.getText().toString();
                String taskMinute = task_minute.getText().toString();
                String taskAMPM = "";

                if(taskName.equals("") || taskHour.equals("") || taskMinute.equals("")){
                    Toast.makeText(CreateTask.this, "Please Complete the Form", Toast.LENGTH_SHORT).show();
                }else{
                if(task_am.isChecked()) {
                    taskAMPM = "  AM  ";
                    Boolean insert = dbTask.AddTask(taskName, taskHour, taskMinute, taskAMPM, username);

                    if(insert){
                        Toast.makeText(CreateTask.this, "Task Added Successfully", Toast.LENGTH_SHORT).show();

                        task_name.setText("");
                        task_hour.setText("");
                        task_minute.setText("");
                        Intent in = new Intent(CreateTask.this, Home.class);
                        startActivity(in);
                    }else{
                        Toast.makeText(CreateTask.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                } else if (task_pm.isChecked()) {
                    taskAMPM = "  PM  ";
                    Boolean insert = dbTask.AddTask(taskName, taskHour, taskMinute, taskAMPM, username);

                    if(insert){
                        Toast.makeText(CreateTask.this, "Task Added Successfully", Toast.LENGTH_SHORT).show();

                        task_name.setText("");
                        task_hour.setText("");
                        task_minute.setText("");
                        Intent in = new Intent(CreateTask.this, Home.class);
                        startActivity(in);
                    }else{
                        Toast.makeText(CreateTask.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
                }

            }
        });


        setUpMonths();
        setUpdates();
        setUpDays();
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
