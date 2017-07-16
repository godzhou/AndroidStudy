package com.example.pengxiang.studyandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by pengxiang on 07/16 0016.
 */

public class CreateNewRecord extends Activity{

    TestDBActivity testDBActivity = new TestDBActivity();
    MyDataBaseAdapter t_mydatabaseadapter ;

    public Button saveBtn ;
    public EditText username ;
    public EditText topic ;
    public EditText content ;

    @Override
    public void onCreate(Bundle saveInstatceState){
        super.onCreate(saveInstatceState);
        setContentView(R.layout.create_new);

        t_mydatabaseadapter = new MyDataBaseAdapter(this);
        t_mydatabaseadapter.open();

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
                String s_username = username.getText().toString();
                String s_topic = topic.getText().toString();
                String s_content = content.getText().toString();
                System.out.println(s_content);
                testDBActivity.addData(view,t_mydatabaseadapter,s_topic,s_username,s_content);

            }
        });
    }
}
