package com.qstm.industry.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.qstm.industry.R;
import com.qstm.industry.activity.ExploreDetailActivity;

/**
 * Created by Administrator on 2017/12/19 0019.
 * tablayout下的页面
 */

public class ExploreTabFragment extends Fragment implements AdapterView.OnItemClickListener {

//    private static final String EXTRA_CONTENT = "content";
//    public static TabListFragment newInstance(String content){
//        Bundle arguments = new Bundle();
//        arguments.putString(EXTRA_CONTENT, content);
//        TabListFragment tabContentFragment = new TabListFragment();
//        tabContentFragment.setArguments(arguments);
//        return tabContentFragment;
//    }

    private ListView mContentLv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_tab_list, null);

        mContentLv = (ListView)contentView.findViewById(R.id.lv_content);  //cardview的listview
        mContentLv.setOnItemClickListener(this);
        ViewCompat.setNestedScrollingEnabled(mContentLv, true);
        mContentLv.setAdapter(new ContentAdapter());

        return contentView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // TODO
    }

    private class ContentAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.item_explore, null);
            ImageView coverIv = (ImageView) contentView.findViewById(R.id.iv_cover);
            coverIv.setImageResource(getResources().getIdentifier("ic_palette_0"+position%4, "mipmap", getActivity().getPackageName()));
            contentView.findViewById(R.id.cv_content).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent detailIntent = new Intent(getActivity(), ExploreDetailActivity.class);
                    detailIntent.putExtra(ExploreDetailActivity.EXTRA_INDEX, position);
                    startActivity(detailIntent);
                }
            });
            return contentView;
        }
    }

}
