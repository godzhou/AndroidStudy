package com.example.pengxiang.studyandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pengxiang.studyandroid.imageAdapter;

public class Gallery01Activity extends Activity {
    /** Called when the activity is first created. */
    Gallery gallery=null;
    imageAdapter imageAdapter=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);
        gallery = (Gallery) findViewById(R.id.gallery1);
        //将存放图片的ImageAdapter给gallery对象
        imageAdapter=new imageAdapter(this);
        gallery.setAdapter(imageAdapter);
        gallery.setOnItemClickListener(new OnItemClickListener(){
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {

                ImageView imgv=(ImageView)findViewById(R.id.showImg);
                // imgv.setImageResource(imageAdapter.mImageIds[arg2]);
                imgv.setImageBitmap(imageAdapter.bmp[arg2]);
                imgv.setScaleType(ImageView.ScaleType.FIT_CENTER);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //回收内存
        if(null!=imageAdapter)
        {
            imageAdapter.recycleHeap();
        }
    }

}

