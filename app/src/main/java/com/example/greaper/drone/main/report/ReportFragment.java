package com.example.greaper.drone.main.report;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.greaper.drone.R;
import com.example.greaper.drone.main.drone.TabDroneFragment;
import com.example.greaper.drone.ui.ReportActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReportFragment extends Fragment {


    @BindView(R.id.txt_date_1)
    TextView txtDate1;
    @BindView(R.id.img_expand_1)
    ImageView imgExpand1;
    @BindView(R.id.layout_date_1)
    RelativeLayout layoutDate1;
    @BindView(R.id.layout_content_1)
    LinearLayout layoutContent1;
    @BindView(R.id.txt_date_2)
    TextView txtDate2;
    @BindView(R.id.img_expand_2)
    ImageView imgExpand2;
    @BindView(R.id.layout_date_2)
    RelativeLayout layoutDate2;
    @BindView(R.id.txt_date_3)
    TextView txtDate3;
    @BindView(R.id.img_expand_3)
    ImageView imgExpand3;
    @BindView(R.id.layout_date_3)
    RelativeLayout layoutDate3;
    Unbinder unbinder;

    private OnReportFragmentListener onReportFragmentListener;
    private Context context;

    public static ReportFragment newInstance() {
        return new ReportFragment();
    }

    public ReportFragment() {
        // Required empty public constructor
    }

    public void setOnReportFragmentListener(OnReportFragmentListener onReportFragmentListener) {
        this.onReportFragmentListener = onReportFragmentListener;
    }

    public interface OnReportFragmentListener {
        void onClickItem();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_report, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.layout_date_1, R.id.layout_date_2, R.id.layout_date_3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_date_1:
                startActivity(new Intent(getContext(), ReportActivity.class));
                break;
            case R.id.layout_date_2:
                startActivity(new Intent(getContext(), ReportActivity.class));
                break;
            case R.id.layout_date_3:
                startActivity(new Intent(getContext(), ReportActivity.class));
                break;
        }
    }
}
