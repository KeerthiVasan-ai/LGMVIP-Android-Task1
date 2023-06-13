package com.keerthi77459.covid19tracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class caseAdapter extends RecyclerView.Adapter<caseAdapter.MyViewHolder> {

    Context context;
    ArrayList<caseName> list;
    SharedPreferences sharedPreferences;

    public caseAdapter(Context context, ArrayList<caseName> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public caseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.case_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull caseAdapter.MyViewHolder holder, int position) {
        caseName CaseName = list.get(position);
        holder.activeCountText.setText(CaseName.getActiveCase());
        holder.confirmedCountText.setText(CaseName.getConfirmedCase());
        holder.decreasedCountText.setText(CaseName.getDecreasedCase());
        holder.recoveredCountText.setText(CaseName.getRecoveredCase());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView activeCountText,confirmedCountText,decreasedCountText,recoveredCountText;
        TextView stateName,districtName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sharedPreferences = context.getSharedPreferences("STATEDATA", Context.MODE_PRIVATE);

            String State = sharedPreferences.getString("StateName",null);
            String District = sharedPreferences.getString("DistrictName",null);

            stateName = itemView.findViewById(R.id.stateName);
            stateName.setText(State);

            districtName = itemView.findViewById(R.id.districtName);
            districtName.setText(District);

            activeCountText = itemView.findViewById(R.id.activeCountText);
            confirmedCountText = itemView.findViewById(R.id.confirmedCountText);
            decreasedCountText = itemView.findViewById(R.id.decreasedCountText);
            recoveredCountText = itemView.findViewById(R.id.recoveredCountText);
        }
    }
}