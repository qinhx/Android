package com.administrator.mid_term;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.administrator.mid_term.ArcMenu.OnMenuItemClickListener;

import org.greenrobot.eventbus.EventBus;


public class MainActivity_2 extends Activity {

    private  String name;
    private  String place;
    private  String story;
    private  int PicId;
    private  String master;
    private ArcMenu mArcMenuLeftTop;
    private  int contry;
    private RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);//人物详情
        mArcMenuLeftTop = (ArcMenu) findViewById(R.id.id_arcmenu2);
        relativeLayout=(RelativeLayout)findViewById(R.id.Rla);

        //动态添加一个MenuItem
        ImageView people = new ImageView(this);
        people.setImageResource(R.mipmap.composer_with);
        people.setTag("People");
        mArcMenuLeftTop.addView(people);


        /*******************************************************/
        final Bundle intent=getIntent().getExtras();
        if (intent!=null){
            name=intent.getString("name");
            place = intent.getString("place");
            story=intent.getString("story");
            PicId=intent.getInt("picId");
            master=intent.getString("master");
            contry=intent.getInt("contry");
        }

        TextView Tname=(TextView)findViewById(R.id.name);
        TextView Tpalce=(TextView)findViewById(R.id.place);
        TextView Tmaster=(TextView)findViewById(R.id.master);
        TextView Tsty=(TextView)findViewById(R.id.styOfperson);
        ImageView Timage=(ImageView)findViewById(R.id.perImage);
        Tname.setText(name);
        Tmaster.setText(master);
        Tpalce.setText(place);
        Tsty.setText(story);
        Timage.setImageResource(PicId);


        /*********************************************************/

        mArcMenuLeftTop
                .setOnMenuItemClickListener(new OnMenuItemClickListener()
                {
                    @Override
                    public void onClick(View view, int pos)
                    {
                        if(pos==0){
                            Intent intent = new Intent(MainActivity_2.this,MainActivity.class);
                            startActivity(intent);
                        }

                        if (pos==1){
                            Intent intent = new Intent(MainActivity_2.this,MainActivity_2.class);
                            startActivity(intent);
                        }

                        else if(pos==2){
                            Intent intent = new Intent(MainActivity_2.this,MainActivity_3.class);
                            startActivity(intent);
                        }
                        else if (pos==3){
                            Intent intent1=new Intent();
                            intent1.putExtra("likeName",name);
                            intent1.putExtra("likeplace",place);
                            intent1.putExtra("likestory",story);
                            intent1.putExtra("likemaster",master);
                            intent1.putExtra("likePicId",PicId);
                            intent1.putExtra("label","1");
                            EventBus.getDefault().postSticky(intent1);
                            Toast.makeText(MainActivity_2.this,"已加入收藏",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

@Override
public  void onResume(){
    super.onResume();
    if (contry==1){
        relativeLayout.setBackgroundResource(R.mipmap.shug);

    }
    if (contry==2){
        relativeLayout.setBackgroundResource(R.mipmap.wug);

    }
    if (contry==3){
        relativeLayout.setBackgroundResource(R.mipmap.weig);

    }
}
    public  int getImageId(String name){
        Context context=getBaseContext();
        int id=getResources().getIdentifier(name,"mipmap",context.getPackageName());
        return  id;
    }
}
