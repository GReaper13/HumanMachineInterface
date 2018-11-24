package com.example.greaper.drone.ui;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.example.greaper.drone.R;
import com.github.chrisbanes.photoview.PhotoView;
//import com.github.chrisbanes.photoview.OnSingleFlingListener;
//import com.github.chrisbanes.photoview.PhotoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class ControlActivity extends AppCompatActivity {

    @BindView(R.id.photoView)
    PhotoView photoView;
//
//    @BindView(R.id.ivUp)
//    ImageView ivUp;

    private float scale = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        ButterKnife.bind(this);
        photoView.post(() -> {
            photoView.setMaximumScale(10);
            photoView.setMinimumScale(1);
            photoView.setScale(scale);
        });

        goForward();
    }

    Handler handler = new Handler();

    private void goForward() {
        handler.postDelayed(() -> {
            photoView.getAttacher().drag(0, 1 * scale / 5);
            goForward();
        }, 10);
    }

    @OnClick(R.id.ivUp)
    public void up() {
        if (scale > 1) {
            scale -= 1;
        }
        photoView.getAttacher().setScale(scale, true);
    }

    @OnClick(R.id.ivDown)
    public void down() {
        if (scale < 9) {
            scale += 1;
        }
        photoView.getAttacher().setScale(scale, true);
    }

    @OnClick(R.id.ivLeft)
    public void left() {
        photoView.getAttacher().drag(10, 0);
    }

    @OnClick(R.id.ivRight)
    public void right() {
        photoView.getAttacher().drag(-10, 0);
    }

    @BindView(R.id.ivLeft)
    ImageView ivLeft;

    @OnLongClick(R.id.ivLeft)
    public boolean holdLeft() {

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            left();
        }, 10);
        left();
        left();
        left();
        left();
        left();

        return true;
    }

    @OnLongClick(R.id.ivRight)
    public boolean holdRight() {
        right();
        right();
        right();
        right();
        right();
        return true;
    }
}
