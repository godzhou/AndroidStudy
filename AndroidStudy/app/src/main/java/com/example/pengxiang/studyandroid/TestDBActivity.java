package com.example.pengxiang.studyandroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TestDBActivity extends Activity {

    public MyDatabaseHelper dbHelper;
    public MyDataBaseAdapter myDataBaseAdapter;
    public TextView _id;

    private int lastPress = 0;
    private static int miCount = 0;
    public Button addBtn;



    /* 列表视图-显示数据库中的数据 */
    ListView m_ListView = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        dbHelper = new MyDatabaseHelper(this,"test.db",null,4);

		/* 创建ListView对象 */
        m_ListView=(ListView)findViewById(R.id.listView);

		/* 构造MyDataBaseAdapter对象 */
        myDataBaseAdapter = new MyDataBaseAdapter(this);
        System.out.print("2"+myDataBaseAdapter);

		/* 取得数据库对象 */
//        m_MyDataBaseAdapter.open();
		/*查询数据库表信息*/
        UpdataAdapter();

        /*新建记事时间监听*/
        addBtn = (Button)findViewById(R.id.addData);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.getWritableDatabase();
                System.out.println("1"+dbHelper);
                Intent intent = new Intent(TestDBActivity.this,CreateNewRecord.class);
                startActivity(intent);
                finish();
            }
        });

        m_ListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                new AlertDialog.Builder(TestDBActivity.this).setTitle("删除窗口")
                        .setMessage("是否删除这一项？")
                        .setPositiveButton("确定",new DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialog,int which){

                                _id = (TextView)findViewById(R.id.date_id);

                                myDataBaseAdapter.deleteData(Long.valueOf(_id.getText().toString()));
                                System.out.println("true");
                                UpdataAdapter();
                            }
                        }).setNegativeButton("返回",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog,int which){
                        System.out.println("back");

                    }
                }).show();

                return true;
            }
        });

        m_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                _id = (TextView)view.findViewById(R.id.date_id);
              //  Toast.makeText(TestDBActivity.this," "+Long.valueOf(_id.getText().toString()),Toast.LENGTH_SHORT).show();
                Cursor cursor = myDataBaseAdapter.fetchData(Long.valueOf(_id.getText().toString()));

                String c_name = cursor.getString(cursor.getColumnIndex("username"));
                String c_topic = cursor.getString(cursor.getColumnIndex("data_topic"));
                String c_content = cursor.getString(cursor.getColumnIndex("content"));
                String c_time = cursor.getString(cursor.getColumnIndex("datetime"));

                cursor.close();

                Bundle bundle = new Bundle();
                bundle.putString("topic",c_topic);
                bundle.putString("name",c_name);
                bundle.putString("time",c_time);
                bundle.putString("content",c_content);

                Intent intent = new Intent(TestDBActivity.this,ViewDBActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

    }



    final int[] vi = new int[] {R.id.date_id,R.id.data_topic,R.id.data_time};

    /* 更新视图显示 */
    public void UpdataAdapter() {
        // 获取数据库Phones的Cursor
        Cursor cur = myDataBaseAdapter.fetchAllData();
        miCount = cur.getCount();
        if (cur != null && cur.getCount() >= 0) {
            // ListAdapter是ListView和后台数据的桥梁
            ListAdapter adapter = new SimpleCursorAdapter(this,
                    // 定义List中每一行的显示模板
                    // 表示每一行包含两个数据项
                    R.layout.listviewitem,
                    // 数据库的Cursor对象
                    cur,
                    // 从数据库的TABLE_NUM和TABLE_DATA两列中取数据
                    new String[] {
                            MyDataBaseAdapter.KEY_ID,
                            MyDataBaseAdapter.KEY_TOPIC,
                            MyDataBaseAdapter.KEY_DATE },
                    // 与NAME和NUMBER对应的Views
                    vi);

			/* 将adapter添加到m_ListView中 */
            m_ListView.setAdapter(adapter);
        }
    }



}