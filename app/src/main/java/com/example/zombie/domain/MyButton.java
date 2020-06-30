package com.example.zombie.domain;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.Timer;
import java.util.TimerTask;

public class MyButton extends Sprite {
    private boolean isclick;//是否被点击
    private Bitmap mPressBitmap;//被点击后的图片
    private Timer timer;
    public MyButton(Bitmap mDefaultBitmap,Bitmap mPressBitmap, Point mPoint) {
        super(mDefaultBitmap, mPoint);
        this.mPressBitmap = mPressBitmap;
    }

    @Override
    public void drawSelf(Canvas canvas) {
        if(!isclick){
            super.drawSelf(canvas);
        }else{
            canvas.drawBitmap(mPressBitmap,mPoint.x,mPoint.y,null);
        }
    }

    public boolean isClick(Point point){
        Rect rect = new Rect(mPoint.x,mPoint.y,mPoint.x+mDefaultBitmap.getWidth(),mPoint.y+mDefaultBitmap.getHeight());
        isclick = rect.contains(point.x,point.y);//是否点击了按钮
        if(isclick){
            if(listener != null){
                //启动定时器,可以保证当一直按住按钮不放时，小人可以持续移动
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        listener.click();
                    }
                },0,200);
            }
        }
        return isclick;
    }

    public void setClick(boolean isclick){
        this.isclick = isclick;
        if(!isclick){
            if(timer!=null){
                timer.cancel();//停止定时器
            }
        }
    }
    private ClickListener listener;
    public void setClickListener(ClickListener listener){
        this.listener = listener;
    }
    //按钮点击的回调接口
    public interface ClickListener{
        public void click();
    }
}
