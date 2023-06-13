package com.keerthi77459.covid19tracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class districtAdapter extends RecyclerView.Adapter<districtAdapter.MyViewHolder> {

    Context context;
    SharedPreferences sharedPreferences;
    ArrayList<stateName> list;

    public districtAdapter(Context context, ArrayList<stateName> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(context).inflate(R.layout.state_list,parent,false);
        return new MyViewHolder(V);
    }

    @Override
    public void onBindViewHolder(@NonNull districtAdapter.MyViewHolder holder, int position) {
        stateName StateName = list.get(position);
        holder.stateName.setText(StateName.getStateName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView stateName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            stateName = itemView.findViewById(R.id.adapterStateName);

            stateName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sharedPreferences = context.getSharedPreferences("STATEDATA",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("DistrictName",stateName.getText().toString());
                    editor.commit();
                    Intent intent = new Intent(itemView.getContext(),CaseActivity.class);
                    itemView.getContext().startActivity(intent);
                }

            });
        }
    }

}

