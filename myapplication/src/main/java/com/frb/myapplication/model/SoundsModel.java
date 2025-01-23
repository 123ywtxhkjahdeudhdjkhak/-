package com.frb.myapplication.model;

import com.frb.myapplication.vo.Sound;

import java.util.ArrayList;
import java.util.List;

public class SoundsModel {
    private static List<Sound> mSoundList=new ArrayList<>();
    static {
        for(String path:AssetsModel.getPath(AssetsModel.AssetsType.SAMPLE_SOUNDS)){
            mSoundList.add(new Sound(path));
        }
    }

    public static List<Sound> getmSoundList() {
        return mSoundList;
    }
}
