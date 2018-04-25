package com.example.niu.mlpt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.niu.mlpt.Utils.Api;
import com.example.niu.mlpt.Utils.SharePreferenceUtils;
import com.example.niu.mlpt.ui.LoginActivity;
import com.example.niu.mlpt.ui.WebViewActivity;

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
    @BindView(R.id.slide_get_coupon)
    LinearLayout mGetmCoupon;

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

    @OnClick({R.id.slide_mycoupon,R.id.slide_get_coupon,R.id.slide_invite,R.id.slide_svip
    ,R.id.slide_common_address,R.id.slide_collection_slide,R.id.slide_online_service
            ,R.id.slide_notification,R.id.slide_setting,R.id.tv_account_money,R.id.now_recharge,R.id.user_avatar
           , R.id.slide_allorder,R.id.slide_wait_pay,R.id.slide_wait_sure,R.id.slide_doing_order
    })
    public void onViewClick(View view){
        //Toast.makeText(getContext(),"tedtttt",Toast.LENGTH_SHORT).show();
        //mActivityDrawer.closeDrawers();
        Intent intent ;

        if (!(Boolean) SharePreferenceUtils.get("isLogin",false)){

            Intent intent1 = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent1);
            return;
        }
        switch (view.getId()){

          case R.id.slide_mycoupon:
              intent = new Intent(getContext(), WebViewActivity.class);
              intent.putExtra("url", Api.BASEURL+Api.SLIDE_MYCOUPON);
              getActivity().startActivity(intent);
              break;
            case R.id.slide_get_coupon:
                /*Toast.makeText(getContext(),"test slide_get_coupon",Toast.LENGTH_LONG)
                    .show();*/
                intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("url", Api.BASEURL+Api.SLIDE_GET_COUPON);
                getActivity().startActivity(intent);
                break;
            case R.id.slide_invite:
                intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("url", Api.BASEURL+Api.SLIDE_INVITE);
                getActivity().startActivity(intent);
                break;
            case R.id.slide_svip:
                intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("url", Api.BASEURL+Api.SLIDE_SVIP);
                getActivity().startActivity(intent);
                break;
            case R.id.slide_common_address:
                intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("url", Api.BASEURL+Api.SLIDE_COMMOM_ADDRESS);
                getActivity().startActivity(intent);
                break;
            case R.id.slide_collection_slide:
                intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("url", Api.BASEURL+Api.SLIDE_COLLECTION);
                getActivity().startActivity(intent);
                break;
            case R.id.slide_online_service:
                intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("url", Api.BASEURL+Api.SLIDE_ONLINE_SERVICE);
                getActivity().startActivity(intent);
                break;
            case R.id.slide_notification:
                intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("url", Api.BASEURL+Api.SLIDE_MESSAGE);
                getActivity().startActivity(intent);
                break;
            case R.id.slide_setting:
                intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("url", Api.BASEURL+Api.SLIDE_SETTINGS);
                getActivity().startActivity(intent);
                break;
            case R.id.tv_account_money:
                intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("url", Api.BASEURL+Api.SLIDE_USER_MOMEY);
                getActivity().startActivity(intent);
                break;
            case R.id.now_recharge:
                intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("url", Api.BASEURL+Api.SLIDE_RECHARGE);
                getActivity().startActivity(intent);
                break;

            case R.id.user_avatar:
                intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("url", Api.BASEURL+Api.SLIDE_USER_AVATAT);
                getActivity().startActivity(intent);
                break;

            case R.id.slide_allorder:
                intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("url", Api.BASEURL+Api.SLIDE_ALL_ORDER);
                getActivity().startActivity(intent);
                break;

            case R.id.slide_wait_pay:
                intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("url", Api.BASEURL+Api.SLIDE_WAIT_PAY);
                getActivity().startActivity(intent);
                break;
            case R.id.slide_wait_sure:
                intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("url", Api.BASEURL+Api.SLIDE_WAIT_SURE);
                getActivity().startActivity(intent);
                break;
            case R.id.slide_doing_order:
                intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("url", Api.BASEURL+Api.SLIDE_DOING_ORDER);
                getActivity().startActivity(intent);
                break;
        }
    }

}
