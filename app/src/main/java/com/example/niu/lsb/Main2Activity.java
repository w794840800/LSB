package com.example.niu.lsb;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

public class Main2Activity extends AppCompatActivity {
Button button1,button2;
    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        button1 = (Button) findViewById(R.id.hello1);
        button2 = (Button) findViewById(R.id.hello2);
        relativeLayout = (RelativeLayout) findViewById(R.id.rl);
        ViewGroup.LayoutParams layoutParams =  relativeLayout.getLayoutParams();
        int height = layoutParams.width;
        final int startY = button1.getHeight();
        int change = button2.getHeight();
          final int endY = height-change;
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewGroup.LayoutParams layoutParams =  relativeLayout.getLayoutParams();
                int height = layoutParams.height;
                Log.d("TAG", "onCreate: height= "+height+"");

                ObjectAnimator expand =  ObjectAnimator.ofFloat(relativeLayout,"translationY",0,-button2.getHeight());
                expand.setDuration(3000)
                        .setRepeatCount(ValueAnimator.INFINITE);

                       expand .start();
            }
        });


    }
}
