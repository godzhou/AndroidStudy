package com.example.pengxiang.studyandroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pengxiang.studyandroid.imageAdapter;

public class ListViewActivity extends Activity {

    private List<RestInfo> restInfoList = new ArrayList<RestInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
//        // 创建ListView对象
//        ListView listview = (ListView) findViewById(R.id.listView);
//        // 定义动态数组
//        ArrayList<HashMap<String, Object>> arryList = new ArrayList<HashMap<String, Object>>();
//
//        imageAdapter ima = new imageAdapter(this);
//        // 往数组放入值
//
//        for (int k = 0; k < 6; k++) {
//            HashMap<String, Object> map = new HashMap<String, Object>();
//            map.put("Img",ima.mImageIds[k] );
//            map.put("rest_name", getString(ima.mStringIds[k]));
//            map.put("judge","还未评分");
//            if (k<3) {
//                map.put("money", "人均：￥" + (k + 1) * 15);
//            }
//            map.put("area","郫县");
//            map.put("type","店铺种类");
//            map.put("distance",300*k+"米");
//            arryList.add(map);
//        }
        // 定义适配器
//        SimpleAdapter itemAdapter = new SimpleAdapter(this, arryList,
//                R.layout.listview_item, new String[] { "Img", "rest_name","judge",
//                "money","area","type","distance" }, new int[]{R.id.img,R.id.rest_name,
//                R.id.judge,R.id.money,R.id.area,R.id.type,R.id.distance});
//        //添加适配器
//        listview.setAdapter(itemAdapter);
        initRests();

        RestAdapter restAdapter = new RestAdapter(ListViewActivity.this,R.layout.listview_item,restInfoList);
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(restAdapter);


/*        listview.setOnItemClickListener(new OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                TextView tv=(TextView)arg1.findViewById(R.id.rest_name);
                tv.setText("修改用户名");
                Toast.makeText(ListViewActivity.this, "id="+arg2, Toast.LENGTH_SHORT).show();
            }

        });*/

    }
    private void initRests(){
        RestInfo rest1 = new RestInfo(R.drawable.image11,R.string.rest_name_1,
                R.string.judge_3,"人均：17￥","郫县","川味菜肴","1800米");
        RestInfo rest2 = new RestInfo(R.drawable.image12,R.string.rest_name_2,
                R.string.judge_3,"人均：31￥","郫县","韩国料理","1800米");
        RestInfo rest3 = new RestInfo(R.drawable.image13,R.string.rest_name_3,
                R.string.judge_2,"","郫县","川味火锅","1300米");
        RestInfo rest4 = new RestInfo(R.drawable.image4,R.string.rest_name_4,
                R.string.judge_4,"","郫县","川味火锅","1700米");
        RestInfo rest5 = new RestInfo(R.drawable.image7,R.string.rest_name_5,
                R.string.judge_3,"","郫县","川味火锅","1300米");
        RestInfo rest6 = new RestInfo(R.drawable.image8,R.string.rest_name_6,
                R.string.judge_3,"","郫县","川味菜肴","1200米");
        RestInfo rest7 = new RestInfo(R.drawable.image9,R.string.rest_name_7,
                R.string.judge_2,"","郫县","自助食堂","1000米");
        RestInfo rest8 = new RestInfo(R.drawable.image10,R.string.rest_name_8,
                R.string.judge_5,"","郫县","兰州拉面","800米");

        restInfoList.add(rest1);
        restInfoList.add(rest2);
        restInfoList.add(rest3);
        restInfoList.add(rest4);
        restInfoList.add(rest5);
        restInfoList.add(rest6);
        restInfoList.add(rest7);
        restInfoList.add(rest8);
    }

    //长按菜单响应函数
    public boolean onContextItemSelected(MenuItem item){
        setTitle("点击了长按菜单里面的第"+item.getItemId()+"个项目");
        return super.onContextItemSelected(item);
    }

}
