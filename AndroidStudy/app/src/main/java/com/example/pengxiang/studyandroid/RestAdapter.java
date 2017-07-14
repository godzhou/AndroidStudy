package com.example.pengxiang.studyandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by pengxiang on 07/13 0013.
 */

public class RestAdapter extends ArrayAdapter<RestInfo>{
    private int resourceId;

    public RestAdapter(Context context, int textViewResourceId, List<RestInfo> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        RestInfo restInfo = getItem(position);
        View view ;
        ViewHolder viewHolder;

        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.restImage = (ImageView)view.findViewById(R.id.img);
            viewHolder.restName = (TextView)view.findViewById(R.id.rest_name);
            viewHolder.restJudge = (TextView)view.findViewById(R.id.judge);
            viewHolder.restMoney = (TextView)view.findViewById(R.id.money);
            viewHolder.restArea = (TextView)view.findViewById(R.id.area);
            viewHolder.restType = (TextView)view.findViewById(R.id.type);
            viewHolder.restDistance = (TextView)view.findViewById(R.id.distance);
            view.setTag(viewHolder);
        }
        else{
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.restImage.setImageResource(restInfo.getImageId());
        viewHolder.restName.setText(restInfo.getRestName());
        viewHolder.restJudge.setText(restInfo.getJudgement());
        viewHolder.restMoney.setText(restInfo.getMoney());
        viewHolder.restArea.setText(restInfo.getArea());
        viewHolder.restType.setText(restInfo.getType());
        viewHolder.restDistance.setText(restInfo.getDistance());

//        ImageView restImage = (ImageView)view.findViewById(R.id.img);
//        TextView restName = (TextView)view.findViewById(R.id.rest_name);
//        TextView restJudge = (TextView)view.findViewById(R.id.judge);
//        TextView restMoney = (TextView)view.findViewById(R.id.money);
//        TextView restArea = (TextView)view.findViewById(R.id.area);
//        TextView restType = (TextView)view.findViewById(R.id.type);
//        TextView restDistance = (TextView)view.findViewById(R.id.distance);
//
//        restImage.setImageResource(restInfo.getImageId());
//        restName.setText(restInfo.getRestName());
//        restJudge.setText(restInfo.getJudgement());
//        restMoney.setText(restInfo.getMoney());
//        restArea.setText(restInfo.getArea());
//        restType.setText(restInfo.getType());
//        restDistance.setText(restInfo.getDistance());
        return view;
    }
    class ViewHolder{
        ImageView restImage;
        TextView restName;
        TextView restJudge;
        TextView restMoney;
        TextView restArea;
        TextView restType;
        TextView restDistance;
    }

}
