package com.example.niu.lsb.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.niu.lsb.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HelpSendActivity extends AppCompatActivity {

    @BindView(R.id.iv_back)
    ImageView mImageViewBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_send);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked(View view){

        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;

        }


    }


}
