package com.canace.yu_diary.module.data;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.canace.yu_diary.R;
import com.canace.yu_diary.app.BaseFragment;

public class DataFragment extends BaseFragment {
    @Override
    public View initView() {
        View view = View.inflate(context,R.layout.fragment_data,null);
        return view;
    }
}
