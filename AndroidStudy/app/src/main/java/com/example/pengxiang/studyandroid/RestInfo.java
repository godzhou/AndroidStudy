package com.example.pengxiang.studyandroid;

/**
 * Created by pengxiang on 07/13 0013.
 */

public class RestInfo {
    private int imageId;
    private int restName;
    private int judgement;
    private String money;
    private String area;
    private String type;
    private String distance;

    public RestInfo(int imageId,int restName,int judgement,String money,String area,String type,String distance){
        this.imageId = imageId;
        this.restName = restName;
        this.judgement = judgement;
        this.money = money;
        this.area = area;
        this.type = type;
        this.distance = distance;
    }
    public int getImageId(){
        return imageId;
    }
    public int getRestName(){
        return restName;
    }
    public int getJudgement(){
        return judgement;
    }
    public String getMoney(){
        return money;
    }
    public String getArea(){
        return area;
    }
    public String getType(){
        return type;
    }
    public String getDistance(){
        return distance;
    }



}
