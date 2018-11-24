package com.example.greaper.drone.ui;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.greaper.drone.R;
import com.example.greaper.drone.data.Auth;
import com.example.greaper.drone.data.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePasswordActivity extends AppCompatActivity {

    @BindView(R.id.edtOldPassword)
    EditText edtOldPassword;

    @BindView(R.id.edtNewPassword)
    EditText edtNewPassword;

    @BindView(R.id.edtConfirmPassword)
    EditText edtConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.ivBack)
    public void back() {
        finish();
    }

    @OnClick(R.id.btnSave)
    public void save() {
        String oldPw = edtOldPassword.getText().toString().trim();
        String newPw = edtNewPassword.getText().toString().trim();
        String confirm = edtConfirmPassword.getText().toString().trim();

        if (oldPw.length() < 6) {
            alert("Mật khẩu cũ không đúng");
            return;
        }
        if (newPw.length() < 6) {
            alert("Mật khẩu có tối thiểu 6 ký tự");
            return;
        }
        if (!confirm.equals(newPw)) {
            alert("Mật khẩu không khớp");
            return;
        }

        User currentUser = Auth.getInstance().getCurrentUser();
        if (currentUser == null) {
            Toast.makeText(this, "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "Đã đổi mật khẩu", Toast.LENGTH_SHORT).show();
        finish();
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
}
