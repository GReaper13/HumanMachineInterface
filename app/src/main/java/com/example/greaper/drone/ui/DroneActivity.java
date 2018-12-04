package com.example.greaper.drone.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.greaper.drone.R;
import com.example.greaper.drone.utils.HawkHelper;
import com.orhanobut.hawk.Hawk;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DroneActivity extends AppCompatActivity {

    @BindView(R.id.tvDroneName)
    TextView tvDroneName;

    @BindView(R.id.tvTimeRemain)
    TextView tvTimeRemain;

    @BindView(R.id.img_route)
    ImageView imgRoute;

    @BindView(R.id.cb_auto)
    CheckBox cbAuto;

    @BindView(R.id.layout_hide_auto)
    RelativeLayout layoutHideAuto;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drone);
        ButterKnife.bind(this);
        cbAuto.setChecked(true);
        layoutHideAuto.setVisibility(View.GONE);
        cbAuto.setOnCheckedChangeListener((compoundButton, b) -> {
            if (!b) {
                new AlertDialog.Builder(DroneActivity.this).setMessage(getString(R.string.to_control_drone))
                        .setCancelable(false)
                        .setPositiveButton(R.string.ok, (dialogInterface, i) -> {
                            dialogInterface.dismiss();
                            Intent intent = new Intent(this, ControlActivity.class);
                            startActivity(intent);
                        }).setNegativeButton(R.string.cancel, (dialogInterface, i) -> {
                            cbAuto.setChecked(true);
                            dialogInterface.dismiss();
                        }).show();
            }
        });
        if (Hawk.contains(HawkHelper.IMG_ROUTE)) {
            String encoded = HawkHelper.getImageRoute();
            byte[] imageAsBytes = Base64.decode(encoded.getBytes(), Base64.DEFAULT);
            imgRoute.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
        }
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

    @OnClick(R.id.layout_edit)
    public void edit() {
        Intent intent = new Intent(DroneActivity.this, DrawRouteActivity.class);
        startActivityForResult(intent, 900);
    }

    private String timeIntervalToString(long timeInterval) {
        long seconds = timeInterval % 60;
        long minutes = (timeInterval / 60) % 60;
        long hours = (timeInterval / 3600);
        return hours + ":" + (minutes < 10 ? ("0" + minutes) : minutes) + ":" + (seconds < 10 ? ("0" + seconds) : seconds);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 900) {
            if (Hawk.contains(HawkHelper.IMG_ROUTE)) {
                String encoded = HawkHelper.getImageRoute();
                byte[] imageAsBytes = Base64.decode(encoded.getBytes(), Base64.DEFAULT);
                imgRoute.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
            }
        }
    }
}
