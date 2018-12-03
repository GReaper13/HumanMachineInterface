package com.example.greaper.drone.ui;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

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

    @BindView(R.id.tvLevel)
    TextView tvLevel;

    @BindView(R.id.tvScale)
    TextView tvScale;

    @BindView(R.id.position)
    View position;

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
            tvScale.setText("Thu phóng: " + ((int) scale));
        });

        goForward();
    }

    Handler handler = new Handler();

    private void goForward() {
        handler.postDelayed(() -> {
            photoView.getAttacher().drag(0, 1 * scale / 5);
            float y = position.getTranslationY();
            y -= 0.1f;
            position.setTranslationY(y);
            goForward();
        }, 10);
    }

    @OnClick(R.id.ivUp)
    public void up() {
        if (scale > 1) {
            scale -= 1;
        }
        tvScale.setText("Thu phóng: " + ((int) scale));
        photoView.getAttacher().setScale(scale, true);
    }

    @OnClick(R.id.ivDown)
    public void down() {
        if (scale < 9) {
            scale += 1;
        }
        tvScale.setText("Thu phóng: " + ((int) scale));
        photoView.getAttacher().setScale(scale, true);
    }

    @OnClick(R.id.ivLeft)
    public void left() {
//        photoView.setRotationBy(1);
        photoView.getAttacher().drag(10, 0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) position.getLayoutParams();
        int left = layoutParams.getMarginStart();
        if (left > 5) {
            left -= 5;
            layoutParams.setMarginStart(left);
            position.setLayoutParams(layoutParams);
        }
    }

    @OnClick(R.id.ivRight)
    public void right() {
//        photoView.setRotationBy(-1);
        photoView.getAttacher().drag(-10, 0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) position.getLayoutParams();
        int left = layoutParams.getMarginStart();
        if (left < 135) {
            left += 5;
            layoutParams.setMarginStart(left);
            position.setLayoutParams(layoutParams);
        }
    }

    @BindView(R.id.ivLeft)
    ImageView ivLeft;



//    @OnLongClick(R.id.ivLeft)
//    public boolean holdLeft() {
//
//        Handler handler = new Handler();
//        handler.postDelayed(() -> {
//            left();
//        }, 10);
//        left();
//        left();
//        left();
//        left();
//        left();
//
//        return true;
//    }
//
//    @OnLongClick(R.id.ivRight)
//    public boolean holdRight() {
//        right();
//        right();
//        right();
//        right();
//        right();
//        return true;
//    }

    int level = 500;

    @OnClick(R.id.ivTop)
    public void moveUp() {
        if (scale < 9) {
            scale -= 0.1f;
        }
        if (level < 900) {
            level += 10;
        }
        tvLevel.setText("Độ cao: " + level + "m");
        photoView.getAttacher().setScale(scale, true);
    }

    @OnClick(R.id.ivBottom)
    public void moveDown() {
        if (scale > 1) {
            scale += 0.1f;
        }
        if (level > 100) {
            level -= 10;
        }
        tvLevel.setText("Độ cao: " + level + "m");
        photoView.getAttacher().setScale(scale, true);
    }
}
