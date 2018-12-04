package com.example.greaper.drone.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.widget.ImageView;

import com.example.greaper.drone.R;
import com.example.greaper.drone.utils.HawkHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultActivity extends AppCompatActivity {

    @BindView(R.id.img_main)
    ImageView imgMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);
        String encoded = HawkHelper.getImageRoute();
        byte[] imageAsBytes = Base64.decode(encoded.getBytes(), Base64.DEFAULT);
        imgMain.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
    }
}
