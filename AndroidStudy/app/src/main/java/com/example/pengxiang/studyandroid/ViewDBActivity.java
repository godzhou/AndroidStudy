package com.example.pengxiang.studyandroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by pengxiang on 07/16 0016.
 */

public class ViewDBActivity extends Activity{
    private String topic;
    private String name;
    private String time;
    private String content;

    @Override
    public void onCreate(Bundle saveInstatceState){
        super.onCreate(saveInstatceState);
        setContentView(R.layout.view_db);

        Bundle extras = getIntent().getExtras();
        topic = extras.getString("topic");
        name = extras.getString("name");
        time = extras.getString("time");
        content = extras.getString("content");


        /*从数据库中查询到数据并显示*/
        TextView tv_topic = (TextView)findViewById(R.id.get_topic);
        TextView tv_name = (TextView)findViewById(R.id.get_name);
        TextView tv_time = (TextView)findViewById(R.id.get_time);
        TextView tv_content = (TextView)findViewById(R.id.get_con);

        tv_topic.setText(topic);
        tv_name.setText(name);
        tv_time.setText(time);
        tv_content.setText(content);

    }
}
