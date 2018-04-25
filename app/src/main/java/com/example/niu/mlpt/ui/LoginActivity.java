package com.example.niu.mlpt.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.niu.mlpt.R;
import com.example.niu.mlpt.Utils.SharePreferenceUtils;
import com.example.niu.mlpt.Utils.ToastUtils;
import com.example.niu.mlpt.view.ShowHideEditText;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

import static android.content.ContentValues.TAG;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.bt_login)
    TextView mLogin;
    @BindView(R.id.tv_login_pwd)
    TextView tvLoginPwd;
    @BindView(R.id.ll_login_type)
    LinearLayout llLoginType;
    @BindView(R.id.et_login_number)
    EditText etLoginNumber;
    @BindView(R.id.et_login_pwd)
    ShowHideEditText etLoginPwd;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_remember)
    TextView tvRemember;
    @BindView(R.id.ll_register_remember)
    RelativeLayout llRegisterRemember;
    @BindView(R.id.ll_login_pwd)
    LinearLayout llLoginPwd;
    @BindView(R.id.et_register_number)
    EditText etRegisterNumber;
    @BindView(R.id.bt_login_next)
    TextView btLoginNext;
    @BindView(R.id.ll_login_message)
    LinearLayout llLoginMessage;
    @BindView(R.id.fl_login)
    FrameLayout flLogin;
    @BindView(R.id.tv_login_message)
    TextView tvLoginMessage;
    @BindView(R.id.iv_login_close)
    ImageView ivLoginClose;
    @BindView(R.id.iv_login_type)
    ImageView ivLoginType;
    @BindView(R.id.iv_qq)
    ImageView ivQq;
    @BindView(R.id.iv_weixin)
    ImageView ivWeixin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

            initEvent();








        /*etLoginPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "onTextChanged2: chaesequence= " + s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 11) {

                    if (!isVaildPhoneNumber(s.toString())) {
                        Toast.makeText(getApplicationContext(), "请输入正确的号码", Toast.LENGTH_SHORT).show();

                    }
                }


            }
        });*/

    }

    private void initEvent() {
        Observable<CharSequence> mLoginNumberObservable = RxTextView.textChanges(etLoginNumber);
        Observable<CharSequence> mLoginPwdObservable = RxTextView.textChanges(etLoginPwd);

        Observable.combineLatest(mLoginNumberObservable, mLoginPwdObservable, new BiFunction<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(CharSequence charSequence, CharSequence charSequence2) throws Exception {
                String phoneNumber = charSequence.toString();
                String pwd = charSequence2.toString();

                Log.d(TAG, "apply: phoneNumber= "+phoneNumber+" size= "+phoneNumber.length());
                if (phoneNumber.length()==11){

                 return isVaildPhoneNumber(phoneNumber)&&pwd.length()>0;

                }
                //isVaildPhoneNumber()
                return false;

            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean){
                    mLogin.setEnabled(true);
                    mLogin.setTextColor(Color.BLACK);

                }else{

                    mLogin.setEnabled(false);
                    mLogin.setTextColor(Color.WHITE);
                }
            }
        });




    }

    @OnClick({R.id.tv_remember,R.id.tv_login_pwd, R.id.tv_login_message, R.id.bt_login,R.id.iv_qq,R.id.iv_weixin,R.id.tv_register

    ,R.id.iv_login_close
    })
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {

            case R.id.iv_login_close:

                finish();
                break;
            case R.id.tv_login_message:

                tvLoginMessage.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                tvLoginPwd.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                tvLoginPwd.setTextColor(Color.argb(255, 0, 0, 0));
                tvLoginMessage.setTextColor(Color.BLACK);
                llLoginMessage.setVisibility(View.VISIBLE);
                llLoginPwd.setVisibility(View.GONE);
                break;

            case R.id.tv_login_pwd:

                tvLoginPwd.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                tvLoginMessage.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                tvLoginMessage.setTextColor(Color.argb(255, 0, 0, 0));
                tvLoginPwd.setTextColor(Color.BLACK);
                llLoginPwd.setVisibility(View.VISIBLE);
                llLoginMessage.setVisibility(View.GONE);
                break;
            case R.id.bt_login:
                ToastUtils.showToast("登录成功", Toast.LENGTH_SHORT);

                SharePreferenceUtils.put("isLogin",true);
                finish();
                break;
            case R.id.iv_qq:
                ToastUtils.showToast("qq login",Toast.LENGTH_SHORT);
                break;

            case R.id.iv_weixin:
                ToastUtils.showToast("weixin login",Toast.LENGTH_SHORT);

                break;

            case R.id.tv_register:
                 intent = new Intent(this,RegisterAvtivity.class);
                startActivity(intent);
                break;

            case R.id.tv_remember:
                 intent = new Intent(this,RememberActivity.class);
                startActivity(intent);
                break;
        }


    }

    public boolean isVaildPhoneNumber(String number) {

        Log.d(TAG, "isVaildPhoneNumber: number= " + number);

        String pattren = "[1][3-8][0-9]{0,9}";
        boolean result = number.matches(pattren);

        if (!result){
            ToastUtils.showToast("请输入正确手机号码",Toast.LENGTH_SHORT);

        }
        return result;
    }

}
