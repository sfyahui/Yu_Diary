package com.canace.yu_diary.module.me;

import android.view.View;

import com.canace.yu_diary.R;
import com.canace.yu_diary.app.BaseFragment;

public class MeFragment extends BaseFragment{
    @Override
    public View initView() {
        View view = View.inflate(context,R.layout.fragment_me,null);
        return view;
    }
}
