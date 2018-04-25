package com.example.niu.mlpt;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.example.niu.mlpt.Utils.SharePreferenceUtils;
import com.example.niu.mlpt.Utils.ToastUtils;

/**
 * Created by NIU on 2018/4/25.
 */

public class BaseApplication extends Application {

    private static  Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtils.init(this);
        SharePreferenceUtils.init(this);
       context = this;
    }
    public static Context getBaseApplicationContext(){

        return context;

    }

}
