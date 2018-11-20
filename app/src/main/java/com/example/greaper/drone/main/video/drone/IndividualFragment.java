package com.example.greaper.drone.main.video.drone;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.greaper.drone.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class IndividualFragment extends Fragment {

    @BindView(R.id.img_expand_1)
    ImageView imgExpand1;
    @BindView(R.id.layout_content_1)
    LinearLayout layoutContent1;
    Unbinder unbinder;

    boolean isExpand = false;

    public IndividualFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_individual, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.layout_date_1)
    public void onViewClicked() {
        if (isExpand) {
            imgExpand1.setImageResource(R.drawable.ic_next);
            layoutContent1.setVisibility(View.GONE);
            isExpand = false;
        } else {
            imgExpand1.setImageResource(R.drawable.ic_expand);
            layoutContent1.setVisibility(View.VISIBLE);
            isExpand = true;
        }
    }
}
