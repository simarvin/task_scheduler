package com.example.planpro;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Dates_RecyclerViewAdapter extends RecyclerView.Adapter<Dates_RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "Dates_RecyclerViewAdapt";

    private ArrayList<String> mDates = new ArrayList<>();
    private Context context;

    public Dates_RecyclerViewAdapter(Context context, ArrayList<String> mDates) {
        this.mDates = mDates;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_dates, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");

        holder.dates.setText(String.valueOf(mDates.get(position)));

        holder.dates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: click a date" + mDates.get(position));
                Toast.makeText(context, mDates.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        Button dates;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dates = (Button) itemView.findViewById(R.id.dates_btn);
        }
    }
}
