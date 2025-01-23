package com.furb.developfoundation.base.android.activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 所有activity的基类
 * 作用：统一管理activity,统一初始状态的结构,便于理解和维护,后续有对该初始化activity的方法再进行维护
 * @作者 furb
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver();
    }

    //初始化操作
    public void init(){
        initView();
        initViewMonitor();
        registerReceiver();
    }
    //初始化视图
    public abstract void initView();
    //初始化视图监听器
    public abstract void initViewMonitor();
    //初始化广播接收器
    public abstract void registerReceiver();
    //移除广播接收器
    public abstract void unregisterReceiver();
    public abstract Intent newIntent(Context context);
}
