package com.frb.myapplication.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.frb.myapplication.vo.Sound;

public class SoundViewModel extends BaseObservable {
    private Sound mSound;
    @Bindable
    public String getSoundName(){
        return mSound.getSoundName();
    }
    public void setSound(Sound sound){
        mSound=sound;
        notifyChange();
    }
}
