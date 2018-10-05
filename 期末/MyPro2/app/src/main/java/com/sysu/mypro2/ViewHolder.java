package com.sysu.mypro2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Gavin on 2017/10/25.
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> myViews;  // 存储list_Item的子View
    private View myConvertView;     // 存储list_Item
    public ViewHolder(Context context, View itemView, ViewGroup parent) {
        super(itemView);
        myConvertView = itemView;
        myViews = new SparseArray<View>();
    }
    public static ViewHolder get(Context context, ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        ViewHolder holder = new ViewHolder(context, itemView, parent);
        return holder;
    }

    public <T extends View> T getView(int viewId) {
        View view = myViews.get(viewId);
        if (view == null) {
            view = myConvertView.findViewById(viewId);
            myViews.put(viewId, view);
        }
        return (T) view;
    }

}