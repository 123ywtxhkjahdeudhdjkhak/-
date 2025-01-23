package com.frb.myapplication.vo;

import android.util.Log;

public class Sound {
    private String mSoundName;
    private String mPath;
    public Sound(String path){
        mPath = path;
        mSoundName = path.split("\\.")[0];
    }
    public String getSoundName() {
        return mSoundName;
    }

    public void setSoundName(String mSoundName) {
        this.mSoundName = mSoundName;
    }

    public String getPath() {
        return mPath;
    }

    public void setPath(String mPath) {
        this.mPath = mPath;
    }
}
