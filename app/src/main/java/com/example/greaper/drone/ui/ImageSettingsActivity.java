package com.example.greaper.drone.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.greaper.drone.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImageSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_settings);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.ivBack)
    public void back() {
        finish();
    }
}
