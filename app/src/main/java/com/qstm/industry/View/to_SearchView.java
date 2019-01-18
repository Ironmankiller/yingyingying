package com.qstm.industry.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.qstm.industry.R;


public class to_SearchView extends LinearLayout {
    private EditText et_search;     //输入框
    public to_SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //加载布局文件
        View view = LayoutInflater.from(context).inflate(R.layout.to_search, null);

        //获取控件
        et_search = (EditText) view.findViewById(R.id.et_search);

        //把布局添加到当前控件中
        ViewGroup.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(view, params);
    }
}
