package com.example.niu.mlpt.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.niu.mlpt.R;
import com.example.niu.mlpt.Utils.ToastUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class VerificationCodeActivity extends AppCompatActivity {

    @BindView(R.id.iv_verification_back)
    ImageView ivVerificationBack;
    @BindView(R.id.iv_verification)
    ImageView ivVerification;
    @BindView(R.id.tv_verification)
    TextView tvVerification;
    @BindView(R.id.tv_verification_item1)
    TextView tvVerificationItem1;
    @BindView(R.id.tv_verification_item2)
    TextView tvVerificationItem2;
    @BindView(R.id.tv_verification_item3)
    TextView tvVerificationItem3;
    @BindView(R.id.tv_verification_item4)
    TextView tvVerificationItem4;
    @BindView(R.id.ll_verificaion)
    LinearLayout llVerificaion;
    @BindView(R.id.tv_verification_phone)
    TextView tvVerificationPhone;
    @BindView(R.id.tv_verification_try)
    TextView tvVerificationTry;
    @BindView(R.id.tv_keyboard1)
    TextView tvKeyboard1;
    @BindView(R.id.tv_keyboard2)
    TextView tvKeyboard2;
    @BindView(R.id.tv_keyboard3)
    TextView tvKeyboard3;
    @BindView(R.id.tv_keyboard4)
    TextView tvKeyboard4;
    @BindView(R.id.tv_keyboard5)
    TextView tvKeyboard5;
    @BindView(R.id.tv_keyboard6)
    TextView tvKeyboard6;
    @BindView(R.id.tv_keyboard7)
    TextView tvKeyboard7;
    @BindView(R.id.tv_keyboard8)
    TextView tvKeyboard8;
    @BindView(R.id.tv_keyboard9)
    TextView tvKeyboard9;
    @BindView(R.id.tv_keyboard_null)
    TextView tvKeyboardNull;
    @BindView(R.id.tv_keyboard0)
    TextView tvKeyboard0;
    @BindView(R.id.tv_keyboard_del)
    TextView tvKeyboardDel;
     boolean isFirstEnter;
    private int mPosition = 0;
    List<String> mInputVerificaionList;
    List<TextView> verificationItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_code);
        mInputVerificaionList = new ArrayList<>();
        verificationItemList = new ArrayList<>();
        ButterKnife.bind(this);
        if (!isFirstEnter){

            beginSendVerificaion();
            isFirstEnter  = true;
        }

        verificationItemList.add(tvVerificationItem1);
        verificationItemList.add(tvVerificationItem2);
        verificationItemList.add(tvVerificationItem3);
        verificationItemList.add(tvVerificationItem4);
    }

    @OnClick({R.id.tv_verification_try,R.id.tv_keyboard0,R.id.tv_keyboard1,R.id.tv_keyboard2,R.id.tv_keyboard3,R.id.tv_keyboard4
    ,R.id.tv_keyboard5,R.id.tv_keyboard6,R.id.tv_keyboard7,R.id.tv_keyboard8,R.id.tv_keyboard9,R.id.tv_verification_item1,
            R.id.tv_verification_item2,R.id.tv_verification_item3,R.id.tv_verification_item4,R.id.tv_keyboard_del
            ,R.id.tv_verification_phone,R.id.iv_verification_back
    })
    public void onViewClicked(View view) {

        switch (view.getId()) {

            case  R.id.iv_verification_back:
                finish();
                break;


            case R.id.tv_verification_try:

                beginSendVerificaion();


                break;
                    case R.id.tv_keyboard0:
                    case R.id.tv_keyboard1:
                    case R.id.tv_keyboard2:
                    case R.id.tv_keyboard3:
                    case R.id.tv_keyboard4:
                    case R.id.tv_keyboard5:
                    case R.id.tv_keyboard6:
                    case R.id.tv_keyboard7:
                    case R.id.tv_keyboard8:
                    case R.id.tv_keyboard9:
                       TextView textView = (TextView) view;
                       if (mPosition<4){
                       mInputVerificaionList.add(textView.getText().toString());
                            mPosition++;
                         showVeritification();
                       }


                   //ToastUtils.showToast(((TextView)view).getText().toString(), Toast.LENGTH_SHORT);
                   break;

            case R.id.tv_keyboard_del:

                mPosition--;
                if (mPosition<0){

                    mPosition=0;
                }
                Log.d("tag", "onViewClicked: mPosition= "+mPosition);
                if (mInputVerificaionList.size()!=0){
                mInputVerificaionList.remove(mPosition);
                showVeritification();
                }
                break;

            case R.id.tv_verification_phone:

                ToastUtils.showToast(" "+mPosition,Toast.LENGTH_SHORT);

                break;


        }

    }

    private void beginSendVerificaion() {

        Observable.intervalRange(1, 60, 0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                                   @Override
                                   public void accept(Disposable disposable) throws Exception {
                                       tvVerificationTry.setEnabled(false);
                                   }
                               }
                ).doOnNext(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {

                tvVerificationTry.setText(60 - aLong + "秒后重发");
            }
        }).doOnComplete(new Action() {
            @Override
            public void run() throws Exception {
                tvVerificationTry.setEnabled(true);
            }
        }).subscribe();

    }

    private void showVeritification() {

        clealAllTextViewText();

        if (mInputVerificaionList!=null&&mInputVerificaionList.size()>0){
        String number;
        for (int i = 0; i < mInputVerificaionList.size(); i++) {
             number = mInputVerificaionList.get(i);
            Log.d("TAG", "showVeritification:  verificationItemList item1 =="+(verificationItemList.get(0)==null));

            if (mInputVerificaionList.get(i)!=null){
                verificationItemList.get(i).setText(number);
            }

        }

    }

    }

    private void clealAllTextViewText() {

        for (TextView tv:
             verificationItemList) {
            tv.setText("");

        }
    }


}
