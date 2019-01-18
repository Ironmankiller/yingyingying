package com.qstm.industry.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.qstm.industry.R;
import com.qstm.industry.adapter.ViewPagerAdapter;
import com.qstm.industry.fragment.ExploreFragment;
import com.qstm.industry.fragment.HomeFragment;
import com.qstm.industry.fragment.MessageFragment;
import com.qstm.industry.fragment.TabFragment;
import com.qstm.industry.fragment.UserFragment;
import com.qstm.industry.utils.ActivityUtils;
import com.qstm.industry.utils.BottomNavigationViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    //底部导航栏及其上内容
    @BindView(R.id.bottom_navigation)
    BottomNavigationView navigation;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ActivityUtils.StatusBarLightMode(this);
        ActivityUtils.setStatusBarColor(this, R.color.blue);//设置状态栏颜色
        initView();

    }

    private void initView() {

        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
        BottomNavigationViewHelper.disableShiftMode(navigation);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new TabFragment());
        adapter.addFragment(new ExploreFragment());
        adapter.addFragment(new MessageFragment());
        adapter.addFragment(new UserFragment());
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(0);

        //缓存3个页面，来解决点击“我的”回来，首页空白的问题，
        //存在的问题，如果有的页面不需要缓存该如何自动刷新，可以利用eventbus传参来进行该页面的操作
        //viewpager.setOffscreenPageLimit(3);   //方法一

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        viewpager.setCurrentItem(0);//首页
                        return true;
                    case R.id.navigation_community:
                        viewpager.setCurrentItem(1);//社区
                        return true;
                    case R.id.navigation_shopCart:
                        viewpager.setCurrentItem(2);//购物车
                        return true;
                    case R.id.navigation_user:
                        viewpager.setCurrentItem(3);//我的
                        return true;
                }
                return false;
            }
        });

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    navigation.getMenu().getItem(0).setChecked(false);
                }
                menuItem = navigation.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //禁止ViewPager滑动
        viewpager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

    }

    @Override
    public void onClick(View view) {
    }

//    /**
//     * 双击退出应用
//     * @param keyCode
//     * @param event
//     * @return
//     */
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if (mIsExit) {
//                this.finish();
//            } else {
//                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
//                mIsExit = true;
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mIsExit = false;
//                    }
//                }, 1500);
//            }
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }


}
