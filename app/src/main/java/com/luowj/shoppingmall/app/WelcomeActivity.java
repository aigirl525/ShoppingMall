package com.luowj.shoppingmall.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import app.properties.custom.imooc.com.shoppingmall.R;


public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //两秒延迟进入主页面
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //启动主页面
                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                //关闭当前页面
                finish();
            }
        },2000);
    }


}
