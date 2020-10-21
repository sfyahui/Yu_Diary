package com.canace.yu_diary.module.backlog;

import android.view.View;

import com.canace.yu_diary.R;
import com.canace.yu_diary.app.BaseFragment;

public class BacklogFragment extends BaseFragment {

    @Override
    public View initView() {
        View view = View.inflate(context,R.layout.fragment_backlog,null);
        return view;
    }
}
