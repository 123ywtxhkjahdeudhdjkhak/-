package com.frb.myapplication.util;

import android.content.res.AssetManager;

import com.frb.myapplication.MyApplication;
import com.furb.developfoundation.base.util.LogUtil;

import java.io.IOException;

/**
 * Assets资源工具类
 * @作者 furb
 */
public class AssetsManager {
    private static final String TAG = "android.content.res.AssetManager";
    private static AssetManager mAssetManager= MyApplication.getContext().getAssets();
    public static String[] getFile(String path){
        try {
            String[] fileList = mAssetManager.list(path);
            for (String name : mAssetManager.list(path)){
            LogUtil.log(TAG,name,"d");
            }
            return fileList;
        } catch (IOException e) {
            LogUtil.log(TAG,"获取文件失败","e");
            return null;
        }
    }
}
