package com.frb.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.frb.myapplication.R;
import com.frb.myapplication.databinding.ListItemSoundBinding;
import com.frb.myapplication.model.AssetsModel;
import com.frb.myapplication.model.SoundsModel;
import com.frb.myapplication.viewmodel.SoundViewModel;
import com.frb.myapplication.vo.Sound;

import java.util.List;

public class BeatBoxAdapter extends RecyclerView.Adapter{
    private List<Sound> dataList = SoundsModel.getmSoundList();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemSoundBinding listItemSoundBinding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item_sound,parent,false);
        return new SoundHolder(listItemSoundBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((SoundHolder)holder).bing(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    private class SoundHolder extends RecyclerView.ViewHolder{
        private ListItemSoundBinding listItemSoundBinding;

        public SoundHolder(ListItemSoundBinding listItemSoundBinding) {
            super(listItemSoundBinding.getRoot());
            this.listItemSoundBinding=listItemSoundBinding;
            listItemSoundBinding.setSoundViewModel(new SoundViewModel());
        }
        public void bing(Sound sound){
             listItemSoundBinding.getSoundViewModel().setSound(sound);
        }
    }
}
