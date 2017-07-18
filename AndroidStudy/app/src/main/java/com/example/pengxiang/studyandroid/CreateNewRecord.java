package com.example.pengxiang.studyandroid;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Created by pengxiang on 07/16 0016.
 */

public class CreateNewRecord extends Activity{
    private MyDatabaseHelper dbHelper;

    public Button saveBtn ;
    public EditText username ;
    public EditText topic ;
    public EditText content ;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void onCreate(Bundle saveInstatceState){
        super.onCreate(saveInstatceState);
        setContentView(R.layout.create_new);

        dbHelper = new MyDatabaseHelper(this,"test.db4",null,4);

        saveBtn = (Button)findViewById(R.id.save);
        username = (EditText)findViewById(R.id.new_peron);
        topic = (EditText)findViewById(R.id.new_topic);
        content = (EditText)findViewById(R.id.new_content);

        String s_username = username.toString();
        String s_topic = topic.toString();
        String s_content = content.toString();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();

                System.out.println("2"+db);

                java.util.Date date = new java.util.Date(System.currentTimeMillis());
                TimeZone timeZoneSH = TimeZone.getTimeZone("Asia/Shanghai");
                sdf.setTimeZone(timeZoneSH);
                String s_datetime = sdf.format(date);

     //           long s_datetime = date.getTime();

                String s_username = username.getText().toString();
                String s_topic = topic.getText().toString();
                String s_content = content.getText().toString();
                System.out.println("3");
                System.out.println("4"+dbHelper);

                values.put("username",s_username);
                values.put("data_topic",s_topic);
                values.put("content",s_content);
                values.put("datetime",s_datetime);
                db.insert("test",null,values);
                values.clear();

                Intent intent = new Intent(CreateNewRecord.this,TestDBActivity.class);
                startActivity(intent);
            }
        });
    }
}
