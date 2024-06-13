package com.example.planpro;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Days_RecyclerViewAdapter extends RecyclerView.Adapter<Days_RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "Days_RecyclerViewAdapte";

    private ArrayList<String> mDays;
    private Context context;

    public Days_RecyclerViewAdapter(ArrayList<String> mDays, Context context) {
        this.mDays = mDays;
        this.context = context;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recylcler_days, parent, false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
        holder.days.setText(String.valueOf(mDays.get(position)));

        holder.days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, mDays.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDays.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView days;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            days = (TextView) itemView.findViewById(R.id.days_btn);
        }
    }
}
