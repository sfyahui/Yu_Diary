package com.canace.yu_diary.module.calendar;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.canace.yu_diary.R;
import com.canace.yu_diary.app.BaseFragment;
import com.necer.calendar.EmuiCalendar;

public class CalendarFragment extends BaseFragment {

    private EmuiCalendar emuiCalendar;

    @Override
    public View initView() {
        View view = View.inflate(context,R.layout.fragment_calendar,null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
