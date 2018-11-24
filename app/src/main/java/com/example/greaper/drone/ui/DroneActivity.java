package com.example.greaper.drone.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.greaper.drone.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DroneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drone);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tvControlDrone)
    public void controlDrone() {
        Intent intent = new Intent(this, ControlActivity.class);
        startActivity(intent);
    }
}
