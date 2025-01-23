package com.frb.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.frb.myapplication.activity.BeatBoxActivity;
import com.frb.myapplication.util.AssetsManager;
import com.furb.developfoundation.base.android.activity.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private Button mBeatBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        mBeatBox = findViewById(R.id.beatbox);
    }

    @Override
    public void initViewMonitor() {
        mBeatBox.setOnClickListener(this);
    }

    @Override
    public void registerReceiver() {

    }

    @Override
    public void unregisterReceiver() {

    }

    @Override
    public Intent newIntent(Context context) {
        return null;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.beatbox){
            startActivity(new Intent(this, BeatBoxActivity.class));
        }
    }
}