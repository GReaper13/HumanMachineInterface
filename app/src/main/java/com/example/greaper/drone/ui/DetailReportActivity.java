package com.example.greaper.drone.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.greaper.drone.R;
import com.example.greaper.drone.utils.Const;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailReportActivity extends AppCompatActivity implements Const {

    @BindView(R.id.layout_header)
    RelativeLayout layoutHeader;
    @BindView(R.id.layout_item_1)
    RelativeLayout layoutItem1;
    @BindView(R.id.layout_item_2)
    RelativeLayout layoutItem2;
    @BindView(R.id.layout_item_3)
    RelativeLayout layoutItem3;
    @BindView(R.id.video_drone)
    VideoView videoDrone;
    @BindView(R.id.mc_video)
    MediaController mcVideo;
    @BindView(R.id.txt_detail)
    TextView txtDetail;
    @BindView(R.id.layout_detail)
    LinearLayout layoutDetail;
    @BindView(R.id.img_check_1)
    ImageView imgCheck1;
    @BindView(R.id.img_check_2)
    ImageView imgCheck2;
    @BindView(R.id.img_check_3)
    ImageView imgCheck3;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.img_type)
    ImageView imgType;
    @BindView(R.id.bg_main)
    RelativeLayout bgMain;

    private long firstClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_report);
        ButterKnife.bind(this);
        int type = getIntent().getIntExtra(TYPE_WARNING, WARNING);
        if (type == ERROR) {
            imgType.setImageResource(R.drawable.error_red);
            bgMain.setBackgroundColor(getResources().getColor(R.color.blur_red));
        }

    }

    @OnClick({R.id.layout_item_1, R.id.layout_item_2, R.id.layout_item_3, R.id.img_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_item_1:
                imgCheck1.setVisibility(View.VISIBLE);
                imgCheck2.setVisibility(View.GONE);
                imgCheck3.setVisibility(View.GONE);
                layoutDetail.setVisibility(View.VISIBLE);
                txtDetail.setVisibility(View.VISIBLE);
                startVideo();
                break;
            case R.id.layout_item_2:
                imgCheck1.setVisibility(View.GONE);
                imgCheck2.setVisibility(View.VISIBLE);
                imgCheck3.setVisibility(View.GONE);
                layoutDetail.setVisibility(View.VISIBLE);
                txtDetail.setVisibility(View.VISIBLE);
                startVideo();
                break;
            case R.id.layout_item_3:
                imgCheck1.setVisibility(View.GONE);
                imgCheck2.setVisibility(View.GONE);
                imgCheck3.setVisibility(View.VISIBLE);
                layoutDetail.setVisibility(View.VISIBLE);
                txtDetail.setVisibility(View.VISIBLE);
                startVideo();
                break;
            case R.id.img_back:
                finish();
                break;
        }
    }

    private void startVideo() {
        Toast.makeText(DetailReportActivity.this, R.string.show_full_video, Toast.LENGTH_SHORT).show();
        videoDrone.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.chay));
        videoDrone.start();
        videoDrone.setOnClickListener(view -> {
            long timeChange = System.currentTimeMillis() - firstClick;
            if (timeChange <= 1000) {
                Intent intent = new Intent(DetailReportActivity.this, VideoActivity.class);
                startActivity(intent);
                videoDrone.pause();
            } else {
                firstClick = System.currentTimeMillis();
            }
        });
    }
}
