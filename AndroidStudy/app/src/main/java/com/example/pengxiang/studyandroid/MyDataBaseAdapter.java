package com.example.pengxiang.studyandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDataBaseAdapter {

    // 表中一条数据的id
    public static final String KEY_ID = "_id";

    // 表中一条数据的主题
    public static final String KEY_TOPIC = "data_topic";

    //表中一条数据的时间
    public static final String KEY_DATE = "datetime";

    // 表中一条数据的内容
    public static final String KEY_CON = "content";

    public static final String KEY_NAME = "username";

    // 数据库表名
    private static final String DB_TABLE = "Test";

    // 本地Context对象
    private Context mContext = null;

    // 创建一个表
//    private static final String DB_CREATE = "CREATE TABLE " + DB_TABLE + " ("
//            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TOPIC + " TEXT,"
//            + KEY_NAME + "TEXT," /*+ KEY_DATE + "INTEGER,"*/ + KEY_CON + " TEXT)";

    private MyDatabaseHelper myDatabaseHelper = null;
    // 执行open（）打开数据库时，保存返回的数据库对象
    private SQLiteDatabase mSQLiteDatabase = null;


    /* 构造函数-取得Context */
    public MyDataBaseAdapter(Context context) {
        mContext = context;
    }

    // 打开数据库，返回数据库对象
    public void open() throws SQLException {
        myDatabaseHelper = new MyDatabaseHelper(mContext,"test.db4",null,4);
        //mSQLiteDatabase可以直接操纵数据库
        mSQLiteDatabase = myDatabaseHelper.getWritableDatabase();//从真正意义上创建数据库
    }


    /* 删除一条数据 */
    public boolean deleteData(long rowId) {
        // sql="delete from tab_user where id='"+rowId+"'";
        //mSQLiteDatabase.execSQL(sql);
        return mSQLiteDatabase.delete(DB_TABLE, KEY_ID + "=" + rowId, null) > 0;

    }

    /* 通过Cursor查询所有数据 */
    public Cursor fetchAllData() {
        myDatabaseHelper = new MyDatabaseHelper(mContext,"test.db4",null,4);
        mSQLiteDatabase = myDatabaseHelper.getWritableDatabase();
        return mSQLiteDatabase.query(DB_TABLE, new String[] { KEY_ID, KEY_TOPIC,
                KEY_DATE }, null,null, null, null, null, null);
    }

    /* 查询指定数据 */
    public Cursor fetchData(long rowId) throws SQLException {

        Cursor mCursor =

                mSQLiteDatabase.query(true, DB_TABLE, new String[] { KEY_TOPIC, KEY_NAME,
                        KEY_DATE, KEY_CON }, KEY_ID + "=" + rowId, null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }


    /* 删除一个表 */
    public void DeleteTable()
    {
        mSQLiteDatabase.execSQL("DROP TABLE " + DB_TABLE);
    }



}
