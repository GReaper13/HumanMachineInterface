package com.example.greaper.drone.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.greaper.drone.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FAQActivity extends AppCompatActivity {

    @BindView(R.id.rvFAQ)
    RecyclerView rvFAQ;
    FAQAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        ButterKnife.bind(this);

        adapter = new FAQAdapter(this);
        rvFAQ.setAdapter(adapter);
        rvFAQ.setLayoutManager(new LinearLayoutManager(this));
    }

    @OnClick(R.id.ivBack)
    public void back() {
        finish();
    }
}
