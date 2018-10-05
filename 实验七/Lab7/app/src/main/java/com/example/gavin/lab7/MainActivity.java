package com.example.gavin.lab7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public  static int Mode=MODE_PRIVATE;
    public  static  final  String Preference="SaveString";
    private EditText password;
    private  EditText confirm;
    private Button ok;
    private  Button clear;
    private Toast mToast;
    static   String confirmString;
     static   String pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        password=(EditText)findViewById(R.id.password);
        confirm=(EditText)findViewById(R.id.comfirm);
        ok=(Button)findViewById(R.id.ok);
        clear=(Button)findViewById(R.id.clear);
        SharedPreferences sharedPreferences=getSharedPreferences(Preference,Mode);
        final   SharedPreferences.Editor editor=sharedPreferences.edit();
        final String pwdconfirm= sharedPreferences.getString("pwd","000");
       if (pwdconfirm.equals("000"));
        else {
           confirm.setVisibility(View.GONE);

       }
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwd=password.getText().toString();
                confirmString =confirm.getText().toString();
                if (confirmString.equals(pwd)||pwdconfirm.equals(pwd)){
                    Intent intent=new Intent(MainActivity.this,Content.class);
                    editor.clear();
                    editor.putString("pwd",pwd);
                    editor.commit();
                    startActivity(intent);
                }else {
                    if (pwd.isEmpty()||confirmString.isEmpty())
                        showToast("password can't be empty");
                    else showToast("password not match");
                }
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.getText().clear();
                confirm.getText().clear();
            }
        });
    }

    private void showToast(String msg) {
        if (mToast == null){
            mToast = Toast.makeText(this,msg,Toast.LENGTH_SHORT);
        }else{
            mToast.setText(msg);
        }
        mToast.show();
    }
}

