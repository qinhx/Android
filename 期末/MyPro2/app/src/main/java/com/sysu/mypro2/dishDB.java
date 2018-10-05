package com.sysu.mypro2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/31 0031.
 */

public class dishDB extends SQLiteOpenHelper {
    private static final String DB_NAME = "Con.db";
    private static final String TABLE_NAME = "Con";
    private static final int DB_VERSION = 1;
    public dishDB(Context c) {
        super(c, DB_NAME, null, DB_VERSION);
    }
  //创建数据库
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "create table " + TABLE_NAME
                +"(pic text, "  //存图片
                +"name text);";  //存名字
        db.execSQL(CREATE_TABLE);  //执行命令，建立数据库
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //数据库插入语句：
    public int insert(String pic,String name){
        SQLiteDatabase db = getWritableDatabase();//这一句是必须的
        ContentValues values = new ContentValues();
        values.put("pic",pic);
        values.put("name",name);
        db.insert(TABLE_NAME,null,values);//将对应的数据插入
        Cursor cursor = db.rawQuery("select last_insert_rowid() from " + TABLE_NAME,null);
        int new_id = -1;
        if(cursor.moveToFirst()){
            new_id = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return new_id;
    }

    //数据库删除操作：
    public void delete(String name){
        SQLiteDatabase db = getWritableDatabase();
        String sql="delete from "+TABLE_NAME+" where name = "+"'name'";
        db.execSQL(sql);
        Cursor cursor=rawQuery();
        for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            String na=cursor.getString(cursor.getColumnIndex("name"));
            String pi=cursor.getString(cursor.getColumnIndex("pic"));
        }
        cursor.close();
    }
    public Cursor rawQuery(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor= db.rawQuery("select * from "+TABLE_NAME,null);
        return cursor;
    }
}
