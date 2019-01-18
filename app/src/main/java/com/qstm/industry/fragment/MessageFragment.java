package com.qstm.industry.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.qstm.industry.R;
import com.qstm.industry.activity.ChatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    private ListView lv;
    private SimpleAdapter adapter;
    private List<android.support.v4.app.Fragment> mFragmentArrays = new ArrayList<>();
    private List<String> mTabs = new ArrayList<>();
    private View view;
    private List<Map<String, Object>> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_message, container, false);
        lv = (ListView) view.findViewById(R.id.message_listview);
        return view;
    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new SimpleAdapter(getActivity(), getData(), R.layout.item_message,
                new String[]{"img", "title", "body"},
                new int[]{R.id.mes_picture, R.id.mes_name, R.id.mes_content});      //配置适配器，并获取对应Item中的ID
        lv.setAdapter(adapter);

        ListView listView = (ListView) getActivity().findViewById(R.id.message_listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
                Intent chatIntent = new Intent(getActivity(), ChatActivity.class);
                //chatIntent.putExtra(ExploreDetailActivity.EXTRA_INDEX, position);
                startActivity(chatIntent);
                //Toast.makeText(getActivity(), "点击列表有值了，好神奇", Toast.LENGTH_LONG).show();
            }
        });
    }
    //数据的获取！
    private List<? extends Map<String, ?>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        //将需要的值传入map中
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "软院最新公告事项");
        map.put("body", "不知道未来几天有什么最新消息？那就点我查看查看呗");
        map.put("img", R.mipmap.gv_animation);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "校内最新消息通知");
        map.put("body", "校级活动，化工电影本周放啥？艺设妹子有什么动向？点我查看");
        map.put("img", R.mipmap.gv_animation);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "圈内交流园地");
        map.put("body", "来都来了，何不进来说几句？");
        map.put("img", R.mipmap.gv_animation);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "校内最新消息通知");
        map.put("body", "校级活动，化工电影本周放啥？艺设妹子有什么动向？点我查看");
        map.put("img", R.mipmap.gv_animation);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "圈内交流园地");
        map.put("body", "来都来了，何不进来说几句？");
        map.put("img", R.mipmap.gv_animation);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "校内最新消息通知");
        map.put("body", "校级活动，化工电影本周放啥？艺设妹子有什么动向？点我查看");
        map.put("img", R.mipmap.gv_animation);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "圈内交流园地");
        map.put("body", "来都来了，何不进来说几句？");
        map.put("img", R.mipmap.gv_animation);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "校内最新消息通知");
        map.put("body", "校级活动，化工电影本周放啥？艺设妹子有什么动向？点我查看");
        map.put("img", R.mipmap.gv_animation);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "圈内交流园地");
        map.put("body", "来都来了，何不进来说几句？");
        map.put("img", R.mipmap.gv_animation);
        list.add(map);

        return list;
    }

}
