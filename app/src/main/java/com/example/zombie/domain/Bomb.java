package com.example.zombie.domain;

import android.graphics.Bitmap;
import android.graphics.Point;

public class Bomb extends Sprite {
    //移动速度
    private int speed = 10;
    //x偏移
    private int dx;
    //y偏移
    private int dy;
    public Bomb(Bitmap mDefaultBitmap, Point point,Point targetPoint) {
        super(mDefaultBitmap, point);
        int x = targetPoint.x - point.x;
        int y = targetPoint.y - point.y;
        int d = (int) Math.sqrt(x * x + y * y);//炸弹移动的距离
        dx = x * speed / d;
        dy = y * speed / d;
    }
    //炸弹移动
    public void move(){
        mPoint.x += dx;
        mPoint.y += dy;
    }
    //获取炸弹当前显示位置
    public Point getPoint(){
        return mPoint;
    }
}
