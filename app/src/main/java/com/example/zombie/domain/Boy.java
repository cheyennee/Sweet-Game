package com.example.zombie.domain;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

import com.example.zombie.R;

public class Boy extends Sprite {
    public static final int MOVE_DOWN = 0;
    public static final int MOVE_UP = 1;
    public static final int MOVE_LEFT = 2;
    public static final int MOVE_RIGHT = 3;
    private int speed = 5;
    public Boy(Bitmap mDefaultBitmap, Point mPoint) {
        super(mDefaultBitmap, mPoint);
    }
    //产生炸弹
    public Bomb createBomb(Context ctx,Point targetPoint){
        Bomb bomb = new Bomb(BitmapFactory.decodeResource(ctx.getResources(), R.drawable.heart),new Point(mPoint.x+20,mPoint.y+20),targetPoint);
        return bomb;
    }
    public void move(int direction){
        switch(direction){
            case MOVE_DOWN:
                mPoint.y += speed;
                break;
            case MOVE_UP:
                break;
            case MOVE_LEFT:
                break;
            case MOVE_RIGHT:
                break;
        }
    }
}
