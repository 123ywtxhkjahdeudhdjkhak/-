package com.frb.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.frb.myapplication.R;
import com.frb.myapplication.databinding.FragmentBoxBeatBinding;
import com.frb.myapplication.databinding.ListItemSoundBinding;

public class BeatBoxAdapter extends RecyclerView.Adapter{

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemSoundBinding listItemSoundBinding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item_sound,parent,false);
        return new SoundHolder(listItemSoundBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    private class SoundHolder extends RecyclerView.ViewHolder{
        private ListItemSoundBinding listItemSoundBinding;

        public SoundHolder(ListItemSoundBinding listItemSoundBinding) {
            super(listItemSoundBinding.getRoot());
            this.listItemSoundBinding=listItemSoundBinding;
        }
    }
}
