package com.administrator.mid_term;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Gavin on 2017/10/25.
 */

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    private Context myContext;
    private List<T> myDatas;
    private int myLayoutId;
    private OnItemClickListener myOnItemClickListener;

    public CommonAdapter(Context context, int layoutId, List<T> data_) {
        myContext = context;
        myDatas = data_;
        myLayoutId = layoutId;
    }

    // 监听器接口及函数
    public interface OnItemClickListener {
        void onClick(int position);

        void onLongClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.myOnItemClickListener = onItemClickListener;
    }

    // 创建item视图
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        ViewHolder viewHolder = ViewHolder.get(myContext, parent, myLayoutId);
        return viewHolder;
    }

    // 绑定数据到正确的item视图上
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        convert(holder, myDatas.get(position));

        if (myOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myOnItemClickListener.onClick(holder.getPosition());
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    myOnItemClickListener.onLongClick(holder.getPosition());
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return myDatas.size();
    }

    public abstract void convert(ViewHolder holder, T t);
}
