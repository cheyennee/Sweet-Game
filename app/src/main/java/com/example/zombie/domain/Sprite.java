package com.example.zombie.domain;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

//精灵基类
public class Sprite {
    //默认图片
    public Bitmap mDefaultBitmap;
    //显示位置
    public Point mPoint;
    public Sprite(Bitmap mDefaultBitmap, Point mPoint){
        this.mDefaultBitmap = mDefaultBitmap;
        this.mPoint = mPoint;
    }
    //绘制自身
    public void drawSelf(Canvas canvas){
        if(mDefaultBitmap != null){
            //绘制图片
            canvas.drawBitmap(mDefaultBitmap,mPoint.x,mPoint.y,null);
        }
    }
}
