package com.example.pengxiang.studyandroid;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by pengxiang on 07/17 0017.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper{
    public static final String CREATE_TABLE = "create table Test ("
            +"_id integer primary key autoincrement,"
            +"username text,"
            +"data_topic text,"
            +"content text,"
            +"datetime text)";

    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int verson){
        super(context,name,factory,verson);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE);
        Toast.makeText(mContext,"CreateNew",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }
}
