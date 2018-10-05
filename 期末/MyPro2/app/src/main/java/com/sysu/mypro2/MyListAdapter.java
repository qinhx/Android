package com.sysu.mypro2;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/1/1.
 */

public class MyListAdapter extends BaseAdapter {

    private String[] data;
    private Context context;
    int mSelect = 0;   //选中项
    MyListAdapter(String[] data,Context context){
        this.data=data;
        this.context = context;
    }

    public void changeSelected(int positon){ //刷新方法
        if(positon != mSelect){
            mSelect = positon;
            notifyDataSetChanged();
        }
    }
    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        TextView textView;
        if (convertView==null){
            view = LayoutInflater.from(context).inflate(R.layout.fragment2_item1,null);
        }
        else {
            view = convertView;
        }
        textView = (TextView) view.findViewById(R.id.text);
        textView.setText(data[position]);
        if (mSelect==position){
            //item被选中时字体颜色
            textView.setTextColor(Color.parseColor("#FF6600"));
            textView.setBackgroundColor(Color.parseColor("#ffffff"));
        }
        else {
            //平时的颜色
            textView.setTextColor(Color.parseColor("#000000"));
            textView.setBackgroundColor(Color.parseColor("#DCDCDC"));
        }
        return view;
    }
}
