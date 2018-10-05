package com.administrator.mid_term;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.administrator.mid_term.ArcMenu.OnMenuItemClickListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_3 extends Activity {
    private List<Person> list=new ArrayList<>();
    private   CommonAdapter<Person> commonAdapter;
    private ArcMenu mArcMenuLeftTop;
    private  Person person;
    private  int contry;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_3);//收藏
        mArcMenuLeftTop = (ArcMenu) findViewById(R.id.id_arcmenu3);
        //动态添加一个MenuItem
        ImageView people = new ImageView(this);
        people.setImageResource(R.mipmap.composer_with);
        people.setTag("People");
        mArcMenuLeftTop.addView(people);
        commonAdapter=new CommonAdapter<Person>(this,R.layout.w_rec,list) {
            @Override
            public void convert(ViewHolder holder, Person person) {
                TextView textView=holder.getView(R.id.W_tv);
                textView.setText(person.getName());
                ImageView imageView=holder.getView(R.id.W_im);
                imageView.setImageResource(person.getPicId());
            }
        };

        EventBus.getDefault().register(this);
         RecyclerView recyclerView=(RecyclerView)findViewById(R.id.likeRv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(commonAdapter);
        mArcMenuLeftTop.bringToFront();
        mArcMenuLeftTop
                .setOnMenuItemClickListener(new OnMenuItemClickListener()
                {
                    @Override
                    public void onClick(View view, int pos)
                    {
                        if(pos==0){
                            Intent intent = new Intent(MainActivity_3.this,MainActivity.class);
                            startActivity(intent);
                        }

                        if (pos==1){
                            Intent intent = new Intent(MainActivity_3.this,MainActivity_2.class);
                            startActivity(intent);
                        }

                        else if(pos==2){
                            Intent intent = new Intent(MainActivity_3.this,MainActivity_3.class);
                            startActivity(intent);
                        }

                    }
                });
        commonAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent=new Intent(MainActivity_3.this,MainActivity_2.class);
                intent.putExtra("name",list.get(position).getName());
                intent.putExtra("place",list.get(position).getPlace());
                intent.putExtra("story",list.get(position).getStory());
                intent.putExtra("master",list.get(position).getMaster());
                intent.putExtra("picId",list.get(position).getPicId());
                intent.putExtra("contry",list.get(position).getContry());
                startActivity(intent);
            }
            @Override
            public void onLongClick(int position) {
                    list.remove(position);
                commonAdapter.notifyDataSetChanged();
            }
        });
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onMessageEvent(Intent intent){
        String name,place,story,master;
        int PicId,contry;
        name=intent.getExtras().getString("likeName");
        place=intent.getExtras().getString("likeplace");
        story=intent.getExtras().getString("likestory");;
        master=intent.getExtras().getString("likemaster");
        contry=intent.getExtras().getInt("country");
        PicId=intent.getExtras().getInt("likePicId");
        person=new Person(name,place,master,story,PicId,contry);
        list.add(person);
        commonAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    public  int getImageId(String name){
        Context context=getBaseContext();
        int id=getResources().getIdentifier(name,"mipmap",context.getPackageName());
        return  id;
    }
}
