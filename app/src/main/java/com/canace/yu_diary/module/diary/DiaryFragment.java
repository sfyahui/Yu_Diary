package com.canace.yu_diary.module.diary;

import android.view.View;

import com.canace.yu_diary.R;
import com.canace.yu_diary.app.BaseFragment;

public class DiaryFragment extends BaseFragment {
    @Override
    public View initView() {
        View view = View.inflate(context,R.layout.fragment_diary,null);
        return view;
    }
}
