package com.qstm.industry.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * 作者：XiaoZhu
 * 时间：2017/11/18 0018 14:43
 * 注释：把tablayout和其下的内容整合起来，Tablayout里viewpager的适配器
 */
public class ExploreTabFragmentAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList;
    private final List<String> mTabTitle;

    public ExploreTabFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> tabTitle) {
        super(fm);
        this.mFragmentList=fragmentList;
        this.mTabTitle=tabTitle;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitle.get(position % mTabTitle.size());
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}