package com.example.zombie;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.zombie.domain.Bomb;
import com.example.zombie.domain.Boy;
import com.example.zombie.domain.MyButton;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameUI extends SurfaceView implements SurfaceHolder.Callback {
    //标记是否开启线程
    private boolean isRender;
    //private Bomb bomb;
    //private ArrayList<Bomb> bombArrayList = new ArrayList<>();
    private CopyOnWriteArrayList<Bomb> bombArrayList = new CopyOnWriteArrayList<>();
    private Boy boy;
    private MyButton myButton;
    public GameUI(Context context) {
        super(context);
        //获取放映机
        SurfaceHolder holder = getHolder();
        //添加回调接口，这样才能在surfaceview中回调生命周期的api
        holder.addCallback(this);
    }

    //surfaceview创建
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isRender = true;
        //bomb = new Bomb(BitmapFactory.decodeResource(getResources(),R.drawable.heart),new Point(0,0),new Point(200,200));
        boy = new Boy(BitmapFactory.decodeResource(getResources(),R.drawable.pig),new Point(0,0));
        myButton = new MyButton(BitmapFactory.decodeResource(getResources(),R.drawable.default_button),BitmapFactory.decodeResource(getResources(),R.drawable.press_button),new Point(60,getHeight() - 250));
        myButton.setClickListener(new MyButton.ClickListener() {
            @Override
            public void click() {
                int y = getHeight() - boy.mDefaultBitmap.getHeight();
                if(boy.mPoint.y < y){
                    boy.move(Boy.MOVE_DOWN);
                }
            }
        });
        RenderThread renderThread = new RenderThread();
        //开启绘制线程
        renderThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //停止线程
        isRender = false;
    }
    //绘制线程
    class RenderThread extends Thread{
        @Override
        public void run(){
            //使用while循环，不断绘制界面，保证界面能够不断发生变化，从而实现动画效果
            while (isRender){
                drawUI();
            }
        }
    }
    //绘制屏幕
    private void drawUI(){
        //画布,上锁防止其他线程打扰本线程绘画
        Canvas canvas = getHolder().lockCanvas();
        //有可能刚刚进入线程，但程序已经停止运行，从而导致空指针
        if(canvas != null){
            //画笔,清洗屏幕
            Paint paint = new Paint();
            paint.setColor(Color.GRAY);
            canvas.drawRect(0,0,getWidth(),getHeight(),paint);
            boy.drawSelf(canvas);
            myButton.drawSelf(canvas);
            /*if(bomb != null){
                bomb.drawSelf(canvas);
                bomb.move();
            }*/
            //遍历炸弹的集合
            /*for(int i = 0;i<bombArrayList.size();i++){
                Bomb bomb = bombArrayList.get(i);
                bomb.drawSelf(canvas);
                bomb.move();
                Point point = bomb.getPoint();
                //判断炸弹是否移出屏幕
                if(point.x > getWidth() || point.x < 0
                ||point.y > getHeight()||point.y<0){
                    bombArrayList.remove(bomb);
                }
            }*/
            //解决并发修改异常
            /*ArrayList<Bomb> list = new ArrayList<>();
            list.addAll(bombArrayList);*/
            for(Bomb bomb:bombArrayList){
                bomb.drawSelf(canvas);
                bomb.move();
                Point point = bomb.getPoint();
                //判断炸弹是否移出屏幕
                if(point.x > getWidth() || point.x < 0
                        ||point.y > getHeight()||point.y<0){
                    bombArrayList.remove(bomb);
                }
            }
            //解锁画布并提交
            getHolder().unlockCanvasAndPost(canvas);
        }
    }
    public void handleTouch(MotionEvent event){
        int x = (int) event.getX();
        int y = (int) event.getY();
        Point point = new Point(x,y);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(!myButton.isClick(point)){
                    Bomb bomb = boy.createBomb(getContext(),new Point(x,y));
                    bombArrayList.add(bomb);
                }else{

                }
                break;
            case MotionEvent.ACTION_UP:
                myButton.setClick(false);//抬起时，需要将图片置为默认图片
                break;
        }
    }
}
