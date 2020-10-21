package com.canace.yu_diary.module.calendar;

import android.view.View;

import com.canace.yu_diary.R;
import com.canace.yu_diary.app.BaseFragment;

public class CalendarFragment extends BaseFragment {

    @Override
    public View initView() {
        View view = View.inflate(context,R.layout.fragment_calendar,null);
        return view;
    }
}
