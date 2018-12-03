package com.example.greaper.drone.main.video;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.greaper.drone.R;
import com.example.greaper.drone.data.TotalItem;
import com.example.greaper.drone.main.video.total.TotalAdapter;
import com.example.greaper.drone.ui.VideoDroneActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class VideoFragment extends Fragment implements TotalAdapter.VideoListener {

    @BindView(R.id.rv_total)
    RecyclerView rvTotal;
    private TotalAdapter totalAdapter;
    Unbinder unbinder;

    public VideoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private void initViews() {
        totalAdapter = new TotalAdapter();
        List<TotalItem> list = new ArrayList<>();
        list.add(new TotalItem("05/11/2018", 1));
        list.add(new TotalItem("06/11/2018", 1));
        list.add(new TotalItem("07/11/2018", 0));
        list.add(new TotalItem("08/11/2018", 1));
        list.add(new TotalItem("09/11/2018", 1));
        list.add(new TotalItem("10/11/2018", 0));
        list.add(new TotalItem("11/11/2018", 1));
        list.add(new TotalItem("12/11/2018", 0));
        totalAdapter.setTotalItemList(list, this);
        rvTotal.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTotal.setAdapter(totalAdapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    public void onClickItem() {
        startActivity(new Intent(getContext(), VideoDroneActivity.class));
    }
}
