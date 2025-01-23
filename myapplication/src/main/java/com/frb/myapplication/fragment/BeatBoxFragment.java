package com.frb.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import com.frb.myapplication.Adapter.BeatBoxAdapter;
import com.frb.myapplication.R;
import com.frb.myapplication.databinding.FragmentBoxBeatBinding;
import com.furb.developfoundation.base.android.fragment.BaseFragment;

public class BeatBoxFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentBoxBeatBinding fragmentBoxBeatBinding =
                DataBindingUtil.inflate(inflater,R.layout.fragment_box_beat,container,false);
        fragmentBoxBeatBinding.beatbox.setLayoutManager(new GridLayoutManager(getActivity(),3));
        fragmentBoxBeatBinding.beatbox.setAdapter(new BeatBoxAdapter());
        return fragmentBoxBeatBinding.getRoot();
    }
}
