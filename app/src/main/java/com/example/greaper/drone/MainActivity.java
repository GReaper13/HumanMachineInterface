package com.example.greaper.drone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.greaper.drone.main.MainPagerAdapter;
import com.example.greaper.drone.main.MainPresenter;
import com.example.greaper.drone.main.MainView;
import com.example.greaper.drone.ui.ReportActivity;
import com.example.greaper.drone.utils.AppUtils;
import com.example.greaper.drone.utils.Const;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.layout_title)
    RelativeLayout layoutTitle;
    @BindView(R.id.vp_main)
    ViewPager vpMain;
    @BindView(R.id.img_dashboard)
    ImageView imgDashboard;
    @BindView(R.id.txt_dashboard)
    TextView txtDashboard;
    @BindView(R.id.line_dashboard)
    View lineDashboard;
    @BindView(R.id.layout_dashboard)
    LinearLayout layoutDashboard;
    @BindView(R.id.img_video)
    ImageView imgVideo;
    @BindView(R.id.txt_video)
    TextView txtVideo;
    @BindView(R.id.line_video)
    View lineVideo;
    @BindView(R.id.layout_video)
    LinearLayout layoutVideo;
    @BindView(R.id.img_drone)
    ImageView imgDrone;
    @BindView(R.id.txt_drone)
    TextView txtDrone;
    @BindView(R.id.line_drone)
    View lineDrone;
    @BindView(R.id.layout_drone)
    LinearLayout layoutDrone;
    @BindView(R.id.img_report)
    ImageView imgReport;
    @BindView(R.id.txt_report)
    TextView txtReport;
    @BindView(R.id.line_report)
    View lineReport;
    @BindView(R.id.layout_report)
    LinearLayout layoutReport;
    @BindView(R.id.img_account)
    ImageView imgAccount;
    @BindView(R.id.txt_account)
    TextView txtAccount;
    @BindView(R.id.line_account)
    View lineAccount;
    @BindView(R.id.layout_account)
    LinearLayout layoutAccount;
    @BindView(R.id.ll_category)
    LinearLayout llCategory;
    @BindView(R.id.img_search)
    ImageView imgSearch;
    @BindView(R.id.img_filter)
    ImageView imgFilter;
    @BindView(R.id.txt_title)
    TextView txtTitle;

    boolean isFilterByDate = true;
    @BindView(R.id.layout_option)
    LinearLayout layoutOption;

    private MainPresenter mainPresenter;
    private MainPagerAdapter mainPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        if (AppUtils.getCurrentRole().equals(Const.RANGER)) {
            layoutDashboard.setVisibility(View.GONE);
            layoutVideo.setVisibility(View.GONE);
            layoutReport.setVisibility(View.GONE);
        } else {
            layoutDrone.setVisibility(View.GONE);
        }
        mainPresenter = new MainPresenter(this, this);
        mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mainPagerAdapter.setFragmentList(mainPresenter.getListFragment());
        vpMain.setAdapter(mainPagerAdapter);
        vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (AppUtils.getCurrentRole().equals(Const.ADMIN)) {
                    switch (i) {
                        case 0:
                            mainPresenter.changeViewPager(0);
                            break;
                        case 1:
                            mainPresenter.changeViewPager(1);
                            break;
                        case 2:
                            mainPresenter.changeViewPager(3);
                            break;
                        case 3:
                            mainPresenter.changeViewPager(4);
                            break;
                    }
                } else {
                    switch (i) {
                        case 0:
                            mainPresenter.changeViewPager(2);
                            break;
                        case 1:
                            mainPresenter.changeViewPager(4);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void showDashboard(boolean enable) {
        if (enable) {
            txtTitle.setText(R.string.dashboard);
            lineDashboard.setVisibility(View.VISIBLE);
            imgDashboard.setImageResource(R.drawable.ic_graph_blue);
            txtDashboard.setTextColor(getResources().getColor(R.color.main_color));
        } else {
            lineDashboard.setVisibility(View.GONE);
            imgDashboard.setImageResource(R.drawable.ic_graph_black);
            txtDashboard.setTextColor(getResources().getColor(R.color.text_normal));
        }
    }

    @Override
    public void showVideo(boolean enable) {
        if (enable) {
            txtTitle.setText(R.string.video);
            lineVideo.setVisibility(View.VISIBLE);
            imgVideo.setImageResource(R.drawable.ic_video_blue);
            txtVideo.setTextColor(getResources().getColor(R.color.main_color));
        } else {
            lineVideo.setVisibility(View.GONE);
            imgVideo.setImageResource(R.drawable.ic_video_black);
            txtVideo.setTextColor(getResources().getColor(R.color.text_normal));
        }
    }

    @Override
    public void showDrone(boolean enable) {
        if (enable) {
            txtTitle.setText(R.string.drone);
            lineDrone.setVisibility(View.VISIBLE);
            imgDrone.setImageResource(R.drawable.ic_drone_blue);
            txtDrone.setTextColor(getResources().getColor(R.color.main_color));
        } else {
            lineDrone.setVisibility(View.GONE);
            imgDrone.setImageResource(R.drawable.ic_drone_black);
            txtDrone.setTextColor(getResources().getColor(R.color.text_normal));
        }
    }

    @Override
    public void showReport(boolean enable) {
        if (enable) {
            txtTitle.setText(R.string.report);
            lineReport.setVisibility(View.VISIBLE);
            imgReport.setImageResource(R.drawable.ic_report_blue);
            txtReport.setTextColor(getResources().getColor(R.color.main_color));
        } else {

            lineReport.setVisibility(View.GONE);
            imgReport.setImageResource(R.drawable.ic_report_black);
            txtReport.setTextColor(getResources().getColor(R.color.text_normal));
        }
    }

    @Override
    public void showAccount(boolean enable) {
        if (enable) {
            txtTitle.setText(R.string.account);
            lineAccount.setVisibility(View.VISIBLE);
            imgAccount.setImageResource(R.drawable.ic_account_blue);
            txtAccount.setTextColor(getResources().getColor(R.color.main_color));
        } else {
            lineAccount.setVisibility(View.GONE);
            imgAccount.setImageResource(R.drawable.ic_account_black);
            txtAccount.setTextColor(getResources().getColor(R.color.text_normal));
        }
    }

    @Override
    public void clickReport() {
        startActivity(new Intent(MainActivity.this, ReportActivity.class));
    }

    @OnClick({R.id.layout_dashboard, R.id.layout_video, R.id.layout_drone, R.id.layout_report, R.id.layout_account, R.id.img_search, R.id.img_filter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_dashboard:
                mainPresenter.changeViewPager(0);
                vpMain.setCurrentItem(0);
                break;
            case R.id.layout_video:
                mainPresenter.changeViewPager(1);
                vpMain.setCurrentItem(1);
                break;
            case R.id.layout_drone:
                mainPresenter.changeViewPager(2);
                vpMain.setCurrentItem(0);
                break;
            case R.id.layout_report:
                mainPresenter.changeViewPager(3);
                vpMain.setCurrentItem(2);
                break;
            case R.id.layout_account:
                mainPresenter.changeViewPager(4);
                if (AppUtils.getCurrentRole().equals(Const.ADMIN)) {
                    vpMain.setCurrentItem(3);
                } else {
                    vpMain.setCurrentItem(1);
                }
                break;
            case R.id.img_search:
                break;
            case R.id.img_filter:
                PopupMenu popupMenu = new PopupMenu(this, imgFilter);
                popupMenu.getMenuInflater().inflate(R.menu.sort_menu, popupMenu.getMenu());
                if (isFilterByDate) {
                    popupMenu.getMenu().getItem(0).setChecked(true);
                } else {
                    popupMenu.getMenu().getItem(1).setChecked(true);
                }
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.sort_by_date) {
                            isFilterByDate = true;
                        } else {
                            isFilterByDate = false;
                        }
                        return true;
                    }
                });
                break;
        }
    }
}
