package com.example.gavin.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Gavin on 2017/12/7.
 */

public class Add extends AppCompatActivity {
    private EditText name;
    private EditText birthday;
    private EditText gift;
    private Button add;
    private SQLiteDatabase Sdb;
    private  static  final String TABLE_NAME="LContacts";
    private DB dbHelper=new DB(this);;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        name=(EditText)findViewById(R.id.Addname);
        birthday=(EditText)findViewById(R.id.Addbirthday);

        gift=(EditText)findViewById(R.id.gift);
        add=(Button)findViewById(R.id.addContent);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Sname=name.getText().toString();
                String Sbir=birthday.getText().toString();
                String SGift=gift.getText().toString();
                if (!Sname.isEmpty()){
                    Intent intent=new Intent(Add.this,MainActivity.class);
                    String sql="insert into "+TABLE_NAME+" (name,birth,gift) values('"+Sname+"','"+Sbir+"','"+SGift+"')";
                    Sdb =  dbHelper.getWritableDatabase();
                    dbHelper.insert(sql,Sdb);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(Add.this,"请完善名字", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
