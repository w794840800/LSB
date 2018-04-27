package com.example.niu.mlpt.ui;

import android.Manifest;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.niu.mlpt.R;
import com.example.niu.mlpt.Utils.ToastUtils;
import com.example.niu.mlpt.view.ClearEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HelpSendActivity extends AppCompatActivity {

    @BindView(R.id.iv_back)
    ImageView mImageViewBack;
    @BindView(R.id.tv_use_notice)
    TextView tvUseNotice;
    @BindView(R.id.send_toolbar)
    RelativeLayout sendToolbar;
    @BindView(R.id.send_items)
    LinearLayout sendItems;
    @BindView(R.id.tv_send)
    TextView tvSend;
    @BindView(R.id.iv_send_contacts)
    ImageView ivSendContacts;
    @BindView(R.id.et_send_number)
    ClearEditText etSendNumber;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_common_send)
    TextView tvCommonSend;
    @BindView(R.id.tv_send_address)
    ClearEditText tvSendAddress;
    @BindView(R.id.ll_send_info)
    LinearLayout llSendInfo;
    @BindView(R.id.tv_bring_number)
    TextView tvBringNumber;
    @BindView(R.id.iv_bring_contacts)
    ImageView ivBringContacts;
    @BindView(R.id.et_bring_number)
    ClearEditText etBringNumber;
    @BindView(R.id.tv_bring)
    TextView tvBring;
    @BindView(R.id.tv_common_bring_address)
    TextView tvCommonBringAddress;
    @BindView(R.id.ll_bring_info)
    LinearLayout llBringInfo;
    @BindView(R.id.tv_beizhu_title)
    TextView tvBeizhuTitle;
    @BindView(R.id.tv_bring_address)
    ClearEditText tvBringAddress;
    @BindView(R.id.tv_send_time_title)
    TextView tvSendTimeTitle;
    @BindView(R.id.tv_time_detail)
    TextView tvTimeDetail;
    @BindView(R.id.iv_select_time)
    ImageView ivSelectTime;
    @BindView(R.id.tv_total_money)
    TextView tvTotalMoney;
    @BindView(R.id.tv_pay)
    TextView tvPay;
    private String userName;
    private String userNumber;
   public static final int SEND = 100;
    public static final int RECEIPT = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_send);

        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back,R.id.iv_send_contacts,R.id.iv_bring_contacts})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_send_contacts:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){

                    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},3);

                }
                Intent intent = new Intent
                        (Intent.ACTION_PICK,ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(intent,SEND);

                break;



        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("wanglei", "onActivityResult: ");
           switch (requestCode){

               case SEND:

                   Uri uri = data.getData();
                   Log.d("wanglei ", "onActivityResult: uri= "+uri);
                   if (uri!=null){

                       //String[]contact = getPho

                       final Cursor cursor = managedQuery(uri,null,null,null,null);

                       cursor.moveToFirst();
                       userName= cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                       String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                       Log.d("wanglei", "onActivityResult: userName= "+userName+" uri= "+uri+" id= "+contactId);

                       CursorLoader cursorLoader = new CursorLoader(this,uri,new String[]{"data1"},null,null,null);
                            cursorLoader.registerListener(1, new Loader.OnLoadCompleteListener<Cursor>() {
                                @Override
                                public void onLoadComplete(Loader<Cursor> loader, Cursor data) {
                                    if (data==null){
                                        return;

                                    }

                                    if (data.moveToFirst()){

                                        int index = data.getColumnIndex("data1");
                                        String number = data.getString(index);
                                        Log.d("wanglei ", "onLoadComplete: number= "+number);


                                    }



                                }
                            });
                            cursorLoader.startLoading();
                      // ToastUtils.showToast( "wanglei"+"onActivityResult: userName= "+userName+" uri= "+uri+" id= "+contactId,Toast.LENGTH_LONG);
                       Cursor phone = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                               null, null, null);

                       Log.d("wanglei", "onActivityResult: curcor phone= "+phone.getCount());
                       phone.moveToFirst();
                       //while(phone.moveToFirst()){

                           userNumber = phone.getString
                                   (phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                           String userId = phone.getString
                                   (phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));

                           String id = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
//+86 132 8566 7989  张晓
                           Log.d("wanglei ", "onActivityResult: userNumber= "+userNumber+" userId= "+userId);


                       // }

                        phone.close();



                   }
                   ToastUtils.showToast("userName= "+userName+" userPassword= "+userNumber
                           ,Toast.LENGTH_LONG
                   );
                   break;



           }

    }
}
