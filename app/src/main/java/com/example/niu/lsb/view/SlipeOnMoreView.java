package com.example.niu.lsb.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by NIU on 2018/4/20.
 */

public class SlipeOnMoreView extends View {
    int mWidth;
    int mHeight;
    int mLineWidth;
    int mLineHeight;
    Paint mLinePaint;

    public SlipeOnMoreView(Context context) {
        super(context);
    }

    public SlipeOnMoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        mLinePaint = new Paint();
        mLinePaint.setStrokeWidth(5);
        mLinePaint.setColor(Color.rgb(221,221,221));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mWidth = getWidth();
        mHeight = getHeight();
        mLineWidth = getWidth()/2;
        int startX = mWidth/2-30;
        int endX = mWidth/2+30;
        int startY = mHeight-10;
        int endY = startY-30;
        //canvas.drawLine(startX,startY,endX,startY,mLinePaint);
        //canvas.drawLine(startX,endY,endX,endY,mLinePaint);
      // canvas.drawLine(getHeight()-20,);

        canvas.drawLine(startX,getHeight()/2-10,endX,getHeight()/2-10,mLinePaint);
        canvas.drawLine(startX,getHeight()/2,endX,getHeight()/2,mLinePaint);
    }
}
