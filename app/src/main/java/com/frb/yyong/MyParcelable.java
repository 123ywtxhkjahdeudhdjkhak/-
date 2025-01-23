package com.frb.yyong;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class MyParcelable implements Parcelable {
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {

    }
    public final static Parcelable.Creator<MyParcelable> CREATOR = new Creator() {
        @Override
        public MyParcelable createFromParcel(Parcel source) {
            return null;
        }

        @Override
        public MyParcelable[] newArray(int size) {
            return new MyParcelable[size];
        }
    };
}
