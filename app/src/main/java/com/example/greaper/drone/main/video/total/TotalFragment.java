package com.example.greaper.drone.main.video.total;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.greaper.drone.R;
import com.example.greaper.drone.data.TotalItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class TotalFragment extends Fragment {

    @BindView(R.id.rv_total)
    RecyclerView rvTotal;
    Unbinder unbinder;

    private TotalAdapter totalAdapter;

    public TotalFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_total, container, false);
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
        totalAdapter.setTotalItemList(list);
        rvTotal.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTotal.setAdapter(totalAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
