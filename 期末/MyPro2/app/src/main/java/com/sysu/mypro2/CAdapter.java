package com.sysu.mypro2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/31 0031.
 */


//为收藏界面RecyclerView 添加CommonAdapter适配器，传入对应的api
public abstract class CAdapter extends RecyclerView.Adapter<ViewHolder> {
    protected Context mycontext;
    protected int myLayoutNum;
    protected List<Map<String,Object>> myData;
    protected LayoutInflater myInfalter;
    private OnItemClickListener onItemClickListener = null;

    public CAdapter (Context contextin,int layoutin,List<Map<String,Object>>datain){
        mycontext = contextin;
        myData = datain;
        myLayoutNum = layoutin;
        myInfalter = LayoutInflater.from(contextin);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = ViewHolder.get(mycontext,parent,myLayoutNum);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        convert(holder,myData.get(position));
        if(onItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override   //轻点进入，获取位置
                public void onClick(View v) {
                    onItemClickListener.onClick(holder.getAdapterPosition());
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){

                @Override  //长按删除
                public boolean onLongClick(View v) {
                    onItemClickListener.onLongClick(holder.getAdapterPosition());
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return myData.size();
    }
/*
public interface OnItemClickListener{
void onClick(int position);
boolean onLongClick(int position);
}
*/
//public abstract void convert(ViewHolder holder, Map<String, Object> s);
    public abstract void convert(ViewHolder holder, Map<String, Object> stringObjectMap);

    public interface OnItemClickListener{
        void onClick(int position);
        boolean onLongClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener= onItemClickListener;
    }
    //提取当前的文档编号：
    public String getData(int pos,String tag){//获取特定的信息
        return myData.get(pos).get(tag).toString();
    }
    public void clearData(){
        myData.removeAll(myData);
        notifyDataSetChanged();
    }
}
