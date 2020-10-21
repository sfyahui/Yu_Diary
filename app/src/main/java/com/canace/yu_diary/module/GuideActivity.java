package com.canace.yu_diary.module;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.canace.yu_diary.R;
import com.canace.yu_diary.utils.CacheUtils;
import com.canace.yu_diary.utils.DensityUtil;

import java.util.ArrayList;

public class GuideActivity extends Activity {

    private ViewPager viewPager;
    private Button btn_start_main;
    private LinearLayout ll_point_group;
    private ImageView iv_red_point;
    private static final String TAG = GuideActivity.class.getSimpleName();

    private ArrayList<ImageView> imageViews;
    /**
     * 两点的间距
     */
    private int leftmax;

    private int widthdpi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        viewPager = findViewById(R.id.viewPager);
        btn_start_main = findViewById(R.id.btn_start_main);
        ll_point_group = findViewById(R.id.ll_point_group);
        iv_red_point = findViewById(R.id.iv_red_point);

        // 准备数据
        int[] ids = new int[]{
                R.drawable.guide_1,
                R.drawable.guide_2,
                R.drawable.guide_3
        };

        widthdpi = DensityUtil.dip2px(this,10);
        Log.e(TAG,widthdpi+"--------------");

        imageViews = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            ImageView imageView = new ImageView(this);
            // 设置背景
            imageView.setBackgroundResource(ids[i]);

            // 添加到集合中
            imageViews.add(imageView);

            // 创建点
            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.point_normal);
            /**
             * 单位是像素
             * 把单位当成dp转成对应的像素
             */
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(widthdpi, widthdpi);
            if (i != 0) {
                // 不包括第0个。所有的点距离左边有10个像素
                params.leftMargin = widthdpi;
            }
            point.setLayoutParams(params);
            // 添加到线性布局里面
            ll_point_group.addView(point);
        }
        // 设置ViewPager的适配器、
        viewPager.setAdapter(new MyPagerAdapter());

        // 根据View的生命周期，当视图执行到onLayout或者onDraw的时候，视图的高和宽，边距都有了
        iv_red_point.getViewTreeObserver().addOnGlobalLayoutListener(new MyOnGlobalLayoutListener());

        // 得到屏幕滑动的百分比
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());

        // 设置按钮的点击事件
        btn_start_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.保存曾经进入过主页面
                CacheUtils.putBoolean(GuideActivity.this, SplahsActivity.START_MAIN,true);

                //2.跳转到主页面
                Intent intent = new Intent(GuideActivity.this,MainActivity.class);
                startActivity(intent);

                //3.关闭引导页面
                finish();
            }
        });
    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener{

        /**
         * 当页面滚动了会回调这个方法
         * @param position 当前华东页面的位置
         * @param positionOffset 页面滑动的百分比
         * @param positionOffsetPixels 滑动的像素
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            // 两点间移动的距离 = 屏幕滑动百分比 * 间距
            //int leftmargin = (int) (positionOffset * leftmax);
            Log.e(TAG,"position==" + position + ",positionOffset==" + positionOffset + ",positionOffsetPixels==" + positionOffsetPixels);

            // 两点间滑动距离对应的坐标 = 原来的起始位置 + 两点间移动的距离
            int leftmargin = (int) (position * leftmax  + (positionOffset * leftmax));
            // params.leftMargin = 两点间滑动距离对应的坐标

            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iv_red_point.getLayoutParams();
            params.leftMargin = leftmargin;
            iv_red_point.setLayoutParams(params);
        }

        /**
         * 当页面被选中的时候，回调这个方法
         * @param position 被选中页面的对应位置
         */
        @Override
        public void onPageSelected(int position) {
            if (position == imageViews.size() - 1){
                // 最后一个页面
                btn_start_main.setVisibility(View.VISIBLE);
            }else{
                btn_start_main.setVisibility(View.GONE);
            }
        }

        /**
         *
         * 当ViewPager页面华东状态发生变化的时候
         * @param state
         */
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    class MyOnGlobalLayoutListener implements  ViewTreeObserver.OnGlobalLayoutListener {



        @Override
        public void onGlobalLayout() {
            // 执行不止一次’
            iv_red_point.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            // 间距 = 第1个点做点的距离 - 第0个点左边的距离
            leftmax = ll_point_group.getChildAt(1).getLeft() - ll_point_group.getChildAt(0).getLeft();
            Log.e(TAG,"leftmax==" + leftmax );
        }
    }

    class MyPagerAdapter extends PagerAdapter {
        /**
         * 销魂页面
         *
         * @param container ViewPager
         * @param position  要销毁页面的位置
         * @param object    要销魂的页面
         */
        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View) object);
        }

        /**
         * 作用，getView
         *
         * @param container ViewPager
         * @param position  要创建页面的位置
         * @return 返回和创建当前页面的关系值
         */
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView = imageViews.get(position);
            //添加到容器中
            container.addView(imageView);
            //return position;
            return imageView;
            //return super.instantiateItem(container, position);
        }

        /**
         * 返回数据的总个数
         *
         * @return
         */
        @Override
        public int getCount() {
            return imageViews.size();
        }

        /**
         * 判断
         *
         * @param view   当前创建的视图
         * @param object instantiateItem返回的结果值
         * @return
         */
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            // return view == imageViews.get(Integer.parseInt((String) object));
            return view == object;
        }
    }
}
