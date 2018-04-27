package com.example.niu.mlpt.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.niu.mlpt.R;
import com.example.niu.mlpt.Utils.ToastUtils;

import static android.content.ContentValues.TAG;

/**
 * Created by NIU on 2018/4/23.
 */

public class ClearEditText extends EditText
        implements View.OnFocusChangeListener,TextWatcher{

    Drawable drawable = getResources().getDrawable(R.drawable.clear);

    public ClearEditText(Context context) {
        super(context);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction()==MotionEvent.ACTION_UP){

            Log.d(TAG, "onTouchEvent: getRight= "+getRight()+" getWidth= "+getWidth()+" getLeft= "+getLeft()+" getX= "+event.getX());
            int drawableLeft = getWidth()-getTotalPaddingRight();
                    //getWidth()-getTotalPaddingRight();
            int drawableRight = getWidth()-getPaddingRight();
            if (event.getX()>drawableLeft&&event.getX()<drawableRight){

                setText("");

            }

        }

        return super.onTouchEvent(event);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        setClearEditTextVisibity(s.toString().length()>0);
    }

    private void setClearEditTextVisibity(boolean b) {

        Log.d("wanglei", "setClearEditTextVisibity: b= "+b);

        if (b){
            if (drawable==null) {
                drawable = getResources().getDrawable(R.drawable.clear);


            }drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

            setCompoundDrawables(getCompoundDrawables()[0],getCompoundDrawables()[1],drawable,getCompoundDrawables()[3]);
           //invalidate();
           // requestLayout();
        }else{

         //   drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), (int) (drawable.getMinimumHeight()));
            setCompoundDrawables(getCompoundDrawables()[0],getCompoundDrawables()[1],null,getCompoundDrawables()[3]);

        }

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    drawable  = getCompoundDrawables()[2];
    }

}
