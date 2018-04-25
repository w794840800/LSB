package com.example.niu.mlpt.Utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by NIU on 2018/4/25.
 */

public class ToastUtils {
    public static Context mContext;
    public boolean isShow;
    public static Toast mToast;

    public static void init(Context context){

        mContext = context;
    }

    public static void showToast(String text,int duration){

        Log.d(TAG, "showToast: mContext==null "+(mContext==null));
        if (mContext!=null){

            if (mToast==null){
                mToast = Toast.makeText(mContext,text,duration);

            }else{
                mToast.setText(text);
                mToast.setDuration(duration);

            }

            mToast.show();




        }

    }
    public static void showToastWithGravity(String text,int duration,int gravity){

        if (mContext!=null){

            if (mToast==null){
                mToast = Toast.makeText(mContext,text,duration);

            }else{
                mToast.setText(text);

                mToast.setDuration(duration);

            }
            mToast.setGravity(gravity,0,0);
            mToast.show();




        }

    }
}
