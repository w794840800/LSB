package com.example.niu.lsb.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import static android.content.ContentValues.TAG;

/**
 * Created by NIU on 2018/4/19.
 */

public class CircleRunView extends View {

    int mRadius;
   Paint mPaint1,mPaint2;

    public CircleRunView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CircleRunView(Context context) {
        super(context);
       initView(context);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        Log.d(TAG, "onMeasure: width= "+widthSize+" height= "+heightSize);
        if (widthMeasureSpec == MeasureSpec.EXACTLY&&heightMeasureSpec ==MeasureSpec.EXACTLY){

            setMeasuredDimension(widthSize,heightSize);

        }




    }

    private void initView(Context context) {

        mPaint1 = new Paint();
        mPaint1.setColor(Color.RED);
        mPaint2 = new Paint();
        mPaint2.setColor(Color.BLACK);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();

     //  canvas.translate(getMeasuredWidth()/2,getMeasuredHeight()/2);

        mRadius = Math.min(width,height)/2;
        int mCircleX,mCircleY;

        mCircleX = getWidth()/2;
        mCircleY = getHeight()/2;
        Log.d(TAG, "onDraw: width= "+getWidth()+" height= "+getHeight()+" "+getMeasuredWidth()+" "+getMeasuredHeight()+
                " top= "+getTop()+" left= "+getLeft()+" mRadius= "+mRadius+" mCircleX= "+mCircleX+" mCircleY= "+mCircleY);
        //mPaint1.setColor(Color.RED);
        canvas.drawCircle(mCircleX,mCircleY,mRadius,mPaint1);
        //mPaint1.setColor(Color.BLACK);
        mPaint1.setStrokeWidth(20);
        canvas.drawPoint(getWidth()/2+mRadius,mRadius,mPaint2);
        ObjectAnimator animator = ObjectAnimator.ofFloat(this,"rotation",0,360);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setDuration(3000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();


    }
}
