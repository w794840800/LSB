package com.example.niu.lsb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.niu.lsb.Utils.Api;
import com.example.niu.lsb.ui.WebViewActivity;
import com.example.niu.lsb.view.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by NIU on 2018/4/19.
 */

public class SlideMenuFragment extends Fragment {

    DrawerLayout mActivityDrawer;
   /* @BindView(R.id.slide_close)
    ImageButton slide_close;*/
    @BindView(R.id.slide_mycoupon)
    LinearLayout mCoupon;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.slide_layout,container,false);
       // slide_close = (ImageButton) view.findViewById(R.id.slide_close);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();
        mActivityDrawer = (DrawerLayout) getActivity().findViewById(R.id.main_drawer);
        //Toast.makeText(getContext(),"mActivityDrawer= "+(mActivityDrawer==null),Toast.LENGTH_SHORT).show();
        /*slide_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  mActivityDrawer.closeDrawers();

            }
        });*/
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @OnClick(R.id.slide_mycoupon)
    public void onViewClick(View view){
        //Toast.makeText(getContext(),"tedtttt",Toast.LENGTH_SHORT).show();
        //mActivityDrawer.closeDrawers();
        Intent intent ;
        switch (view.getId()){


          case R.id.slide_mycoupon:

              Toast.makeText(getContext(),"test",Toast.LENGTH_LONG)
                      .show();
              intent = new Intent(getContext(), WebViewActivity.class);
              intent.putExtra("url", Api.baseUrl+Api.mycoupon);
              getActivity().startActivity(intent);


              break;

      }
    }

}
