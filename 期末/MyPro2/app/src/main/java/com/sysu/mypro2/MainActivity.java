package com.sysu.mypro2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

public class MainActivity extends AppCompatActivity {

    private TextView tvSearch;
    private RadioGroup bt_radios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //默认首页
        getSupportFragmentManager().beginTransaction().replace(R.id.fl,new Fragment1()).commit();
        initEvent();

    }

    private void initView(){
        bt_radios = (RadioGroup) findViewById(R.id.rg_menu_radios);
      //  tvSearch=(TextView)findViewById(R.id.title_search_word);
    }

    private void initEvent(){
        bt_radios.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.rb_index:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fl,new Fragment1()).commit();
                        break;
                    case R.id.rb_class:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fl,new Fragment2()).commit();
                        break;
                    case R.id.rb_more:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fl,new Fragment3()).commit();
                        break;
                    default:
                        break;
                }
            }
        });
//        tvSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity.this,SearchActivity.class);
//                startActivity(intent);
//            }
//        });
    }

}
