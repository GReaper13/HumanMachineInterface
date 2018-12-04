package com.example.greaper.drone.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.greaper.drone.R;
import com.example.greaper.drone.data.Image;
import com.example.greaper.drone.utils.AppUtils;
import com.example.greaper.drone.utils.Const;
import com.example.greaper.drone.utils.ViewUtil;
import com.github.chrisbanes.photoview.OnMatrixChangedListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoDroneActivity extends AppCompatActivity implements Const {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.layout_header)
    RelativeLayout layoutHeader;
    @BindView(R.id.photo_video)
    PhotoView photoVideo;
    @BindView(R.id.number_drone)
    SearchableSpinner spNumberDrone;
    @BindView(R.id.sp_select_drone)
    SearchableSpinner spSelectDrone;
    @BindView(R.id.layout_select_drone)
    LinearLayout layoutSelectDrone;
    @BindView(R.id.img_hide)
    ImageView imgHide;
    @BindView(R.id.layout_photo_view)
    RelativeLayout layoutPhotoView;
    @BindView(R.id.txt_time)
    TextView txtTime;
    @BindView(R.id.ic_more)
    ImageView icMore;
    private boolean firstCreate;
    private float originLeft, originTop;

    private int countDroneCustom = 1;

    private List<ImageView> imageViewList;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_drone);
        ButterKnife.bind(this);
        initSpinner();
    }

    public void backClick(View view) {
        finish();
    }

    @OnClick(R.id.img_hide)
    public void onViewClicked() {
        if (layoutSelectDrone.getVisibility() == View.VISIBLE) {
            layoutSelectDrone.setVisibility(View.GONE);
            imgHide.setImageResource(R.drawable.arrow_down);
        } else {
            layoutSelectDrone.setVisibility(View.VISIBLE);
            imgHide.setImageResource(R.drawable.arrow_up);
        }
    }

    private void initSpinner() {
        String[] arraySpinner = new String[]{
                "1", "10", "20", "50", "100", "200", "500", "Custom"
        };

        ArrayAdapter<String> adapterNumberDrone = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapterNumberDrone.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNumberDrone.setAdapter(adapterNumberDrone);

        spNumberDrone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position != 7) {
                    List<String> listDrone = new ArrayList<>();
                    int countDrone = Integer.parseInt(arraySpinner[position]);
                    if (position == 0) {
                        for (int i = 0; i < 500; i++) {
                            listDrone.add("Drone " + String.valueOf(i + 1));
                            String[] arrayDrone = new String[listDrone.size()];
                            listDrone.toArray(arrayDrone);
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(VideoDroneActivity.this,
                                    android.R.layout.simple_spinner_item, arrayDrone);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spSelectDrone.setAdapter(adapter);
                        }
                    } else {
                        setNumberSpinner(countDrone);
                    }
                } else {
                    Dialog dialog = new Dialog(VideoDroneActivity.this);
                    dialog.setContentView(R.layout.dialog_get_drone);
                    dialog.show();
                    EditText edtCountDrone = dialog.findViewById(R.id.edt_count_drone);
                    Button btnOk = dialog.findViewById(R.id.btn_ok);
                    btnOk.setOnClickListener(view1 -> {
                        if (checkInput(edtCountDrone.getText().toString())) {
                            int countDrone = Integer.parseInt(edtCountDrone.getText().toString());
                            dialog.dismiss();
                            setNumberSpinner(countDrone);
                            countDroneCustom = countDrone;
                        } else {
                            Toast.makeText(VideoDroneActivity.this, R.string.count_drone_error, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spSelectDrone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int countDrone;
                try {
                    countDrone = Integer.parseInt(spNumberDrone.getSelectedItem().toString());
                } catch (NumberFormatException e) {
                    countDrone = countDroneCustom;
                }

                initList(countDrone);
                layoutPhotoView.removeAllViews();
                for (ImageView imageView : imageViewList) {
                    layoutPhotoView.addView(imageView);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        initList(1);
        photoVideo.setOnMatrixChangeListener(new OnMatrixChangedListener() {
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
        countDownTimer = new CountDownTimer(3000000, 1000) {
            public void onTick(long millisUntilFinished) {
                txtTime.setText(AppUtils.intToTime(3000000 - millisUntilFinished + 7 * 3600 * 1000));
            }

            public void onFinish() {
            }
        };
        countDownTimer.start();
    }

    private void setPositionIcon(int marginLeft, int marginTop, ImageView imgIcon, RectF rect) {
        int currentMarginLeft = Math.round((ViewUtil.dpToPx(marginLeft) + Math.abs(originLeft)) * photoVideo.getScale() - Math.abs(rect.left));
        int currentMarginTop = Math.round((ViewUtil.dpToPx(marginTop) + Math.abs(originTop)) * photoVideo.getScale() - Math.abs(rect.top));
        int height = ViewUtil.dpToPx(30);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(height, height);
        lp.setMargins(currentMarginLeft, currentMarginTop, 0, 0);
        imgIcon.setLayoutParams(lp);
    }


    private boolean checkInput(String input) {
        try {
            int count = Integer.parseInt(input);
            if (count > 500) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void setNumberSpinner(int countDrone) {
        List<String> listDrone = new ArrayList<>();
        int countItemInt = 500 / countDrone;
        float countItemFloat = 500f / countDrone;
        if (countItemFloat != (int) countItemFloat) {
            countItemInt += 1;
        }
        for (int i = 0; i < countItemInt; i++) {
            int startDrone = i * countDrone + 1;
            int endDrone = (i + 1) * countDrone;
            if (endDrone > 500) {
                endDrone = 500;
            }
            listDrone.add("Drone " + startDrone + " - " + endDrone);
            String[] arrayDrone = new String[listDrone.size()];
            listDrone.toArray(arrayDrone);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(VideoDroneActivity.this,
                    android.R.layout.simple_spinner_item, arrayDrone);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spSelectDrone.setAdapter(adapter);
        }
    }

    private void initList(int countDrone) {
        imageViewList = new ArrayList<>();
        Random r = new Random();
        int low = 100;
        int highLeft = 700;
        int highTop = 500;
        for (int i = 0; i < countDrone; i++) {
            int marginTop = r.nextInt(highTop - low) + low;
            int marginLeft = r.nextInt(highLeft - low) + low;
            ImageView imageView = new ImageView(this);
            initImageView(imageView, marginLeft, marginTop);
            Image imageInfor = new Image(marginTop, marginLeft, 2);
            imageView.setImageResource(R.drawable.ic_drone_blue);
            imageView.setTag(imageInfor);
            imageViewList.add(imageView);
            imageView.setOnClickListener(view -> {
                Dialog dialog = new Dialog(VideoDroneActivity.this);
                dialog.setContentView(R.layout.dialog_drone_detail);
                Button btnView = dialog.findViewById(R.id.btn_view);
                btnView.setOnClickListener(view1 -> {
                    dialog.dismiss();
                    Intent warningIntent = new Intent(VideoDroneActivity.this, VideoActivity.class);
                    warningIntent.putExtra(TYPE_WARNING, 2);
                    startActivity(warningIntent);
                });
                dialog.show();
            });
        }

    }

    private void initImageView(ImageView imageView, int marginLeft, int marginTop) {
        int height = ViewUtil.dpToPx(30);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(height, height);
        lp.setMargins(marginLeft, marginTop, 0, 0);
        imageView.setLayoutParams(lp);
    }

    @OnClick(R.id.ic_more)
    public void moreClick() {
        PopupMenu popupMenu = new PopupMenu(this, icMore);
        popupMenu.getMenuInflater().inflate(R.menu.sort_menu, popupMenu.getMenu());
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.change_start_time) {
                    Dialog timeDialog = new Dialog(VideoDroneActivity.this);
                    timeDialog.setContentView(R.layout.dialog_choose_time);
                    EditText edtHour = timeDialog.findViewById(R.id.edt_hour);
                    EditText edtMinute = timeDialog.findViewById(R.id.edt_minute);
                    EditText edtSecond = timeDialog.findViewById(R.id.edt_second);
                    Button btnOK = timeDialog.findViewById(R.id.btn_ok);
                    btnOK.setOnClickListener(view -> {
                        try {
                            int hour = Integer.parseInt(edtHour.getText().toString());
                            int minute = Integer.parseInt(edtMinute.getText().toString());
                            int second = Integer.parseInt(edtSecond.getText().toString());
                            if (hour < 0 || hour >= 24 || minute < 0 || minute >= 60 || second < 0 || second >= 60) {
                                Toast.makeText(VideoDroneActivity.this, getString(R.string.count_drone_error), Toast.LENGTH_SHORT).show();
                            } else {
                                timeDialog.dismiss();
                                String time = hour + ":" + (minute < 10 ? ("0" + minute) : minute) + ":" + (second < 10 ? ("0" + second) : second);
                                txtTime.setText(time);
                                if (countDownTimer != null) {
                                    countDownTimer.cancel();
                                    long startTime = (hour * 3600 + minute * 60 + second) * 1000;
                                    countDownTimer = new CountDownTimer(3000000, 1000) {
                                        public void onTick(long millisUntilFinished) {
                                            txtTime.setText(AppUtils.intToTime(3000000 - millisUntilFinished + startTime));
                                        }

                                        public void onFinish() {
                                        }
                                    };
                                    countDownTimer.start();
                                }
                            }
                        } catch (NumberFormatException e) {
                            Toast.makeText(VideoDroneActivity.this, getString(R.string.count_drone_error), Toast.LENGTH_SHORT).show();
                        }
                    });
                    timeDialog.show();
                } else if (menuItem.getItemId() == R.id.warning) {
                    Dialog differentDialog = new Dialog(VideoDroneActivity.this);
                    differentDialog.setContentView(R.layout.dialog_warning);
                    Button btnView = differentDialog.findViewById(R.id.btn_view_detail);
                    MediaPlayer mediaPlayer = MediaPlayer.create(VideoDroneActivity.this, R.raw.alarm);
                    mediaPlayer.start();
                    btnView.setOnClickListener(view -> {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        differentDialog.dismiss();
                        Intent warningIntent = new Intent(VideoDroneActivity.this, DetailReportActivity.class);
                        warningIntent.putExtra(TYPE_WARNING, Const.WARNING);
                        startActivity(warningIntent);
                    });
                    differentDialog.show();
                }
                return true;
            }
        });
    }
}
