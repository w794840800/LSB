package com.example.niu.lsb.ui;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.niu.lsb.R;
import com.example.niu.lsb.view.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends AppCompatActivity {
    WebView mWebView;
    String url;
    @BindView(R.id.web_title)
    TextView mWebTitle;
    @BindView(R.id.webview)
    FrameLayout mWebViewContainer;
    @BindView(R.id.web_progress)
    ProgressBar mWebProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        mWebView = new WebView(this);
       // View view = findViewById(R.id.cl_webview);

        //mWebView.addView(view);
         mWebViewContainer.addView(mWebView);



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

                 mWebProgress.setProgress(newProgress);

             }
         });

        url = getIntent().getStringExtra("url");

        mWebView.loadUrl(url);

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
        public void onPageStarted(android.webkit.WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
           mWebProgress.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(android.webkit.WebView view, String url) {
            super.onPageFinished(view, url);
            mWebProgress.setVisibility(View.GONE);
        }
    }

}
