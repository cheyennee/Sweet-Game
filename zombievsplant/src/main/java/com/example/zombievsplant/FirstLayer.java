package com.example.zombievsplant;

import android.view.MotionEvent;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

public class FirstLayer extends CCLayer {
    private static final int TAG = 1;
    public FirstLayer(){
        //参数表示图片在assets目录下的相对路径
        CCSprite sprite = CCSprite.sprite("z_1_attack_01.png");
        //默认锚点是（0.5，0.5）
        sprite.setAnchorPoint(0,0);
        //sprite.setPosition(ccp(sprite.getContentSize().width/2.0f, sprite.getContentSize().height/2.0f));
        //水平翻转
        //sprite.setFlipX(true);
        //设置不透明度，0-255，255表示完全不透明
        //sprite.setOpacity(100);
        //宽高变为原来的2倍，面积是4倍
        //sprite.setScale(2);
        //添加一个精灵
        //this.addChild(sprite);
        //默认优先级为0
        this.addChild(sprite,0,TAG);
        //打开点击事件，默认是关闭的
        setIsTouchEnabled(true);
    }

    //点击监听
    @Override
    public boolean ccTouchesBegan(MotionEvent event) {
        //转化cocos2d坐标为安卓坐标
        CGPoint converTouchToNodeSpace = convertTouchToNodeSpace(event);
        //通过TAG找到僵尸对象
        CCSprite sprite = (CCSprite) this.getChildByTag(TAG);
        //判断该坐标点有没有落在僵尸上
        if(CGRect.containsPoint(sprite.getBoundingBox(),converTouchToNodeSpace)){

        }
        return super.ccTouchesBegan(event);
    }
}
