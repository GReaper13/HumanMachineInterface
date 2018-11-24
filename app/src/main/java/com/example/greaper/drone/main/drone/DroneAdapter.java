package com.example.greaper.drone.main.drone;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.greaper.drone.R;
import com.example.greaper.drone.data.Drone;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DroneAdapter extends RecyclerView.Adapter<DroneAdapter.DroneViewHolder> {

    private Context mContext;
    private List<Drone> droneList;
    private LayoutInflater inflater;

    private OnItemClickListener onItemClickListener;

    public DroneAdapter(Context mContext, List<Drone> droneList) {
        this.mContext = mContext;
        this.droneList = droneList;
        this.inflater = LayoutInflater.from(mContext);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public DroneViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.drone_item, viewGroup, false);
        return new DroneViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DroneViewHolder holder, int i) {
        holder.droneName.setText(droneList.get(i).getName());

        if (onItemClickListener != null) {
            holder.droneName.setOnClickListener(view -> onItemClickListener.onClick(i));
        }

    }

    @Override
    public int getItemCount() {
        return droneList.size();
    }

    class DroneViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvDroneName)
        TextView droneName;

        DroneViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
