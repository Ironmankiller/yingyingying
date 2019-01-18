package com.qstm.industry.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.qstm.industry.R;
import com.qstm.industry.adapter.RecycleViewAdapter;
import com.qstm.industry.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by Administrator on 2017/12/19 0019.
 */

public class TabFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    @BindView(R.id.swp)
    SwipeRefreshLayout swp;
    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;

    ViewPager pager;
    TabLayout tab;


    private List<Fragment> mFragmentArrays = new ArrayList<>();
    private List<String> mTabs = new ArrayList<>();
    private Handler mHandler;
    Unbinder unbinder;
    private List<String> mdata = new ArrayList<>();
    private List<String> imageUrl = new ArrayList<>();
    int mPosition;
    private RecycleViewAdapter mAdapter;
    private Banner mBanner;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        inflater=(LayoutInflater)getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.fragment_tab_zpx, container, false);
        mHandler = new Handler();
        unbinder = ButterKnife.bind(this, rootView);
//        mPosition = getArguments().getInt("position");
//        pager=(ViewPager) rootView.findViewById(R.id.pager_main);
//        tab=(TabLayout) rootView.findViewById(R.id.tab_main);


        initView(rootView);
        initData();

        return rootView;
    }


    private void initView(View rootView) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecycleViewAdapter(R.layout.item_expert, mdata);



//        if (mFragmentArrays != null) {
//            mFragmentArrays.clear();
//            mTabs.clear();
//        }
//        mTabs.add("服务");
//        mTabs.add("需求");
//
//
//            mFragmentArrays.add(new serve_fragment());
//            mFragmentArrays.add(new need_fragment());
//        TabPagerAdapter courseadapter=new TabPagerAdapter(getChildFragmentManager(),mFragmentArrays);
//
//        pager.setAdapter(courseadapter);
//        tab.setupWithViewPager(pager);



        swp.setOnRefreshListener(this);

        View top = getLayoutInflater().inflate(R.layout.layout_banner, (ViewGroup) mRecyclerView.getParent(), false);
        View expert_guide=getLayoutInflater().inflate(R.layout.item_middle,(ViewGroup) mRecyclerView.getParent(),false);
        View see_more=getLayoutInflater().inflate(R.layout.item_seemore,(ViewGroup) mRecyclerView.getParent(),false);
//        View Tab=getLayoutInflater().inflate(R.layout.item_tab,(ViewGroup) mRecyclerView.getParent(),false);
        mBanner = top.findViewById(R.id.banner);
        mAdapter.addHeaderView(top);
        mAdapter.addHeaderView(expert_guide);
        mAdapter.addFooterView(see_more);
//        mAdapter.addFooterView(Tab);
        mRecyclerView.setAdapter(mAdapter);

    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            mdata.add("周大勇");
        }
        mAdapter.setNewData(mdata);//模拟网络请求成功后要调用这个方法刷新数据
//        if (mPosition == 0) {
            imageUrl.clear();
            imageUrl.add("http://mpic.tiankong.com/aa4/fd8/aa4fd84a633298f43fe4521ba9a2dcbc/640.jpg");
            imageUrl.add("http://mpic.tiankong.com/34d/ee2/34dee24f36c176651e0b64dbc8f5d170/640.jpg");
            imageUrl.add("http://mpic.tiankong.com/5a4/a2d/5a4a2dc36ad6d42ba95ee8c2afd8e038/640.jpg");
            initBanner(imageUrl);
//        } else {
//            mBanner.setVisibility(View.GONE);
//        }

    }

    private void initBanner(List<String> imageUrl) {
        mBanner.setImages(imageUrl)
                .setImageLoader(new GlideImageLoader())
                .setDelayTime(2500)
                .start();
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        mBanner.stopAutoPlay();
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mdata.clear();
                for(int i=0;i<10;i++){
                mdata.add(0,"李大康");}
                mAdapter.notifyDataSetChanged();
                swp.setRefreshing(false);
            }
        }, 1500);
    }

}
