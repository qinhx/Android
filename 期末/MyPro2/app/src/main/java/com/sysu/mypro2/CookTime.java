package com.sysu.mypro2;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gavin on 2017/12/28.
 */

public class CookTime extends AppCompatActivity {
    private List<AllThings.ResultBeanX.ResultBean.ListBean.MaterialBean> materialBeanList=new ArrayList<>();
    private RecyclerView cookMetRv;
    private  RecyclerView cookProRv;
    private ImageView cookImage;
    private TextView cookName;
    static  String name;
    static String url;
    private static final String TABLE_NAME = "Contacts";
    private dishDB db=new dishDB(this);
    private List<AllThings.ResultBeanX.ResultBean.ListBean.ProcessBean> list=new ArrayList<>();
    private Button like;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cooklayout);
        EventBus.getDefault().register(this);
        cookMetRv=(RecyclerView)findViewById(R.id.cookMetRv);
        cookProRv=(RecyclerView)findViewById(R.id.cProgressRv);
        cookProRv.setLayoutManager(new LinearLayoutManager(this));
        cookMetRv.setLayoutManager(new LinearLayoutManager(this));
        cookName=(TextView)findViewById(R.id.cName);
        cookName.bringToFront();
        cookImage=(ImageView)findViewById(R.id.cImage);
        cookImage.bringToFront();
        cookName.setText(name);
        like=(Button)findViewById(R.id.like);
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.insert(url,name);
                Intent intent=new Intent(CookTime.this,FavouriteActivity.class);
                //startActivity(intent);

            }
        });
        Glide.with(CookTime.this).load(url).override(500,200).into(cookImage);
        CommonAdapter<AllThings.ResultBeanX.ResultBean.ListBean.MaterialBean> materialBeanCommonAdapter=new CommonAdapter<AllThings.ResultBeanX.ResultBean.ListBean.MaterialBean>(this,R.layout.cookmetrvitem,materialBeanList) {
            @Override
            public void convert(ViewHolder holder, AllThings.ResultBeanX.ResultBean.ListBean.MaterialBean materialBean) {
                TextView mName=holder.getView(R.id.mName);
                mName.setText(materialBean.getMname());
                TextView mCount=holder.getView(R.id.mCount);
                mCount.setText(materialBean.getAmount());
        }
        };

        CommonAdapter<AllThings.ResultBeanX.ResultBean.ListBean.ProcessBean> processBeanCommonAdapter=new
                CommonAdapter<AllThings.ResultBeanX.ResultBean.ListBean.ProcessBean>(this,R.layout.cookprogressitem,list) {
            @Override
            public void convert(ViewHolder holder, AllThings.ResultBeanX.ResultBean.ListBean.ProcessBean processBean) {
                ImageView pic=holder.getView(R.id.proPic);
                Glide.with(CookTime.this)
                        .load(processBean.getPic())
                        .into(pic);
                TextView pContent=holder.getView(R.id.pcontent);
                pContent.setText(processBean.getPcontent());
            }
        };


        cookMetRv.setAdapter(materialBeanCommonAdapter);
        cookProRv.setAdapter(processBeanCommonAdapter);
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public  void Recive(AllThings.ResultBeanX.ResultBean.ListBean listBean){
       list=listBean.getProcess();
       materialBeanList=listBean.getMaterial();
       name=listBean.getName();
       url=listBean.getPic();
    }

}
