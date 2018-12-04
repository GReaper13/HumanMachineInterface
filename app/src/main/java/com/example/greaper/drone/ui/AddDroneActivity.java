package com.example.greaper.drone.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.greaper.drone.R;
import com.example.greaper.drone.data.Drone;
import com.example.greaper.drone.main.drone.DroneAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddDroneActivity extends AppCompatActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.layout_header)
    RelativeLayout layoutHeader;
    @BindView(R.id.rvDrone)
    RecyclerView rvDrone;

    DroneAdapter adapter;
    List<Drone> droneList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drone);
        ButterKnife.bind(this);
        init();
    }

    public void backClick(View view) {
        finish();
    }

    private void init() {
        for (int i = 0; i < 10; i++) {
            droneList.add(new Drone(i,randomString()));
        }
        adapter = new DroneAdapter(this, droneList);
        rvDrone.setAdapter(adapter);
        rvDrone.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnItemClickListener(position -> {
            String nameDrone = droneList.get(position).getName();
            Dialog dialog = new Dialog(AddDroneActivity.this);
            dialog.setContentView(R.layout.dialog_add_drone);
            Button btnOk = dialog.findViewById(R.id.btn_ok);
            Button btnCancel = dialog.findViewById(R.id.btn_cancel);
            TextView txtName = dialog.findViewById(R.id.txt_name_drone);
            EditText edtPassword = dialog.findViewById(R.id.edt_password);
            txtName.setText(getString(R.string.code) + " " + nameDrone);
            btnCancel.setOnClickListener(view -> {
                dialog.dismiss();
            });
            btnOk.setOnClickListener(view -> {
                String password = edtPassword.getText().toString();
                if (password.isEmpty()) {
                    Toast.makeText(AddDroneActivity.this, getString(R.string.count_drone_error), Toast.LENGTH_SHORT).show();
                } else {
                    dialog.dismiss();
                    Intent intent = new Intent();
                    intent.putExtra("code", nameDrone);
                    setResult(RESULT_OK, intent);
                    Toast.makeText(AddDroneActivity.this, getString(R.string.add_suceesfully), Toast.LENGTH_SHORT).show();
                    AddDroneActivity.this.finish();
                }
            });
            dialog.show();
        });
    }

    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static Random rnd = new Random();
    String randomString(){
        StringBuilder sb = new StringBuilder(6);
        for(int i = 0; i < 6; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }
}
