package com.example.niu.mlpt.Utils;

import android.content.Context;

/**
 * Created by NIU on 2018/4/25.
 */

public class Utils {


    public static int dp2px(Context context,int size){

        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (scale*size+0.5f);

    }

    public static int px2dp(Context context,int size){

        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (size/scale+0.5f);

    }

}
