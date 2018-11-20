package com.example.greaper.drone.main.video.total;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.greaper.drone.R;
import com.example.greaper.drone.data.TotalItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TotalAdapter extends RecyclerView.Adapter<TotalAdapter.TotalHolder> {

    private List<TotalItem> totalItemList;

    public List<TotalItem> getTotalItemList() {
        return totalItemList;
    }

    public void setTotalItemList(List<TotalItem> totalItemList) {
        this.totalItemList = totalItemList;
    }

    @NonNull
    @Override
    public TotalHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_total, viewGroup, false);
        return new TotalHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TotalHolder totalHolder, int i) {
        totalHolder.onBind(i);
    }

    @Override
    public int getItemCount() {
        return totalItemList.size();
    }

    class TotalHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_date)
        TextView txtDate;
        @BindView(R.id.img_complete)
        ImageView imgComplete;
        @BindView(R.id.layout_content)
        RelativeLayout layoutContent;

        TotalHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind(int position) {
            TotalItem item = totalItemList.get(position);
            txtDate.setText(item.getDate());
            if (item.getType() == 1) {
                imgComplete.setImageResource(R.drawable.ic_check);
            } else {
                imgComplete.setImageResource(R.drawable.ic_close);
            }
        }
    }

}
