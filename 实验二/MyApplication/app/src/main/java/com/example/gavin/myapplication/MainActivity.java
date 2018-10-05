package com.example.gavin.myapplication;
import android.content.DialogInterface;
import android.support.annotation.IdRes;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
        private TextInputLayout mNum;
        private TextInputLayout mPW;
        private RadioButton mRB1;
         private RadioButton mRB2;
         private RadioGroup mRadiogroup;
         private ImageView mImageView;
          private Button mLogin;
          private  Button mRegister;
          private EditText mEditNum;
          private EditText mEditPw;
    void init(){
        mNum=(TextInputLayout) findViewById(R.id.Num);
        mPW=(TextInputLayout)findViewById(R.id.PW);
        mRadiogroup=(RadioGroup)findViewById(R.id.radioGroup);
        mRB1=(RadioButton)findViewById(R.id.RB1);
        mRB2=(RadioButton)findViewById(R.id.RB2);
        mImageView=(ImageView)findViewById(R.id.imageView);
        mLogin=(Button)findViewById(R.id.button1);
        mRegister=(Button)findViewById(R.id.button2);
        mEditNum=mNum.getEditText();
        mEditPw=mPW.getEditText();

        mNum.setHint("请输入学号");
        mPW.setHint("请输入密码");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        mRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group,int checkedId){
                if(checkedId==R.id.RB1){
                    if(checkedId==mRB1.getId()){
                        Toast.makeText(MainActivity.this,"您选择了学生",Toast.LENGTH_SHORT).show();
                        Snackbar.make(mRadiogroup,"您选择了学生",Snackbar.LENGTH_SHORT)
                                .setAction("确认", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                    }
                                }).setActionTextColor(getResources().getColor(R.color.colorPrimary))
                                .show();
                    }
                }
                else if (checkedId==mRB2.getId()){
                    Toast.makeText(MainActivity.this,"您选择了教职工",Toast.LENGTH_SHORT).show();
                    Snackbar.make(mRadiogroup,"您选择了教职工",Snackbar.LENGTH_SHORT).setAction("确认", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    })
                            .setDuration(5000)
                            .show();
                }
            }
        });
        final AlertDialog.Builder mbuilder=new AlertDialog.Builder(this);
        final  String[] items = new String[]{"从相册中选择","拍摄"};
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mbuilder.setTitle("上传头像")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                Toast.makeText(MainActivity.this,"你选择了取消",Toast.LENGTH_SHORT).show();
                            }
                        }).setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Toast.makeText(MainActivity.this,"你选择了["+items[i]+"]",Toast.LENGTH_SHORT).show();
                    }
                });
                mbuilder.create().show();;
            }
        });
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = mEditNum.getText().toString();
                String password = mEditPw.getText().toString();
                if (TextUtils.isEmpty(number)){
                    if(number.isEmpty()){
                    mNum.setErrorEnabled(true);
                    mNum.setError("学号不能为空");
                }
                if (TextUtils.isEmpty(password)){
                        mPW.setErrorEnabled(true);
                        mPW.setError("密码不能为空");
                    }
                }
                if (TextUtils.equals(number,"123456")&&TextUtils.equals(password,"6666")){
                    Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    Snackbar.make(mLogin,"登录成功",Snackbar.LENGTH_SHORT)
                            .setAction("确认", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                }
                            })
                            .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                            .setDuration(5000)
                            .show();
                }
                else {
                    Toast.makeText(MainActivity.this,"学号或者密码错误",Toast.LENGTH_SHORT).show();
                    Snackbar.make(mLogin,"学号或者密码错误",Snackbar.LENGTH_SHORT)
                           .setAction("确认", new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {
                               }
                           })
                            .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                            .setDuration(5000)
                            .show();
                }

            }
        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<mRadiogroup.getChildCount();i++){
                    RadioButton RB=(RadioButton)mRadiogroup.getChildAt(i);
                    if(RB.isChecked()){
                        Toast.makeText(MainActivity.this,RB.getText()+"注册功能尚未启用",Toast.LENGTH_SHORT).show();
                        Snackbar.make(mRegister,RB.getText()+"注册功能尚未启用",Snackbar.LENGTH_SHORT)
                                .setAction("确认", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                    }
                                })
                                .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                                .setDuration(5000)
                                .show();
                    }
                }
            }
        });

    }
}
