package com.example.greaper.drone.main.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.greaper.drone.R;
import com.example.greaper.drone.ui.GraphActivity;
import com.example.greaper.drone.utils.Const;
import com.jjoe64.graphview.GraphView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DashboardFragment extends Fragment implements Const {

    @BindView(R.id.layout_drone)
    RelativeLayout layoutDrone;
    @BindView(R.id.layout_different)
    RelativeLayout layoutDifferent;
    @BindView(R.id.layout_warning)
    RelativeLayout layoutWarning;
    Unbinder unbinder;

    Intent graphIntent;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        unbinder = ButterKnife.bind(this, view);
        graphIntent = new Intent(getContext(), GraphActivity.class);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.layout_drone, R.id.layout_different, R.id.layout_warning})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_drone:
                graphIntent.putExtra(NAME_GRAPH, DRONE);
                startActivity(graphIntent);
                break;
            case R.id.layout_different:
                graphIntent.putExtra(NAME_GRAPH, DIFFERENT);
                startActivity(graphIntent);
                break;
            case R.id.layout_warning:
                graphIntent.putExtra(NAME_GRAPH, WARNING);
                startActivity(graphIntent);
                break;
        }
    }
}
