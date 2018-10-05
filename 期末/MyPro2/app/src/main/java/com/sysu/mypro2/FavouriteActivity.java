package com.sysu.mypro2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Gavin on 2018/1/1.
 */

public class FavouriteActivity extends AppCompatActivity {
    private dishDB db=new dishDB(this);
    private RecyclerView FRv;
    private List<Fthing>list=new ArrayList<>();
    static final String TABLE_NAME = "Con";
    private AllThings.ResultBeanX.ResultBean.ListBean mlistBean;
    private  CommonAdapter<Fthing> commonAdapter=new CommonAdapter<Fthing>(this,R.layout.favourite_item,list) {
        @Override
        public void convert(ViewHolder holder, Fthing fthing) {
            ImageView imageView=holder.getView(R.id.FImage);
            Glide.with(FavouriteActivity.this).load(fthing.getPic()).into(imageView);
            TextView textView=holder.getView(R.id.Fname);
            textView.setText(fthing.getName());
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favourite);
        FRv=(RecyclerView)findViewById(R.id.Frv);
        FRv.setAdapter(commonAdapter);
        FRv.setLayoutManager(new LinearLayoutManager(this));
        Cursor cursor= db.rawQuery();
        for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            String name=cursor.getString(cursor.getColumnIndex("name"));
            String pic=cursor.getString(cursor.getColumnIndex("pic"));
            list.add(new Fthing(name,pic));
            commonAdapter.notifyDataSetChanged();
        }
        cursor.close();

        commonAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent=new Intent(FavouriteActivity.this,CookTime.class);
                Retrofit retrofit=createRetrofit("https://way.jd.com/jisuapi/");
                FoodService service=retrofit.create(FoodService.class);
                rx.Observable<AllThings>observable=service.getUser(list.get(position).getName());
                observable.observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(new Observer<AllThings>() {
                            @Override
                            public void onCompleted() {
                            }
                            @Override
                            public void onError(Throwable e) {
                            }

                            @Override
                            public void onNext(AllThings repos) {
                                    mlistBean=repos.getResult().getResult().getList().get(0);
                                    EventBus.getDefault().postSticky(mlistBean);
                            }
                        });
                        startActivity(intent);
            }
            @Override
            public void onLongClick(int position) {
                SQLiteDatabase Sdb=db.getWritableDatabase();
                String Sname=list.get(position).getName();
                String sql="delete from "+TABLE_NAME+" where name = '"+Sname+"'";
                Sdb.execSQL(sql);
                    list.remove(position);
                    commonAdapter.notifyDataSetChanged();
            }
        });
    }

    private static OkHttpClient createHttp(){
        OkHttpClient okHttpClient=new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS).writeTimeout(10,TimeUnit.SECONDS).build();
        return okHttpClient;
    }

    private static Retrofit createRetrofit(String url){
        return new Retrofit.Builder().baseUrl(url)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(createHttp())
                .build();
    }
}
