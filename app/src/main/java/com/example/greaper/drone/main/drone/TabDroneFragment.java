package com.example.greaper.drone.main.drone;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.greaper.drone.R;
import com.example.greaper.drone.data.Drone;
import com.example.greaper.drone.ui.AddDroneActivity;
import com.example.greaper.drone.ui.DroneActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabDroneFragment extends Fragment {


    public static TabDroneFragment newInstance() {
        return new TabDroneFragment();
    }

    public TabDroneFragment() {
    }

    @BindView(R.id.rvDrone)
    RecyclerView rvDrone;
    @BindView(R.id.txt_add_drone)
    TextView txtAddDrone;
    DroneAdapter adapter;
    List<Drone> droneList = new ArrayList<>();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_drone, container, false);
        ButterKnife.bind(this, rootView);
        init();
        return rootView;
    }

    private void init() {
        for (int i = 0; i < 6; i++) {
            droneList.add(new Drone(i,randomString()));
        }
        Context context = getContext();
        adapter = new DroneAdapter(context, droneList);
        rvDrone.setAdapter(adapter);
        rvDrone.setLayoutManager(new LinearLayoutManager(context));
        adapter.setOnItemClickListener(position -> {
            if (context == null) {
                return;
            }
            Intent intent = new Intent(context, DroneActivity.class);
            intent.putExtra("drone_id", droneList.get(position).getId());
            intent.putExtra("drone_name", droneList.get(position).getName());
            startActivity(intent);
        });
        txtAddDrone.setOnClickListener(view -> {
            startActivityForResult(new Intent(context, AddDroneActivity.class), 500);
        });
    }

    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static Random rnd = new Random();
    String randomString(){
        StringBuilder sb = new StringBuilder(6);
        for(int i = 0; i < 6; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 500 && resultCode == Activity.RESULT_OK) {
            String newDrone = data.getStringExtra("code");
            droneList.add(new Drone(droneList.size(), newDrone));
            adapter.notifyDataSetChanged();
        }
    }
}
