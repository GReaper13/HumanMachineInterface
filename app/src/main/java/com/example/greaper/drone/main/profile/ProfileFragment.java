package com.example.greaper.drone.main.profile;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.greaper.drone.R;
import com.example.greaper.drone.data.Auth;
import com.example.greaper.drone.data.FAQ;
import com.example.greaper.drone.data.User;
import com.example.greaper.drone.ui.AccountSettingsActivity;
import com.example.greaper.drone.ui.ChangePasswordActivity;
import com.example.greaper.drone.ui.FAQActivity;
import com.example.greaper.drone.ui.HelpActivity;
import com.example.greaper.drone.ui.ImageSettingsActivity;
import com.example.greaper.drone.ui.LoginActivity;
import com.example.greaper.drone.ui.NotificationSettingsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileFragment extends Fragment {

    @BindView(R.id.btnLogout)
    Button btnLogout;

    @BindView(R.id.tvName)
    TextView tvName;

    @BindView(R.id.tvRole)
    TextView tvRole;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);

        User user = Auth.getInstance().getCurrentUser();
//        tvName.setText("Tên: " + user.getName());
//        if (user.getRole().equals("admin"))
        if (user == null) {
            user = new User();
        }
        tvRole.setText(getString(R.string.position) + user.getName());


        return view;
    }

    @OnClick(R.id.btnLogout)
    public void logout() {
        Context context = getContext();
        if (context == null) {
            return;
        }
        new AlertDialog.Builder(context)
                .setTitle(getString(R.string.warning))
                .setMessage(getString(R.string.question_logout))
                .setNegativeButton(getString(R.string.cancel), (dialog, which) -> {
                    dialog.cancel();
                }).setPositiveButton(getString(R.string.ok), (dialog, which) -> {
            dialog.cancel();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }).create().show();
    }

    @OnClick(R.id.layoutImageSettings)
    public void imageSettings() {
        Intent intent = new Intent(getActivity(), ImageSettingsActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.layoutNotificationSettings)
    public void notificationSettings() {
        Intent intent = new Intent(getActivity(), NotificationSettingsActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.layoutAccountSettings)
    public void accountSettings() {
        Intent intent = new Intent(getActivity(), AccountSettingsActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.layoutChangePassword)
    public void changePassword() {
        Intent intent = new Intent(getActivity(), ChangePasswordActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.layoutHelp)
    public void help() {
        Intent intent = new Intent(getActivity(), HelpActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.layoutFAQ)
    public void faq() {
        Intent intent = new Intent(getActivity(), FAQActivity.class);
        startActivity(intent);
    }


}
