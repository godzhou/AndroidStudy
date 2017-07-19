package com.example.pengxiang.studyandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Activity03 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.http2);

        Button button = (Button) this.findViewById(R.id.button03);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity03.this.onClick(v);
            }
        });

    }
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {

            ImageView imgView = (ImageView) findViewById(R.id.picture);
            imgView.setImageBitmap((Bitmap) msg.obj);
        }
    };

    public Bitmap getInternetPicture(String UrlPath) {
        Bitmap bm = null;
        //String urlpath = "http://csdnimg.cn/www/images/csdnindex_logo.gif";
        // 2、获取Uri
        try {
            URL url = new URL(UrlPath);
            //获取连接对象
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            //初始化连接对象
            httpURLConnection.setRequestMethod("GET");
            // 读取超时
            httpURLConnection.setReadTimeout(5000);
            // 设置连接超时
            httpURLConnection.setConnectTimeout(5000);

            if (httpURLConnection.getResponseCode() == 200) {
                // 7、拿到服务器返回的流，客户端请求的数据，就保存在流当中
                InputStream is = httpURLConnection.getInputStream();
                // 8、从流中读取数据，构造一个图片对象GoogleAPI
                bm = BitmapFactory.decodeStream(is);
                // 9、把图片设置到UI主线程
                // ImageView中,获取网络资源是耗时操作需放在子线程中进行,通过创建消息发送消息给主线程刷新控件；
                Log.i("", "网络请求成功");

            } else {
                Log.v("tag", "网络请求失败");
                bm = null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bm;

    }
    public void onClick(View v){
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Bitmap bm = getInternetPicture("http://csdnimg.cn/www/images/csdnindex_logo.gif");
                Message msg = new Message();
                // 把bm存入消息中,发送到主线程
                msg.obj = bm;
                handler.sendMessage(msg);
            }
        }).start();
    }
}