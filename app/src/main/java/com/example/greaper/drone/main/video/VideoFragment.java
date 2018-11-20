package com.example.greaper.drone.main.video;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.greaper.drone.R;
import com.example.greaper.drone.main.video.drone.IndividualFragment;
import com.example.greaper.drone.main.video.total.TotalFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class VideoFragment extends Fragment {

    @BindView(R.id.txt_total)
    TextView txtTotal;
    @BindView(R.id.txt_drone)
    TextView txtDrone;
    @BindView(R.id.layout_title)
    LinearLayout layoutTitle;
    @BindView(R.id.bottom_line)
    View bottomLine;
    @BindView(R.id.layout_indicator)
    LinearLayout layoutIndicator;
    @BindView(R.id.vp_video)
    ViewPager vpVideo;
    Unbinder unbinder;
    private List<Fragment> fragmentList;
    private VideoPagerAdapter videoPagerAdapter;

    public VideoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private void initViews() {
        final DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        videoPagerAdapter = new VideoPagerAdapter(getChildFragmentManager());
        fragmentList = new ArrayList<>();
        fragmentList.add(new TotalFragment());
        fragmentList.add(new IndividualFragment());

        videoPagerAdapter.setFragmentList(fragmentList);
        vpVideo.setAdapter(videoPagerAdapter);
        vpVideo.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    bottomLine.animate().x(0).setDuration(200).start();
                } else {
                    bottomLine.animate().x(metrics.widthPixels/2).setDuration(200).start();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @OnClick({R.id.txt_total, R.id.txt_drone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_total:
                vpVideo.setCurrentItem(0, true);
                break;
            case R.id.txt_drone:
                vpVideo.setCurrentItem(1, true);
                break;
        }
    }
}
