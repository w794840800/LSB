package com.example.niu.mlpt.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

import static android.content.ContentValues.TAG;

/**
 * Created by NIU on 2018/4/26.
 */

public class PaotuiScrollView extends ScrollView{

    Rect mNormalRel;

    boolean isCanPullDown,isCanPullUp;
    View onlyChildView;
    int statY;
    public PaotuiScrollView(Context context) {
        super(context);
    }

    public PaotuiScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
         onlyChildView = getChildAt(0);
         mNormalRel = new Rect();
         mNormalRel.set(onlyChildView.getLeft(),onlyChildView.getTop(),onlyChildView.getRight(),onlyChildView.getBottom());




    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {


        switch (ev.getAction()){

            case MotionEvent.ACTION_DOWN:

                statY = (int) ev.getY();

                break;


            case MotionEvent.ACTION_MOVE:
               // Log.d(TAG, "dispatchTouchEvent: scroolY= "+onlyChildView.getScrollY());
                int nowY = (int) ev.getY();

                int delY = nowY-statY;
                boolean isCanMove = (isCanPullDown&&delY>0)

                        ||(isCanPullUp&&delY<0);

                onlyChildView.layout(0,mNormalRel.top+(int)(delY*0.5),mNormalRel.right, (int) (mNormalRel.bottom+(delY*0.5)));

                break;


            case MotionEvent.ACTION_UP:
                Log.d(TAG, "dispatchTouchEvent: scrollY= "+onlyChildView.getScrollY()+"  "+onlyChildView.getTop());

                ValueAnimator valueAnimator = ValueAnimator.ofInt(onlyChildView.getTop(),mNormalRel.top);
                valueAnimator.setDuration(200);
                valueAnimator.start();

                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {

                        int value = (int) animation.getAnimatedValue();
                        onlyChildView.layout(mNormalRel.left,value,mNormalRel.right,mNormalRel.bottom);

                    }
                });



                break;






        }




        return super.dispatchTouchEvent(ev);
    }


    public boolean isCanPullUp(){

        return onlyChildView.getScrollY()+getHeight()==onlyChildView.getMeasuredHeight();

    }

    public boolean isCanPullDown(){

        return onlyChildView.getScrollY()==0;

    }

}
