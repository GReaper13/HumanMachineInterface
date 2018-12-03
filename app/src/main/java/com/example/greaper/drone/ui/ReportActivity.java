package com.example.greaper.drone.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.greaper.drone.R;
import com.example.greaper.drone.data.Image;
import com.example.greaper.drone.utils.Const;
import com.example.greaper.drone.utils.ViewUtil;
import com.github.chrisbanes.photoview.OnMatrixChangedListener;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReportActivity extends AppCompatActivity implements Const{

    @BindView(R.id.img_bg)
    PhotoView imgBg;
    @BindView(R.id.layout_main)
    RelativeLayout layoutMain;

    private int scale = 1;
    private boolean firstCreate;
    private float originLeft, originTop;
    private List<ImageView> imageViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);
        imgBg.post(() -> {
            imgBg.setMaximumScale(4);
            imgBg.setMinimumScale(1);
            imgBg.setScale(scale);
        });
        firstCreate = true;

        initList();
        for (ImageView imageView : imageViewList) {
            layoutMain.addView(imageView);
        }

        Toast.makeText(this, R.string.select_icon_to_view, Toast.LENGTH_SHORT).show();

        imgBg.setOnMatrixChangeListener(new OnMatrixChangedListener() {
            @Override
            public void onMatrixChanged(RectF rect) {
                if (firstCreate) {
                    originTop = rect.top;
                    originLeft = rect.left;
                    firstCreate = false;
                } else {
                    for (ImageView imageView : imageViewList) {
                        Image image = (Image) imageView.getTag();
                        setPositionIcon(image.getMarginLeft(), image.getMarginTop(), imageView, rect);
                    }
                }
            }
        });

    }

    private void setPositionIcon(int marginLeft, int marginTop, ImageView imgIcon, RectF rect) {
        int currentMarginLeft = Math.round((ViewUtil.dpToPx(marginLeft) + Math.abs(originLeft)) * imgBg.getScale() - Math.abs(rect.left));
        int currentMarginTop = Math.round((ViewUtil.dpToPx(marginTop) + Math.abs(originTop)) * imgBg.getScale() - Math.abs(rect.top));
        int height = ViewUtil.dpToPx(30);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(height, height);
        lp.setMargins(currentMarginLeft, currentMarginTop, 0, 0);
        imgIcon.setLayoutParams(lp);
    }

    private void initImageView(ImageView imageView, int marginLeft, int marginTop) {
        int height = ViewUtil.dpToPx(30);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(height, height);
        lp.setMargins(marginLeft, marginTop, 0, 0);
        imageView.setLayoutParams(lp);
    }

    private void initList() {
        imageViewList = new ArrayList<>();
        Random r = new Random();
        int low = 100;
        int highLeft = 700;
        int highTop = 500;
        for (int i = 0; i < 10; i++ ) {
            int marginTop = r.nextInt(highTop-low) + low;
            int marginLeft = r.nextInt(highLeft-low) + low;
            int type = r.nextInt(2);
            ImageView imageView = new ImageView(this);
            initImageView(imageView, marginLeft, marginTop);
            Image imageInfor = new Image(marginTop, marginLeft, type);
            if (type == WARNING) {
                imageView.setImageResource(R.drawable.warning_yellow);
            } else {
                imageView.setImageResource(R.drawable.error_red);
            }
            imageView.setTag(imageInfor);
            imageViewList.add(imageView);
            imageView.setOnClickListener(view -> {
                Intent warningIntent = new Intent(ReportActivity.this, DetailReportActivity.class);
                warningIntent.putExtra(TYPE_WARNING, type);
                startActivity(warningIntent);
            });
        }

    }

    public void backClick(View view) {
        finish();
    }
}
