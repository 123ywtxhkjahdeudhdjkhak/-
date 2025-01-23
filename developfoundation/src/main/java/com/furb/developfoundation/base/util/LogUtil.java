package com.furb.developfoundation.base.util;

/**
 * 作用：统一的日志管理类,统一管理当前所在日志包的日志类,并提供对外接口
 * @作者：furb
 */
public final class LogUtil {
    private static boolean sw = true;

    public static void setSW(boolean sw){
        LogUtil.sw = sw;
    }
    public static boolean getSW(){
        return sw;
    }

    /**
     * android_api 日志打印
     */
    public static void log(String tag,String msg,String logLevel){
        androidLogUtil.Log_1(tag,msg,logLevel);
    }
}
