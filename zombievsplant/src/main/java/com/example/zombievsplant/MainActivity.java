package com.example.zombievsplant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;

public class MainActivity extends AppCompatActivity {

    private CCDirector director;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建一个surfaceview，类似导演眼前的小屏幕
        CCGLSurfaceView view = new CCGLSurfaceView(this);
        setContentView(view);
        //获取导演单例
        director = CCDirector.sharedDirector();
        //开启绘制线程
        director.attachInView(view);
        //显示帧率，表示每秒刷新界面的次数，一般大于30帧，基本比较流畅,与手机性能和运行的程序有关
        director.setDisplayFPS(true);
        //设置最高帧率
        director.setAnimationInterval(1/60f);
        //设置强制横屏显示
        director.setDeviceOrientation(CCDirector.kCCDeviceOrientationLandscapeLeft);
        //用于屏幕适配，会基于不同大小的屏幕等比例缩放,设置开发时的分辨率
        director.setScreenSize(480,320);
        //创建一个场景对象
        CCScene scene = CCScene.node();
        //创建一个图层对象
        //CCLayer layer = CCLayer.node();
        //FirstLayer layer = new FirstLayer();
        ActionLayer layer = new ActionLayer();
        //给场景添加图层
        scene.addChild(layer);
        //导演运行场景
        director.runWithScene(scene);
    }

    @Override
    protected void onResume() {
        super.onResume();
        director.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        director.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        director.end();
    }
}
