package com.example.niu.mlpt.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.niu.mlpt.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
   @BindView(R.id.login_close)
    ImageView login_close;
    @BindView(R.id.bt_login)
    TextView mLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        login_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLogin.setEnabled(true);
            }
        });
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLogin.setEnabled(false);
            }
        });

    }


}
