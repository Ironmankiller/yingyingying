package com.qstm.industry.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.qstm.industry.Model.Item;
import com.qstm.industry.R;

import java.util.List;

public class ClassifyAdapter extends ArrayAdapter<Item>{
    private int resourceId;
    public ClassifyAdapter(Context context, int textViewResourceId, List<Item> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId ;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Item item=getItem(position);
        View view=LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView itemName=(TextView)view.findViewById(R.id.list_name);
        itemName.setText(item.getName());
        return view;
    }


}

