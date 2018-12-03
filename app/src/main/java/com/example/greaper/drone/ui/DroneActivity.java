package com.example.greaper.drone.ui;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.greaper.drone.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DroneActivity extends AppCompatActivity {

    @BindView(R.id.tvDroneName)
    TextView tvDroneName;

    @BindView(R.id.tvTimeRemain)
    TextView tvTimeRemain;

    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drone);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        tvDroneName.setText(" " + intent.getStringExtra("drone_name"));

        countDownTimer = new CountDownTimer(5*60*60*1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String countDown = timeIntervalToString(millisUntilFinished/1000);
                tvTimeRemain.setText(countDown);
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    @OnClick(R.id.tvControlDrone)
    public void controlDrone() {
        Intent intent = new Intent(this, ControlActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.ivBack)
    public void back() {
        finish();
    }

    private String timeIntervalToString(long timeInterval) {
        long seconds = timeInterval % 60;
        long minutes = (timeInterval / 60) % 60;
        long hours = (timeInterval / 3600);
        return hours + ":" + (minutes < 10 ? ("0" + minutes) : minutes) + ":" + (seconds < 10 ? ("0" + seconds) : seconds);
    }
}
