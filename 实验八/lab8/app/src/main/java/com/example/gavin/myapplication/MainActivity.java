package com.example.gavin.myapplication;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button add;
    private RecyclerView lv;
    private DB dbHelper=new DB(this);;
    private List<thing> list=new ArrayList<>();
    private  SQLiteDatabase Sdb;
    private  static  final String TABLE_NAME="LContacts";
    private CommonAdapter<thing> commonAdapter=new CommonAdapter<thing>(this,R.layout.lst,list) {
        @Override
        public void convert(ViewHolder holder, thing thing) {
            TextView tv1=holder.getView(R.id.lstName);
            tv1.setText(thing.getName());
            TextView tv2=holder.getView(R.id.lstBir);
            tv2.setText(thing.getBirth());
            TextView tv3=holder.getView(R.id.lstGift);
            tv3.setText(thing.getGift());
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add=(Button)findViewById(R.id.add);
        lv=(RecyclerView) findViewById(R.id.lsv);
        lv.setLayoutManager(new LinearLayoutManager(this));
        lv.setAdapter(commonAdapter);
        //db
        Sdb=dbHelper.getWritableDatabase();
        Cursor cursor= Sdb.rawQuery("select * from "+TABLE_NAME,null);
        for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            String name=cursor.getString(cursor.getColumnIndex("name"));
            String birthday=cursor.getString(cursor.getColumnIndex("birth"));
            String gift=cursor.getString(cursor.getColumnIndex("gift"));
            list.add(new thing(name,birthday,gift));
        }
        cursor.close();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Add.class);
                startActivity(intent);
                finish();
            }
        });
        commonAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void onClick(final int position) {

                final AlertDialog alterDialog=new AlertDialog.Builder(MainActivity.this).create();
                alterDialog.show();
                WindowManager.LayoutParams params=alterDialog.getWindow().getAttributes();
                params.width=1100;
                params.height=900;
                alterDialog.getWindow().setAttributes(params);
                Window window=alterDialog.getWindow();
                window.setContentView(R.layout.alter);
                TextView tName=(TextView)window.findViewById(R.id.Alname);
                tName.setText(list.get(position).getName());
                EditText tG=(EditText)window.findViewById(R.id.Agift);
                Button cancelButton=(Button)window.findViewById(R.id.cancel);
                Button getButton=(Button)window.findViewById(R.id.get);
                final EditText GetB=(EditText)window.findViewById(R.id.Abirth);
                final EditText GetG=(EditText)window.findViewById(R.id.Agift);
                final TextView number=(TextView)window.findViewById(R.id.Anum);
                String PeoNum=getContacts(list.get(position).getName());
                number.setText(PeoNum);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alterDialog.cancel();
                    }
                });
                getButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String getGift=GetG.getText().toString();
                        String getBirth=GetB.getText().toString();
                        if (getBirth.isEmpty()&&getGift.isEmpty())
                            ;
                        else if (getBirth.isEmpty()&&!getGift.isEmpty()){
                            Sdb=dbHelper.getWritableDatabase();
                            Sdb.execSQL("update "+TABLE_NAME+" set gift= '"+getGift+"' where name= '"+list.get(position).getName()+"'");
                            list.remove(position);
                            addToList();
                        }else if (!getBirth.isEmpty()&&getGift.isEmpty()){
                            Sdb.execSQL("update "+TABLE_NAME+" set birth='"+getBirth+"' where name= '"+list.get(position).getName()+"'");
                            list.remove(position);
                            addToList();
                        }else {
                            Sdb = dbHelper.getWritableDatabase();
                            Sdb.execSQL("update " + TABLE_NAME + " set birth= '" + getBirth + "', gift='" + getGift + "' where name= '" + list.get(position).getName()+"'");
                            list.remove(position);
                            addToList();
                        }
                        commonAdapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this,"已修改",Toast.LENGTH_LONG).show();
                        alterDialog.cancel();

                    }
                });
            }
            @Override
            public void onLongClick(final int position) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.create();
                builder.setTitle("是否删除？").setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHelper.delete(list.get(position).getName());
                        list.remove(position);
                        commonAdapter.notifyDataSetChanged();
                    }
                }).show();

            }
        });
    }

    public String getContacts(String AnameForNumber) {
        // 得到ContentResolver对象
        ContentResolver cr = this.getContentResolver();
        // 取得电话本中开始一项的光标,主要就是查询"contacts"表
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            StringBuilder sbLog = new StringBuilder();
            // 取得联系人名字 (显示出来的名字)，实际内容在 ContactsContract.Contacts中
            int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            String name = cursor.getString(nameIndex);
            sbLog.append("name=" + name + ";");
            if (AnameForNumber.equalsIgnoreCase(name)) {
                // 取得联系人ID
                String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

                // 根据联系人ID查询对应的电话号码
                Cursor phoneNumbers = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = "
                        + contactId, null, null);
                // 取得电话号码(可能存在多个号码)
                while (phoneNumbers.moveToNext()) {
                    String strPhoneNumber = phoneNumbers.getString(phoneNumbers.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    return  strPhoneNumber;
                }
                phoneNumbers.close();
            }
        }
        cursor.close();
        return "nothing";
    }

    public  void  addToList(){
        Sdb=dbHelper.getWritableDatabase();
        Cursor cursor= Sdb.rawQuery("select * from "+TABLE_NAME,null);
        for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            String name=cursor.getString(cursor.getColumnIndex("name"));
            String birthday=cursor.getString(cursor.getColumnIndex("birth"));
            String gift=cursor.getString(cursor.getColumnIndex("gift"));
            list.add(new thing(name,birthday,gift));
        }
        cursor.close();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    
}}