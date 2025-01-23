package com.furb.developfoundation.base.util;

import android.util.Log;

/**
 * 作用：android实现的日志管理类
 * @作者：furb
 */

public class androidLogUtil {

    /**
     * android Log api
     */
    static void Log_1(String tag,String msg,String logLevel){
        switch (logLevel){
            case "v":
                Log.v(tag,msg);
                break;
            case "d":
                Log.d(tag,msg);
                break;
            case "i":
                Log.i(tag,msg);
                break;
            case "w":
                Log.w(tag,msg);
                break;
            case "e":
                Log.e(tag,msg);
                break;
            default :
                Log.d(tag,msg);
        }
    }
}
