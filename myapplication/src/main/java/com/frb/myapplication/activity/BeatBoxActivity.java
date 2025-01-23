package com.frb.myapplication.activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.frb.myapplication.R;
import com.frb.myapplication.fragment.BeatBoxFragment;
import com.furb.developfoundation.base.android.activity.BaseActivity;

public class BeatBoxActivity extends BaseActivity {
    @Override
    public void initView() {
        setContentView(R.layout.activity_box_beat);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.content);
        fragmentManager.beginTransaction().replace(R.id.content,fragment!=null?fragment:new BeatBoxFragment()).addToBackStack(null).commit();
    }

    @Override
    public void initViewMonitor() {

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
}
