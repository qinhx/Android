package com.sysu.mypro2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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

public class SearchActivity extends AppCompatActivity {


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

    private Button confirm;
    private RecyclerView recyclerView;
    private EditText searchT;
    private AllThings AllThing;
    private  Repos mRepos;
    private List<AllThings.ResultBeanX.ResultBean.ListBean> list=new ArrayList<>();
    private ProgressBar progressBar;
    static   String urlImage;
    static Bitmap bitmap;
    private Intent inten ;
    private String value;
    final CommonAdapter<AllThings.ResultBeanX.ResultBean.ListBean> commonAdapter=new CommonAdapter<AllThings.ResultBeanX.ResultBean.ListBean>(this,R.layout.sitem,list) {
        @Override
        public void convert(ViewHolder holder, AllThings.ResultBeanX.ResultBean.ListBean listBean) {
            TextView tvName=holder.getView(R.id.itemName);
            tvName.setText(listBean.getName());
            ImageView imageView=holder.getView(R.id.itemImage);
            Glide.with(SearchActivity.this)
                    .load(listBean.getPic())

                    .into(imageView);
            TextView tvContent=holder.getView(R.id.itemContent);
            tvContent.setText(listBean.getTag());
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchactivity);

        confirm=(Button)findViewById(R.id.confirm);
        recyclerView=(RecyclerView)findViewById(R.id.Main_Rv);
        searchT=(EditText)findViewById(R.id.searchText);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        inten = getIntent();
        value = inten.getStringExtra("value");
        ForNet(value);


        recyclerView.setAdapter(commonAdapter);
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                recyclerView.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                String name=searchT.getText().toString();
                ForNet(name);
            }
        });
        commonAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                AllThings.ResultBeanX.ResultBean.ListBean listBean=list.get(position);
                EventBus.getDefault().postSticky(listBean);
                Intent intent=new Intent(SearchActivity.this,CookTime.class);
                startActivity(intent);
            }

            @Override
            public void onLongClick(int position) {

            }
        });
    }
    public  void ForNet(String name){
        Retrofit retrofit=createRetrofit("https://way.jd.com/jisuapi/");
        FoodService service=retrofit.create(FoodService.class);
        rx.Observable<AllThings>observable=service.getUser(name);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<AllThings>() {
                    @Override
                    public void onCompleted() {
                        recyclerView.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(AllThings repos) {
                        for (AllThings.ResultBeanX.ResultBean.ListBean bean :repos.getResult().getResult().getList()) {
                            list.add(bean);
                            commonAdapter.notifyDataSetChanged();
                        }

                    }

                });
    }
}