package com.example.pengxiang.studyandroid;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class imageAdapter extends BaseAdapter{

    //定义Content
    private Context mContext;
    public Bitmap[] bmp=null;
    public String[] tex=null;


    //定义一个数组，存放图片资源
    public  Integer[] mImageIds = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6,
            R.drawable.image7,
            R.drawable.image8,
            R.drawable.image9,
            R.drawable.image10,
            R.drawable.image11,
            R.drawable.image12,
            R.drawable.image13,
            R.drawable.image14
    };

    //构造
    public imageAdapter(Context c){
        mContext = c;
        bmp = new Bitmap[mImageIds.length];
        tex = new String[mImageIds.length];
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inPreferredConfig = Bitmap.Config.RGB_565; //颜色组合方式
        opts.inPurgeable = true;  //收回内存，像素被清除
        opts.inInputShareable = true;
        for(int i=0;i<mImageIds.length;i++){
            InputStream is = mContext.getResources().openRawResource(mImageIds[i]);
            tex[i] = "image" + (i+1);
            bmp[i] = BitmapFactory.decodeStream(is,null,opts); //将普通图片转换成位图
        }
    }

    //获取图片的个数
    public int getCount() {
        // TODO Auto-generated method stub
        return mImageIds.length;
    }

    //获取图片在库中的位置
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    //获取图片在库中的ID
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    //将图片取出来
    public View getView(int position, View convertView, ViewGroup parent) {
        //要取出图片，即要定义一个ImageView来存
        ImageView imageView = new ImageView(mContext);
        TextView textView = new TextView(mContext);
        LinearLayout layout = new LinearLayout(mContext);
        //imageView.setImageResource(mImageIds[position]);
        imageView.setImageBitmap(bmp[position]);
        //设置显示比例类型
        //设置布局图片以105*150显示 （简单解释——设置数字不一样，图片的显示大小不一样）
        imageView.setLayoutParams(new Gallery.LayoutParams(180,240));
        //imageView.setLayoutParams(new Gallery.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
        //imageView.setLayoutParams(new Gallery.LayoutParams(150, 150));
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        textView.setText(tex[position]);

        layout.setOrientation(LinearLayout.VERTICAL);

        layout.addView(imageView);
        layout.addView(textView);
        return layout;
    }

    //回收图片
    public void recycleHeap() {
        if (bmp!=null&&bmp.length>0) {
            int n = bmp.length;
            for(int i=0;i<n;i++) {
                Bitmap bitmap = bmp[i];
                if (bitmap != null && !bitmap.isRecycled()) {
                    bitmap.recycle();
                    bitmap = null;
                }
            }
        }
    }

}


