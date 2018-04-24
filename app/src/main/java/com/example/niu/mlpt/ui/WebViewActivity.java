package com.example.niu.mlpt.ui;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.niu.mlpt.R;
import com.example.niu.mlpt.view.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebViewActivity extends AppCompatActivity {
    WebView mWebView;
    String url;
    @BindView(R.id.web_title)
    TextView mWebTitle;
    @BindView(R.id.webview)
    FrameLayout mWebViewContainer;
    @BindView(R.id.web_progress)
    ProgressBar mWebProgress;
    @BindView(R.id.web_back)
    ImageView mWebBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        mWebView = new WebView(this);
       // View view = findViewById(R.id.cl_webview);

        //mWebView.addView(view);
         mWebViewContainer.addView(mWebView);

  mWebView.setWebViewClient(new MyWebViewClient());

         mWebView.setWebChromeClient(new WebChromeClient(){

             @Override
             public void onReceivedTitle(android.webkit.WebView view, String title) {
                 super.onReceivedTitle(view, title);
                 mWebTitle.setText(title);
                 Log.d("wanglei", "onReceivedTitle: title= "+title);
             }

             @Override
             public void onProgressChanged(android.webkit.WebView view, int newProgress) {
                 super.onProgressChanged(view, newProgress);
                 //mWebProgress.setVisibility(View.VISIBLE);
                 if (mWebProgress!=null&&newProgress!=100){

                     mWebProgress.setVisibility(View.VISIBLE);

                 }else if (mWebProgress!=null){
                     mWebProgress.setVisibility(View.GONE);

                 }
                 Log.d("test", "onProgressChanged: progress= "+newProgress);
                 mWebProgress.setProgress(newProgress);

             }
         });

        url = getIntent().getStringExtra("url");

        mWebView.loadUrl(url);

    }
    @OnClick(R.id.web_back)

    public void onViewClick(View v){

        if (mWebView.canGoBack()){
            mWebView.goBack();
        }else{

            finish();
        }


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
      if (mWebView!=null&&mWebView.canGoBack()){

          mWebView.goBack();
      }else{

          finish();

      }

    }

    class MyWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(android.webkit.WebView view, WebResourceRequest request) {
                      view.loadUrl(request.getUrl().getPath());
            return true;
            //return super.shouldOverrideUrlLoading(view, request);
        }
        @Override
        public void onPageStarted(android.webkit.WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

        }

        @Override
        public void onPageFinished(android.webkit.WebView view, String url) {
            super.onPageFinished(view, url);
            mWebProgress.setVisibility(View.GONE);
        }
    }

}
