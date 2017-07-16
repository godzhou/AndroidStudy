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
    // 用于打印log
    private static final String TAG = "MyDataBaseAdapter";

    // 表中一条数据的id
    public static final String KEY_ID = "_id";

    //表中一条数据的记录着
    public static final String KEY_NAME = "user_name";

    // 表中一条数据的主题
    public static final String KEY_TOPIC = "data_topic";

    //表中一条数据的时间
    public static final String KEY_DATE = "date";

    // 表中一条数据的内容
    public static final String KEY_CON = "content";

    // 数据库名称
    public final static String DB_NAME = "testuser.db";

    // 数据库表名
    private static final String DB_TABLE = "tab_user";

    // 数据库版本
    private static final int DB_VERSION = 4;

    // 本地Context对象
    private Context mContext = null;

    // 创建一个表
    private static final String DB_CREATE = "CREATE TABLE " + DB_TABLE + " ("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TOPIC + " TEXT,"
            + KEY_NAME + "TEXT," + KEY_DATE + "INTEGER," + KEY_CON + " TEXT)";

    // 执行open（）打开数据库时，保存返回的数据库对象
    private SQLiteDatabase mSQLiteDatabase = null;

    // 由SQLiteOpenHelper继承过来
    private DatabaseHelper mDatabaseHelper = null;

    private static class DatabaseHelper extends SQLiteOpenHelper {
        /* 构造函数-创建一个数据库 */
        DatabaseHelper(Context context) {
            // 当调用getWritableDatabase()
            // 或 getReadableDatabase()方法时
            // 则创建一个数据库
            super(context, DB_NAME, null, DB_VERSION);
        }

        /* 创建一个表 */
        @Override
        public void onCreate(SQLiteDatabase db) {
            // 数据库没有表时创建一个
            db.execSQL(DB_CREATE);
        }

        /* 升级数据库 */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS tab_user");
            onCreate(db);
        }
    }

    /* 构造函数-取得Context */
    public MyDataBaseAdapter(Context context) {
        mContext = context;
    }

    // 打开数据库，返回数据库对象
    public void open() throws SQLException {
        mDatabaseHelper = new DatabaseHelper(mContext);
        //mSQLiteDatabase可以直接操纵数据库
        mSQLiteDatabase = mDatabaseHelper.getWritableDatabase();//从真正意义上创建数据库
    }

    // 关闭数据库
    public void close() {
        mDatabaseHelper.close();
    }

    /* 插入一条数据 */
    /*@param 记事主题，记事者，记事内容
     *
     */
    public long insertData(String topic, String name,String content) {

        ContentValues initialValues = new ContentValues();
        java.util.Date date = new java.util.Date();
        long datetime = date.getTime();
        System.out.println(datetime);
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_TOPIC, topic);
        initialValues.put(KEY_CON,content);
        initialValues.put(KEY_DATE,datetime);

        return mSQLiteDatabase.insert(DB_TABLE, KEY_ID, initialValues);
    }

    /* 删除一条数据 */
    public boolean deleteData(long rowId) {
        // sql="delete from tab_user where id='"+rowId+"'";
        //mSQLiteDatabase.execSQL(sql);
        return mSQLiteDatabase.delete(DB_TABLE, KEY_ID + "=" + rowId, null) > 0;

    }

    /* 通过Cursor查询所有数据 */
    public Cursor fetchAllData() {
        return mSQLiteDatabase.query(DB_TABLE, new String[] { KEY_ID, KEY_NAME,
                KEY_DATE }, null, null, null, null, null);
    }

    /* 查询指定数据 */
    public Cursor fetchData(long rowId) throws SQLException {

        Cursor mCursor =

                mSQLiteDatabase.query(true, DB_TABLE, new String[] { KEY_ID, KEY_NAME,
                        KEY_DATE }, KEY_ID + "=" + rowId, null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    /* 更新一条数据 */
    public boolean updateData(long rowId, String name, String topic,String content) {
        ContentValues contentvalue = new ContentValues();
        contentvalue.put(KEY_NAME, name);
        contentvalue.put(KEY_TOPIC, topic);
        contentvalue.put(KEY_CON,content);

        return mSQLiteDatabase.update(DB_TABLE, contentvalue, KEY_ID + "=" + rowId,
                null) > 0;
    }

    /* 删除一个表 */
    public void DeleteTable()
    {
        mSQLiteDatabase.execSQL("DROP TABLE " + DB_TABLE);
    }


}
