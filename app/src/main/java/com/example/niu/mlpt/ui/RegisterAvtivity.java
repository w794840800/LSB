package com.example.niu.mlpt.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.niu.mlpt.R;
import com.example.niu.mlpt.Utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterAvtivity extends AppCompatActivity {

    @BindView(R.id.iv_register_back)
    ImageView ivRegisterBack;
    @BindView(R.id.iv_register_logo)
    ImageView ivRegisterLogo;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.et_register_phone)
    EditText etRegisterPhone;
    @BindView(R.id.bt_register_next)
    TextView btRegisterNext;
    @BindView(R.id.ll_register_info)
    LinearLayout llRegisterInfo;
    @BindView(R.id.ll_register_title)
    LinearLayout llRegisterTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_avtivity);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_register_back,R.id.bt_register_next})
    public void onViewClicked(View view) {
        Intent intent;

        switch (view.getId()){
            case R.id.iv_register_back:
                finish();
                break;
            case R.id.bt_register_next:
                //ToastUtils.showToast("next", Toast.LENGTH_SHORT);
                intent = new Intent(this,VerificationCodeActivity.class);
                startActivity(intent);
                break;


        }


    }
}
