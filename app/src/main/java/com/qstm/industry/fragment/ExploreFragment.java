package com.qstm.industry.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qstm.industry.R;
import com.qstm.industry.adapter.ExploreTabFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 社区
 */
public class ExploreFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.tab_viewpager)
    ViewPager tabViewpager;
    Unbinder unbinder;


    private List<Fragment> mFragmentArrays = new ArrayList<>();
    private List<String> mTabs = new ArrayList<>();
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //解决点击“我的”回来首页空白的问题方法二，推荐的方法
        if (view != null) {
            unbinder = ButterKnife.bind(this, view);//必须加，不然报ButterKnife的异常
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            return view;
        }

        view = inflater.inflate(R.layout.fragment_explore, container, false);

        unbinder = ButterKnife.bind(this, view);//这里也得有，不然报ButterKnife的异常

        initView(view);
        return view;
    }

    private void initView(View view) {
        tablayout.removeAllTabs();
        tabViewpager.removeAllViews();
        if (mFragmentArrays != null) {
            mFragmentArrays.clear();
            mTabs.clear();
        }
        //替换成从服务器接口请求数据就成动态了
        mTabs.add("推荐");
        mTabs.add("关注");
        mTabs.add("分类");

        //动态添加Fragment
        for (int i = 0; i < mTabs.size(); i++) {

            Fragment fragment = new ExploreTabFragment();
            mFragmentArrays.add(fragment);
        }

        tabViewpager.setAdapter(new ExploreTabFragmentAdapter(getFragmentManager(), mFragmentArrays, mTabs));
        tablayout.setupWithViewPager(tabViewpager);

    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
