package com.tonystark10006.ssstore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public  class AboutActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        /*WebView webView = new WebView(this);
        webView.setWebViewClient(new WebViewClient() {
            //设置在webView点击打开的新网页在当前界面显示,而不跳转到新的浏览器中
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);  //设置WebView属性,运行执行js脚本
        webView.loadUrl("http://www.baidu.com");          //调用loadUrl方法为WebView加入链接
        setContentView(webView);                           //调用Activity提供的setContentView将webView显示出来*/
    }
}