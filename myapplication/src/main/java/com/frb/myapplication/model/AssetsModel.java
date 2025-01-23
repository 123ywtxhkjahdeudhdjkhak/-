package com.frb.myapplication.model;

import com.frb.myapplication.util.AssetsManager;

/**
 * assets资源管理类
 * @作者furb
 */
public class AssetsModel {
    public enum AssetsType{
        SAMPLE_SOUNDS("sample_sounds");
        private final String mPath;
        AssetsType(String path){
           this.mPath = path;
        }

        public String getPath() {
            return mPath;
        }
    }
    public static String[] getPath(AssetsType assetsType){
        return AssetsManager.getFile(assetsType.getPath());
    }
}
