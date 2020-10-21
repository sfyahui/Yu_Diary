package com.canace.yu_diary.module;

import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.canace.yu_diary.R;
import com.canace.yu_diary.app.BaseFragment;
import com.canace.yu_diary.module.backlog.BacklogFragment;
import com.canace.yu_diary.module.calendar.CalendarFragment;
import com.canace.yu_diary.module.data.DataFragment;
import com.canace.yu_diary.module.diary.DiaryFragment;
import com.canace.yu_diary.module.me.MeFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RadioGroup rgMain;
    //装多个Fragment实例
    private ArrayList<BaseFragment> fragments;

    private int position = 0;
    //缓存的Fragment或者上次显示的Fragment
    private Fragment tempFragemnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rgMain = findViewById(R.id.rg_main);

        //初始化Fragmnet
        initFragment();
        //设置RadioGroup的监听
        initListener();

    }

    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rb_calendar: //日历
                        position = 0;
                        break;
                    case R.id.rb_backlog:  //待办
                        position = 1;
                        break;
                    case R.id.rb_diary:  //日历
                        position = 2;
                        break;
                    case R.id.rb_data:  //数据
                        position = 3;
                        break;
                    case R.id.rb_me:  //我的
                        position = 4;
                        break;
                    default:
                        break;
                }

                //根据位置取不同的Fragment
                BaseFragment baseFragment = getFragment(position);
                /**
                 * 第一参数：上次显示的Fragmnet
                 * 第二参数：当前正要显示的Fragment
                 */
                switchFragment(tempFragemnt, baseFragment);

            }
        });

        rgMain.check(R.id.rb_diary);
    }

    /**
     * 添加的时候要按照顺序
     */
    public void initFragment(){
        fragments = new ArrayList<>();
        fragments.add(new CalendarFragment());
        fragments.add(new BacklogFragment());
        fragments.add(new DiaryFragment());
        fragments.add(new DataFragment());
        fragments.add(new MeFragment());
    }

    //根据位置得到对应的 Fragment
    private BaseFragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    /**
     * 切换fragment
     * @param fromFragment
     * @param nextFragment
     */
    private void switchFragment(Fragment fromFragment, BaseFragment
            nextFragment) {
        if (tempFragemnt != nextFragment) {
            tempFragemnt = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction =
                        getSupportFragmentManager().beginTransaction();
// 判断 nextFragment 是否添加
                if (!nextFragment.isAdded()) {
// 隐藏当前 Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.frameLayout, nextFragment).commit();
                } else {
// 隐藏当前 Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }
}