package com.example.greaper.drone.ui;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.greaper.drone.R;
import com.example.greaper.drone.utils.Const;
import com.example.greaper.drone.view.DrawView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Utils;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GraphActivity extends AppCompatActivity implements OnChartValueSelectedListener, Const {

    @BindView(R.id.line_chart)
    LineChart lineChart;

    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);

        type = getIntent().getIntExtra(Const.NAME_GRAPH, Const.DRONE);

        // background color
        lineChart.setBackgroundColor(Color.WHITE);

        // disable description text
        lineChart.getDescription().setEnabled(false);

        // enable touch gestures
        lineChart.setTouchEnabled(true);

        // set listeners
        lineChart.setOnChartValueSelectedListener(this);
        lineChart.setDrawGridBackground(false);

        // create marker to display box when values are selected

        // enable scaling and dragging
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        // chart.setScaleXEnabled(true);
        // chart.setScaleYEnabled(true);

        // force pinch zoom along both axis
        lineChart.setPinchZoom(true);

        XAxis xAxis;
        {   // // X-Axis Style // //
            xAxis = lineChart.getXAxis();

            // vertical grid lines
            xAxis.enableGridDashedLine(10f, 10f, 0f);
        }

        YAxis yAxis;
        {   // // Y-Axis Style // //
            yAxis = lineChart.getAxisLeft();

            // disable dual axis (only use LEFT axis)
            lineChart.getAxisRight().setEnabled(false);

            // horizontal grid lines
            yAxis.enableGridDashedLine(10f, 10f, 0f);

            // axis range
            if (type == Const.DRONE) {
                yAxis.setAxisMaximum(800f);
            } else {
                yAxis.setAxisMaximum(70);
            }
            yAxis.setAxisMinimum(0);

        }
        setData();
    }

    private void setData() {
        int maxRandom, minRandom;
        if (type == Const.DRONE) {
            maxRandom = 500;
            minRandom = 300;
        } else {
            maxRandom = 30;
            minRandom = 5;
        }
        ArrayList<Entry> values = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 30; i++) {
            int val = r.nextInt(maxRandom - minRandom) + minRandom;
            values.add(new Entry(i, val));
        }

        LineDataSet set1;

        if (lineChart.getData() != null &&
                lineChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            set1.notifyDataSetChanged();
            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            if (type == WARNING) {
                set1 = new LineDataSet(values, getString(R.string.lable_drone));
            } else {
                set1 = new LineDataSet(values, getString(R.string.lable_case));
            }

            set1.setDrawIcons(false);

            // draw dashed line
            set1.enableDashedLine(10f, 5f, 0f);

            // black lines and points
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);

            // line thickness and point size
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);

            // draw points as solid circles
            set1.setDrawCircleHole(false);

            // customize legend entry
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            // text size of values
            set1.setValueTextSize(9f);

            // draw selection line as dashed
            set1.enableDashedHighlightLine(10f, 5f, 0f);

            // set the filled area
            set1.setDrawFilled(true);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return lineChart.getAxisLeft().getAxisMinimum();
                }
            });

            // set color of filled area
            if (Utils.getSDKInt() >= 18) {
                // drawables only supported on api level 18 and above
                Drawable drawable;
                if (type == WARNING) {
                   drawable = ContextCompat.getDrawable(this, R.drawable.fade_red);
                } else if (type == DIFFERENT) {
                    drawable = ContextCompat.getDrawable(this, R.drawable.fade_yellow);
                } else {
                    drawable = ContextCompat.getDrawable(this, R.drawable.fade_blue);
                }
                set1.setFillDrawable(drawable);
            } else {
                set1.setFillColor(Color.BLACK);
            }

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1); // add the data sets

            // create a data object with the data sets
            LineData data = new LineData(dataSets);

            // set data
            lineChart.setData(data);
        }
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
