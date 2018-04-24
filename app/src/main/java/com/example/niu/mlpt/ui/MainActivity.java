package com.example.niu.mlpt.ui;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.location.Location;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.example.niu.mlpt.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    MapView mapView;
    @BindView(R.id.slipeOnmoreview)
    View slipeOnmoreview;
    @BindView(R.id.other_menu)
    LinearLayout other_menu;
    @BindView(R.id.logo_city)
    LinearLayout logo_city;
    @BindView(R.id.three_menu)
    LinearLayout threeMenu;
    @BindView(R.id.center_cursor)
    ImageView mCenterCursor;
    @BindView(R.id.main_drawer)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.main_account)
    ImageView main_account;
    @BindView(R.id.main_location)
    ImageView mLocation;
    @BindView(R.id.main_center_info)
    RelativeLayout mCenterInfo;
    @BindView(R.id.main_help_buy)
    LinearLayout mHelpBuy;
    @BindView(R.id.main_help_take)
    LinearLayout mHelpTake;
    @BindView(R.id.main_help_send)
    LinearLayout mhelpSend;

    ArrayList<String>images = new ArrayList<>();
    ArrayList<Bitmap> bitmapArrayList = new ArrayList<>();

    LatLng nowLatLng;
    ArrayList<MarkerOptions>saveMarker;
    int index = 0;
    Marker centerMarker;
    CameraUpdate mCameraUpdate;
    UiSettings uiSetting;
    Map<String,LatLng>addressLatng;
    private AMapLocationClient locationClient = null;
    int otherMenuHeight;
    AMap aMap;

    boolean mBottomMenuExpand;

    ObjectAnimator expandAnimatorOne,expandAnimatorTwo ;
    ObjectAnimator collaspeAnimatorOne,collaspeAnimatorTwo,collaspeAnimatorThree;
    AnimatorSet animatorSetOne,animatorSetTwo;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            Log.d("TAG", "onLocationChanged: ");
            if (aMapLocation.getErrorCode()==0){
                Toast.makeText(MainActivity.this," "+aMapLocation.getAddress(),Toast.LENGTH_LONG).show();

            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCameraUpdate = CameraUpdateFactory.zoomTo(17);
        ButterKnife.bind(this);
        other_menu.post(new Runnable() {
            @Override
            public void run() {
                otherMenuHeight = other_menu.getLayoutParams().height;
              //  Toast.makeText(getApplicationContext(),"test height= "+otherMenuHeight,Toast.LENGTH_SHORT).show();

            }
        });
        slipeOnmoreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                     //展开动画

                /*expand1.setDuration(100);
                expand.setDuration(100);
                //.setRepeatCount(ValueAnimator.INFINITE);

                //expand .start();
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(expand).with(expand1);
                animatorSet.start();*/
            /*    expand.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {

                        other_menu.setVisibility(View.VISIBLE);
                        mBottomMenuExpand = true;

                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });*/
                expandAnimatorOne =  ObjectAnimator.ofFloat(threeMenu,"translationY",0,-otherMenuHeight);
                expandAnimatorTwo =  ObjectAnimator.ofFloat(slipeOnmoreview,"translationY",0,-otherMenuHeight);
                animatorSetOne = new AnimatorSet();
                animatorSetOne.play(expandAnimatorOne).with(expandAnimatorTwo);
                animatorSetOne.setDuration(100);
                //animatorSet.start();


                collaspeAnimatorOne =  ObjectAnimator.ofFloat(threeMenu,"translationY",threeMenu.getTranslationY(),0);
                collaspeAnimatorTwo =  ObjectAnimator.ofFloat(slipeOnmoreview,"translationY",slipeOnmoreview.getTranslationY(),
                        0);
                animatorSetTwo = new AnimatorSet();
                animatorSetTwo.play(collaspeAnimatorOne).with(collaspeAnimatorTwo);
                animatorSetTwo.setDuration(100);

                if (mBottomMenuExpand){
                    animatorSetTwo.start();
                    //.setRepeatCount(ValueAnimator.INFINITE);
                    //expand .start();

                }else{
                    animatorSetOne.start();

               /*     expand =  ObjectAnimator.ofFloat(threeMenu,"translationY",0,-otherMenuHeight);
                    expand1 =  ObjectAnimator.ofFloat(slipeOnmoreview,"translationY",0,-otherMenuHeight);
                    expand1.setDuration(100);
                    expand.setDuration(100);
                    //.setRepeatCount(ValueAnimator.INFINITE);

                    //expand .start();
                    animatorSet = new AnimatorSet();
                    animatorSet.play(expand).with(expand1);
                    animatorSet.start();*/

                }
                expandAnimatorOne.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        other_menu.setVisibility(View.VISIBLE);
                        mBottomMenuExpand =true;
                    }
                });
                collaspeAnimatorOne.addListener(new AnimatorListenerAdapter() {


                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        other_menu.setVisibility(View.GONE);

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mBottomMenuExpand =false;
                        other_menu.setVisibility(View.GONE);
                    }
                });


            }
        });


        logo_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"test",Toast.LENGTH_SHORT).show();
                /*ObjectAnimator expand =  ObjectAnimator.ofFloat(threeMenu,"translationY",0,-otherMenuHeight);
                ObjectAnimator expand1 =  ObjectAnimator.ofFloat(slipeOnmoreview,"translationY",0,-otherMenuHeight);
                expand1.setDuration(100);
                expand.setDuration(100);
                        //.setRepeatCount(ValueAnimator.INFINITE);

                //expand .start();
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(expand).with(expand1);
                animatorSet.start();

                //expand.setDuration(400);
                //.setRepeatCount(ValueAnimator.INFINITE);
                expand.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {

                        other_menu.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
*/

            }
        });
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)!=PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_PHONE_STATE,Manifest.permission.ACCESS_FINE_LOCATION},1);

        }  if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION},1);

        }
        /*main_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getApplicationContext(),"test",Toast.LENGTH_LONG).show();

            }
        });*/
        //setSlideMenuFullScreen();

        //findViewById(R.id.location)
        /*if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},2);

        }*/

        //lng: (31.862125106272405,117.20602020621301),zoom=15.0,tilt=0.0,bearing=0.0 万博科技职业学院
        //lng: (31.85864531011247,117.20196738839151) 合肥通用技术
        //lng: (31.853242538185956,117.20574930310248)  天波路
        //(31.8558169534978,117.20880568027496) 科学大道地铁站
        //31.856102868639976,117.21575796604158)西二环
        //31.84783603209266,117.20399782061577 海关路
        addressLatng = new HashMap<>();
        addressLatng.put("万博科技职业学院",new LatLng(31.862125106272405,117.20602020621301));
        addressLatng.put("合肥通用技术",new LatLng(31.85864531011247,117.20196738839151));
        addressLatng.put("天波路",new LatLng(31.853242538185956,117.20574930310248));
        addressLatng.put("科学大道地铁站",new LatLng(31.8558169534978,117.20880568027496));
        addressLatng.put("西二环",new LatLng(31.856102868639976,117.21575796604158));
        addressLatng.put("海关路",new LatLng(31.84783603209266,117.20399782061577));
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
        saveMarker = new ArrayList<>();
        aMap = mapView.getMap();
        //uiSetting =aMap.getUiSettings();
        //uiSetting.setZoomGesturesEnabled(false);
        //uiSetting.setCompassEnabled(true);
        ArrayList<MarkerOptions>list = new ArrayList<>();
        //list.add(new MarkerOptions().position(new LatLng(39.906901,116.397972)).title("beijing"));
        //list.add(new MarkerOptions().position(new LatLng(42.906901,116.397972)).title("beijing2"));
        //aMap.addMarkers(list,true);
         //aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
        //aMap.addMarker();
        //aMap.addMarker();
        locationClient = new AMapLocationClient(getApplicationContext());
        mLocationOption = new AMapLocationClientOption();
        locationClient.startLocation();
       // MyLocationStyle myLocationStyle;
        //myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        MyLocationStyle locationStyle = new MyLocationStyle();
        locationStyle.interval(2000);
        //aMap.setMyLocationStyle(locationStyle);
        // aMap.setMyLocationEnabled(true);
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        aMap.getUiSettings().setCompassEnabled(true);
        aMap.getUiSettings().setZoomControlsEnabled(false);


        mLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationClient.startLocation();
            }
        });

        aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                LatLng latLng = new LatLng(location.getLongitude(),location.getAccuracy());
                Log.d("wanglei", "onMyLocationChange: latlng= "+latLng.toString());

               /* if (centerMarker==null) {
                   //centerMarker = aMap.addMarker(new MarkerOptions().position(latLng));
                 }else{
                  //   centerMarker.setPosition(latLng);

                 }*/
            }
        });
        //更新 当前位置图标一直居中
        aMap.setOnCameraChangeListener(new AMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {

              Log.d("wanglei", "onCameraChange: cameraPosition= "+cameraPosition.toString());
                if (mCenterCursor!=null) {
                    mCenterCursor.setImageResource(R.drawable.icon_home_location_drag);
                }
                if (mCenterInfo!=null){
                    mCenterInfo.setVisibility(View.GONE);

                }
                /*   centerMarker.setTitle(".........");
                if (centerMarker!=null){

                    LatLng latlang = cameraPosition.target;
                    centerMarker.setPosition(latlang);
                }
*/
            }

            @Override
            public void onCameraChangeFinish(CameraPosition cameraPosition) {

                Log.d("TAG", "onCameraChangeFinish: cameraPosition= "+cameraPosition.target.toString());
                if (mCenterCursor!=null) {
                    mCenterCursor.setImageResource(R.drawable.icon_home_location_normal);
                }
               /* centerMarker.setTitle("附近有6位跑男");

                Log.d("TAG", "onCameraChangeFinish: cameraPosition= "+cameraPosition.target.toString()+
                " snippet= "+centerMarker.getTitle());*/
                if (mCenterInfo!=null){
                    mCenterInfo.setVisibility(View.VISIBLE);

                }
                removeAllSaveMask();
                getAddressLatngFromNetWork();

            }
        });
        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                return true;
            }
        });
        mLocationOption.setOnceLocation(true);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        locationClient.setLocationOption(mLocationOption);
        locationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                Log.d("wanglei", "onLocationChanged: error= "+aMapLocation.getAccuracy()+aMapLocation.getAddress());

                Log.d("wanglei", "onLocationChanged: error= "+aMapLocation.toString());
                LatLng latLng = new LatLng(aMapLocation.getLatitude(),aMapLocation.getLongitude());
                /*if (centerMarker==null) {
                    centerMarker = aMap.addMarker(new MarkerOptions().position(latLng).title("附近有6位跑男")
                    .draggable(true).setFlat(true));
                    centerMarker.setObject("main");

                    centerMarker.showInfoWindow();
                }else{
                    centerMarker.setPosition(latLng);

                }*/
                aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));

                /*ArrayList<MarkerOptions>list = new ArrayList<>();
                list.add(new MarkerOptions().position(new LatLng(39.906901,116.397972)).title("beijing"));
                list.add(new MarkerOptions().position(new LatLng(42.906901,116.397972)).title("beijing2"));*/
                //aMap.addMarker(new MarkerOptions().position(new LatLng(aMapLocation.getLongitude(),aMapLocation.getAccuracy())).title("beijing"));

              // Toast.makeText(MainActivity.this," "+aMapLocation.getAddress()+aMapLocation.getErrorCode(),Toast.LENGTH_LONG).show();
            }
        });



    }

    private void setSlideMenuFullScreen() {
        View slide_menu = findViewById(R.id.slide_menu);
        ViewGroup.LayoutParams layoutParams = slide_menu.getLayoutParams();
        DisplayMetrics display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        layoutParams.width = display.widthPixels;
        layoutParams.height = display.heightPixels;
        slide_menu.setLayoutParams(layoutParams);
    }
    @OnClick(R.id.main_account)
    public void onViewClicked(View v){
         switch (v.getId()){
             case R.id.main_account:
                 mDrawerLayout.openDrawer(Gravity.LEFT);
                 break;

         }

    }

    private void getThumbnail() {
        Cursor mCursor = getContentResolver().query(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                null, null, null,
                MediaStore.MediaColumns.DATE_ADDED + " DESC");
        if (mCursor.moveToFirst()) {
            int _date = mCursor.getColumnIndex(MediaStore.Video.Media.DATA);
            int columnIndex = mCursor.getColumnIndex(MediaStore.Video.Media._ID);

            do {
                //data 是数据的意思，获取的就是文件数据，也就是文件路径。
                String path = mCursor.getString(_date);
                int anInt = mCursor.getInt(columnIndex);
                //DebugUtil.error("path:"+path);
                images.add(path);
                Bitmap bitmap= MediaStore.Video.Thumbnails.getThumbnail(getContentResolver(),anInt, MediaStore.Video.Thumbnails.MINI_KIND,null);
                bitmapArrayList.add(bitmap);
            } while (mCursor.moveToNext());
        }
        mCursor.close();
        Log.d("wanglei ", "getThumbnail: images size = "+images.size()+
        " bitmapArrayList size= "+bitmapArrayList.size());
     //   handler.sendEmptyMessage(0);
    }

    @OnClick({R.id.main_help_buy,R.id.main_help_send,R.id.main_help_take})
    public void onHelpClicked(View view){
        //bitmapArrayList.clear();
        //images.clear();

       /* Matisse.from(MainActivity.this)
                .choose(MimeType.allOf())
                .countable(true)
                .maxSelectable(9)
                //.addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .gridExpectedSize(120)
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine())
                .forResult(1);*/

     //getThumbnail();
       /* Intent intent = new Intent(this,HelpSendActivity.class);
        //startActivity(intent);
            Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
            //i.setAction(Intent.ACTION_GET_CONTENT);
        //i.setType("video*//*");
            startActivityForResult(i,1);*/


    }


    private AMapLocationClientOption getDefaultOption(){
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        //mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
        //mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        return mOption;
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Tag", "onResume: sha1= "+sHA1(getApplicationContext()));
        mapView.onResume();
    }
    public static String sHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
                hexString.append(":");
            }
            String result = hexString.toString();
            return result.substring(0, result.length()-1);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
     mapView.onDestroy();
    }
    //模拟从网络获取信息
    public void getAddressLatngFromNetWork(){
       // HashMap <String,LatLng>map = (HashMap) getAddressLatngFromNetWork();
        //Set<String>set = map.keySet();
        //Iterator<String> iterator = set.iterator();
        MarkerOptions markOption;
        LatLng lant;
        saveMarker.clear();
        for(String s: addressLatng.keySet()){

            lant = addressLatng.get(s);
            Log.d("TAG", "getAddressLatngFromNetWork: lant= "+lant.toString());

            View markerView = LayoutInflater.from(this).inflate(R.layout.marker_runningman,mapView,false);
            Log.d("TAG", "getAddressLatngFromNetWork: marker= "+markerView.getWidth()+" "+markerView.getHeight());
            markOption = new MarkerOptions().position(lant).
                    icon(
                           /* BitmapDescriptorFactory.
                            fromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.home_runmen_icon))*/

                            BitmapDescriptorFactory.fromView(markerView)

            ).infoWindowEnable(false);

            //markOption
            saveMarker.add(markOption);

        }
        aMap.addMarkers(saveMarker,false);

    }
    private void removeAllSaveMask() {
        ArrayList<Marker>markers = (ArrayList<Marker>) aMap.getMapScreenMarkers();

         if (markers.size()==0||markers==null){
             return;
         }
        for (int i = 0; i < markers.size(); i++) {
           Marker marker = markers.get(i);
            Log.d("TAG", "removeAllSaveMask: i= "+i+" lat= "+marker.getPosition().toString());
            Object key = marker.getObject();
                    //.toString();
            if (key==null||key.toString().equals("main")){

            }else{
                marker.remove();
            }


        }


    }


}
