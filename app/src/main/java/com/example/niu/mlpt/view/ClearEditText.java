package com.example.niu.mlpt.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by NIU on 2018/4/23.
 */

public class ClearEditText extends android.support.v7.widget.AppCompatEditText
        implements View.OnFocusChangeListener,TextWatcher{

     private Drawable mCleanDrawble;
     private boolean mHasFoucus;
    public ClearEditText(Context context) {
        super(context);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        setOnFocusChangeListener(this);
        addTextChangedListener(this);
     setClearEditIconVisibity(false);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

         if (event.getAction()==MotionEvent.ACTION_UP){

             if (getCompoundDrawables()[2]!=null){
                  //左边界
                 int left = getWidth()-getTotalPaddingRight();
                 int right = getWidth()-getPaddingRight();
                 if (event.getX()>left&&event.getX()<right){
                     setText("");

                 }



             }



         }


        return super.onTouchEvent(event);
    }

    @Override
    public void onFocusChange(View view, boolean b) {

        if (b &&getText().length()>0){

  setClearEditIconVisibity(true);

        }else{
            setClearEditIconVisibity(false);

        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        setClearEditIconVisibity(getText().length()>0);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    public void setClearEditIconVisibity(boolean b){
        Drawable drawable  = b ? mCleanDrawble:null;
        setCompoundDrawables(getCompoundDrawables()[0],getCompoundDrawables()[1],drawable,getCompoundDrawables()[2]);

    }

}
