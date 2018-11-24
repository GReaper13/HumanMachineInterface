package com.example.greaper.drone.ui;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.greaper.drone.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountSettingsActivity extends AppCompatActivity {

    @BindView(R.id.edtEmail)
    EditText edtEmail;

    @BindView(R.id.edtPhone)
    EditText edtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.ivBack)
    public void back() {
        String email = edtEmail.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();

        if (email.isEmpty()) {
            alert("Email không được để trống");
            return;
        }
        if (phone.isEmpty()) {
            alert("Số điện thoại không được để trống");
            return;
        }

        if (!email.equals("thaitv210@gmail.com") || !phone.equals("0375371802")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Thoát");
            builder.setMessage("Thông tin đã được thay đổi. Bạn có muốn lưu lại không?");
            builder.setNegativeButton("Không ", (dialog, which) -> {
                dialog.cancel();
                finish();
            });
            builder.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    finish();
                    Toast.makeText(AccountSettingsActivity.this, "Đã thay đổi thông tin", Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            finish();
        }
    }

    @OnClick(R.id.btnSave)
    public void save() {
        String email = edtEmail.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();

        if (email.isEmpty()) {
            alert("Email không được để trống");
            return;
        }
        if (phone.isEmpty()) {
            alert("Số điện thoại không được để trống");
            return;
        }

        if (!email.equals("thaitv210@gmail.com") || !phone.equals("0375371802")) {
            alert(null, "Bạn có muốn thay đổi thông tin không?", (dialog, which) -> {
                dialog.cancel();
                finish();
                Toast.makeText(this, "Đã thay đổi thông tin", Toast.LENGTH_SHORT).show();
            });
        }

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

    private AlertDialog alert(String title, String message, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNegativeButton("Huỷ", (dialog, which) -> {
            dialog.cancel();
        });
        builder.setPositiveButton("OK", onClickListener);
        AlertDialog dialog = builder.create();
        dialog.show();
        return dialog;
    }
}
