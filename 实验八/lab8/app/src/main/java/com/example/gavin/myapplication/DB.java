package com.example.gavin.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Gavin on 2017/12/7.
 */

public class DB extends SQLiteOpenHelper{
    private  static  final String DB_NAME="LContacts.db";
    private  static  final String TABLE_NAME="LContacts";
    private  static  final  int DB_VERSION=1;
    @Override
    public synchronized void close() {
        super.close();
    }

        public  DB(Context context){
            super(context,DB_NAME,null,DB_VERSION);
        }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE="create table "+TABLE_NAME
                +"(name text , birth text ,gift text)";
                db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insert(String sql,SQLiteDatabase db)
    {
        db.execSQL(sql);
    }
    public void delete(String sql, SQLiteDatabase db) {
            db.execSQL(sql);
    }

    public void delete(String name) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = "name = ?"; // 主键列名 = ?
        String[] whereArgs = { name}; // 主键的值
        db.delete(TABLE_NAME, whereClause, whereArgs);
        db.close();
    }
}
