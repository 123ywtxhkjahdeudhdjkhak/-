package com.frb.yyong;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MainReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")){
            Toast.makeText(context, "网络发生变化", Toast.LENGTH_SHORT).show();
        } else if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            Toast.makeText(context,"已开机",Toast.LENGTH_SHORT).show();
        }
        abortBroadcast();
    }
}
