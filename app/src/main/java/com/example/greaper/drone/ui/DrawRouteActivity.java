package com.example.greaper.drone.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.greaper.drone.R;
import com.example.greaper.drone.utils.HawkHelper;
import com.example.greaper.drone.view.DrawView;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DrawRouteActivity extends AppCompatActivity implements DrawView.DrawViewListener {

    @BindView(R.id.img_main)
    ImageView imgMain;
    @BindView(R.id.dv_main)
    DrawView dvMain;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.layout_header)
    RelativeLayout layoutHeader;
    @BindView(R.id.img_refresh)
    ImageView imgRefresh;
    @BindView(R.id.img_ok)
    ImageView imgOk;
    @BindView(R.id.layout_check_draw)
    LinearLayout layoutCheckDraw;
    private int imageWidth, imageHeight;
    public Bitmap mainBitmap;
    @BindView(R.id.layout_main)
    RelativeLayout layoutMain;
    @BindView(R.id.layout_loading)
    RelativeLayout layoutLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_ruote);
        ButterKnife.bind(this);
        initView();
        new LoadImageTask().execute();
    }

    public void backClick(View view) {
        finish();
    }

    @Override
    public void onActionUp() {
        layoutCheckDraw.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.img_refresh, R.id.img_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_refresh:
//                dvMain.clear();
//                layoutCheckDraw.setVisibility(View.GONE);
                recreate();
                break;
            case R.id.img_ok:
                layoutLoading.setVisibility(View.VISIBLE);
                Bitmap workingBitmap = Bitmap.createBitmap(mainBitmap);
                Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true);
                Canvas canvas = new Canvas(mutableBitmap);
                canvas.drawBitmap(dvMain.getPaintBit(), 0, 0, null);
                //Convert to byte array
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                mutableBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
                HawkHelper.setImageRoute(encoded);
                layoutLoading.setVisibility(View.GONE);
                finish();
                break;
        }
    }

    private final class LoadImageTask extends AsyncTask<Void, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(Void... voids) {

            return getSampledBitmap(imageWidth,
                    imageHeight);
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            if (mainBitmap != null) {
                mainBitmap.recycle();
                mainBitmap = null;
                System.gc();
            }
            mainBitmap = result;
            imgMain.setImageBitmap(result);
        }
    }

    private Bitmap getSampledBitmap(int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        int inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inSampleSize = inSampleSize;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(getResources(), R.drawable.current_route, options);
    }

    private void initView() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        imageWidth = metrics.widthPixels / 2;
        imageHeight = metrics.heightPixels / 2;
        dvMain.setDrawViewListener(this);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
