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

public class Month_RecyclerViewAdapter extends RecyclerView.Adapter<Month_RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "Month_RecyclerViewAdapt";

    private ArrayList<String> mMonths = new ArrayList<>();
    private Context context;

    public Month_RecyclerViewAdapter( Context context, ArrayList<String> mMonths) {
        this.mMonths = mMonths;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyler_month, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onCreateViewHolder: called");
        holder.month.setText(String.valueOf(mMonths.get(position)));

        holder.month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: click on a month" + mMonths.get(position));
                Toast.makeText(context, mMonths.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMonths.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView month;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            month = (TextView) itemView.findViewById(R.id.month_txtView);
        }
    }
}
