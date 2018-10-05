package com.sysu.mypro2;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/30.
 */

public class Fragment1 extends Fragment {
    public MainActivity mainActivity;

    private View root;
    private TextView tvSearch;
    private ViewPager viewPager;
    private List<ImageView> pagerdata = new ArrayList<>();
    private MyAdapter adapter = new MyAdapter();
    private LinearLayout ll;

    //首页两张图片的点击事件

    private TextView header_one,header_two;

    //首页中间的菜单选项按钮
    private Button bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment1_layout, container, false);
        return root;
    }

    //初始化界面
    public void initView() {

        header_one = (TextView)root.findViewById(R.id.home_header_one);
        header_two = (TextView)root.findViewById(R.id.home_header_two);
        viewPager = (ViewPager) root.findViewById(R.id.index_viewpager);
        ll = (LinearLayout) root.findViewById(R.id.title_search);
        bt1 = (Button)root.findViewById(R.id.caidan_tb1);
        bt2 = (Button)root.findViewById(R.id.caidan_tb2);
        bt3 = (Button)root.findViewById(R.id.caidan_tb3);
        bt4 = (Button)root.findViewById(R.id.caidan_tb4);
        bt5 = (Button)root.findViewById(R.id.caidan_tb5);
        bt6 = (Button)root.findViewById(R.id.caidan_tb6);
        bt7 = (Button)root.findViewById(R.id.caidan_tb7);
        bt8 = (Button)root.findViewById(R.id.caidan_tb8);


    }

    //初始化数据
    public void initData() {


        //早中晚餐图片的滑动
        int[] pics = new int[]{R.drawable.homebanner_one,R.drawable.homebanner_two,R.drawable.homebanner_three};
        //初始化容器中的数据
        for(int i = 0;i<pics.length;i++) {
            ImageView iv_tmp = new ImageView(mainActivity);
            iv_tmp.setBackgroundResource(pics[i]);
            pagerdata.add(iv_tmp);
        }
        viewPager.setAdapter(adapter);

    }


    //事件处理
    public void initEvent() {


        //Viewpager的点击事件


        //首页两张图片的点击事件
        header_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity,SearchActivity.class);
                intent.putExtra("value","最爱");
                startActivity(intent);
            }
        });

        header_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity,SearchActivity.class);
                intent.putExtra("value","热");
                startActivity(intent);
            }
        });

        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity,SearchActivity.class);
                intent.putExtra("value","");
                startActivity(intent);
            }
        });

        //首页中间菜单的点击事件
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity,SearchActivity.class);
                intent.putExtra("value",bt1.getText());
                startActivity(intent);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity,SearchActivity.class);
                intent.putExtra("value",bt2.getText());
                startActivity(intent);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity,SearchActivity.class);
                intent.putExtra("value",bt3.getText());
                startActivity(intent);
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity,SearchActivity.class);
                intent.putExtra("value",bt4.getText());
                startActivity(intent);
            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity,SearchActivity.class);
                intent.putExtra("value",bt5.getText());
                startActivity(intent);
            }
        });
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity,SearchActivity.class);
                intent.putExtra("value",bt6.getText());
                startActivity(intent);
            }
        });
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity,SearchActivity.class);
                intent.putExtra("value",bt7.getText());
                startActivity(intent);
            }
        });
        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity,SearchActivity.class);
                intent.putExtra("value",bt8.getText());
                startActivity(intent);
            }
        });
    }



    private class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {

            return pagerdata.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;//过滤和缓存
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View child = pagerdata.get(position);
            container.addView(child);
            return child;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        initEvent();


    }
}
