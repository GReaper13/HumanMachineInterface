package com.example.greaper.drone.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.greaper.drone.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HelpActivity extends AppCompatActivity {

    @BindView(R.id.wvHelp)
    WebView wvHelp;

    @BindView(R.id.layoutLoading)
    View layoutLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ButterKnife.bind(this);
        wvHelp.getSettings().setJavaScriptEnabled(true);
        wvHelp.getSettings().setLoadsImagesAutomatically(true);
        wvHelp.loadUrl("https://www.cctvhd.com.au/hik-connect-setup-guide/");
        wvHelp.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        wvHelp.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                layoutLoading.setVisibility(View.GONE);
            }
        });
    }

    @OnClick(R.id.ivBack)
    public void back() {
        finish();
    }
}
