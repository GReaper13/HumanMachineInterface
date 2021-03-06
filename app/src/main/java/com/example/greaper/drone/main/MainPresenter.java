package com.example.greaper.drone.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;

import com.example.greaper.drone.main.dashboard.DashboardFragment;
import com.example.greaper.drone.main.drone.DroneFragment;
import com.example.greaper.drone.main.drone.TabDroneFragment;
import com.example.greaper.drone.main.profile.ProfileFragment;
import com.example.greaper.drone.main.report.ReportFragment;
import com.example.greaper.drone.main.video.VideoFragment;
import com.example.greaper.drone.utils.AppUtils;
import com.example.greaper.drone.utils.Const;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter {
    private Context context;
    private MainView mainView;

    public MainPresenter(Context context, MainView mainView) {
        this.context = context;
        this.mainView = mainView;
    }

    public void changeViewPager(int position) {
        switch (position) {
            case 0:
                mainView.showDashboard(true);
                mainView.showAccount(false);
                mainView.showVideo(false);
                mainView.showDrone(false);
                mainView.showReport(false);
                break;
            case 1:
                mainView.showDashboard(false);
                mainView.showAccount(false);
                mainView.showVideo(true);
                mainView.showDrone(false);
                mainView.showReport(false);
                break;
            case 2:
                mainView.showDashboard(false);
                mainView.showAccount(false);
                mainView.showVideo(false);
                mainView.showDrone(true);
                mainView.showReport(false);
                break;
            case 3:
                mainView.showDashboard(false);
                mainView.showAccount(false);
                mainView.showVideo(false);
                mainView.showDrone(false);
                mainView.showReport(true);
                break;
            case 4:
                mainView.showDashboard(false);
                mainView.showAccount(true);
                mainView.showVideo(false);
                mainView.showDrone(false);
                mainView.showReport(false);
                break;
        }
    }

    public List<Fragment> getListFragment() {
        List<Fragment> fragmentList = new ArrayList<>();
        if (AppUtils.getCurrentRole().equals(Const.ADMIN)) {
            fragmentList.add(new DashboardFragment());
            fragmentList.add(new VideoFragment());
            ReportFragment reportFragment = new ReportFragment();
            reportFragment.setOnReportFragmentListener(() -> {
                mainView.clickReport();
            });
            fragmentList.add(reportFragment);
            fragmentList.add(new ProfileFragment());
        } else {
            fragmentList.add(TabDroneFragment.newInstance());
            fragmentList.add(new ProfileFragment());
        }
        return fragmentList;
    }



}
