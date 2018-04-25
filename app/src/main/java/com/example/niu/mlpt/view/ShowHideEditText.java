package com.example.niu.mlpt.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ImageFormat;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import static android.content.ContentValues.TAG;

/**
 * Created by NIU on 2018/4/24.
 */

@SuppressLint("AppCompatCustomView")
public class ShowHideEditText extends EditText  implements View.OnFocusChangeListener,TextWatcher {
     boolean isShow = false;
    Bitmap mBitmap;
    public ShowHideEditText(Context context) {
        super(context);
    }

    public ShowHideEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        //mBitmap = getCompoundDrawables()[2];
     addTextChangedListener(this);
     setOnFocusChangeListener(this);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

       if (event.getAction()==MotionEvent.ACTION_UP){
           if (getCompoundDrawables()[2]!=null) {
               int left = getWidth() - getTotalPaddingRight();
               int right = getWidth() - getPaddingRight();

               if (event.getX() > left && event.getX() < right) {

                   showOrHide();

               }


           }
       }

        return super.onTouchEvent(event);


    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        Log.d(TAG, "onTextChanged1: chaesequence= "+charSequence.toString());


    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onFocusChange(View view, boolean b) {

    }
    /**
     * 密码显示或隐藏 （切换）
     */
    private void showOrHide(){
        //记住光标开始的位置
        int pos = getSelectionStart();
        if(getInputType()!= (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)){//隐藏密码
            setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }else{//显示密码

             setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }
        setSelection(pos);

    }

}
