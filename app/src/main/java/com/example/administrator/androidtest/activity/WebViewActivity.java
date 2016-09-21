package com.example.administrator.androidtest.activity;

import android.graphics.Bitmap;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.administrator.androidtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends AppCompatActivity {

    public static final String TAG_URL = "url";

    @BindView(R.id.wv)
    WebView webView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    WebViewClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        client = new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                toolbar.setTitle(view.getTitle());
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                toolbar.setTitle(view.getTitle());
            }
        };

        webView.setWebViewClient(client);
        //webView.loadUrl("file:///android_asset/html_pages/sj-index.html");
        webView.loadUrl(getIntent().getStringExtra(TAG_URL));
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack())
            webView.goBack();
        else
            super.onBackPressed();
    }
}
