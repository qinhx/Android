package com.example.gavin.lab7;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * Created by Gavin on 2017/11/30.
 */

public class Content extends AppCompatActivity {
    private EditText fileName;
    private  EditText content;
    private Button save;
    private  Button load;
    private  Button clear;
    private  Button delete;
    private Toast mToast;
    public  FileOutputStream fos;
    public FileInputStream fis;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);
        fileName=(EditText)findViewById(R.id.fileName);
        content=(EditText)findViewById(R.id.content);
        save=(Button)findViewById(R.id.svaeOfcontent);
        clear=(Button)findViewById(R.id.clearOfContent);
        delete=(Button)findViewById(R.id.delete);
        load=(Button)findViewById(R.id.load);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String FileName=fileName.getText().toString();
                String FileContent=content.getText().toString();
                if (FileName.isEmpty())
                    showToast("fail to load file");
                else {
                    try{
                       fos=openFileOutput(FileName, Context.MODE_PRIVATE);
                        try{
                            fos.write(FileContent.getBytes());
                            fos.flush();
                            fos.close();
                            showToast("save succesfully");
                        }catch (IOException ioe){
                            ioe.printStackTrace();
                        }
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                }
            }
        });
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String FileName=fileName.getText().toString();
                try{
                    fis=openFileInput(FileName);
                    try{
                        int  length=fis.available();
                        byte[]readBytes=new byte[length];
                            fis.read(readBytes);
                            fis.close();
                        String  result=new String(readBytes);
                        content.setText(result);
                    }
                    catch (IOException IOE){
                        showToast("load successfully");
                    }
                }catch (FileNotFoundException e){
                    showToast("load fail the file");
                }
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content.getText().clear();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String FileName=fileName.getText().toString();
                    if(deleteFile(FileName))
                        showToast("delete successfully");
            }
        });

    }
    public void showToast(String msg){
        if (mToast == null){
            mToast = Toast.makeText(this,msg,Toast.LENGTH_SHORT);
        }else{
            mToast.setText(msg);
        }
        mToast.show();
    }
}
