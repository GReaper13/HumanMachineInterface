package com.example.greaper.drone.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.greaper.drone.MainActivity;
import com.example.greaper.drone.R;
import com.example.greaper.drone.data.Auth;
import com.example.greaper.drone.data.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.edtUsername)
    EditText edtUserName;

    @BindView(R.id.edtPassword)
    EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnLogin)
    public void login() {
        String username = edtUserName.getText().toString();
        String password = edtPassword.getText().toString();

        String error = validate(username, password);
        if (error != null) {
            alert(error);
            return;
        }

        Auth.getInstance().login(username, password, new Auth.Callback() {
            @Override
            public void onFinish(Auth.Task<User> task) {
                if (task.isSuccessful()) {
                    navigate();
                } else {
                    alert("Username and password not match any account");
                }
            }
        });
    }


    private String validate(String username, String password) {
        if (username == null || username.isEmpty()) {
            return "Username is not valid";
        }
        if (password == null || password.length() < 6) {
            return "Password is too short";
        }
        return null;
    }


    private AlertDialog alert(String message) {
        return alert(null, message);
    }

    private AlertDialog alert(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNegativeButton("OK", (dialog, which) -> {
            dialog.cancel();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        return dialog;
    }

    private void navigate() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
