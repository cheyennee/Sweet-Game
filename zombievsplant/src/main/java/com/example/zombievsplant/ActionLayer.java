package com.example.zombievsplant;

import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.ease.CCEaseIn;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCBezierBy;
import org.cocos2d.actions.interval.CCBlink;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCFadeIn;
import org.cocos2d.actions.interval.CCJumpBy;
import org.cocos2d.actions.interval.CCMoveBy;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCRotateBy;
import org.cocos2d.actions.interval.CCRotateTo;
import org.cocos2d.actions.interval.CCScaleTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.actions.interval.CCTintBy;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.types.CCBezierConfig;
import org.cocos2d.types.CGSize;

import java.util.ArrayList;

public class ActionLayer extends CCLayer {
    private CCSprite sprite;
    public ActionLayer(){
        sprite = CCSprite.sprite("z_1_attack_01.png");
        sprite.setAnchorPoint(ccp(0,0));
        sprite.setPosition(0,0);
        this.addChild(sprite);
        //moveTo();
        //moveBy();//水平移动
        //rotateBy();//旋转
    }
    //僵尸行走
    private void walk(){
        //先水平行走
        sprite.setFlipX(true);
        CCMoveBy move = CCMoveBy.action(5,ccp(200,0));
        sprite.runAction(move);
        //初始化7帧图片
        ArrayList<CCSpriteFrame> frames = new ArrayList<>();
        //%02d表示两位数字，如果是个位，用0去补位，01，02，10
        String format = "z_1_%02d.png";
        for(int i = 1;i<=7;i++){
            frames.add(CCSprite.sprite(String.format(format,i)).displayedFrame());

        }
        //参2表示每一帧显示时间
        CCAnimation anim = CCAnimation.animation("walk",0.2f,frames);
        CCAnimate animate = CCAnimate.action(anim);
        //表示动画永远循环，需要有这句话，否则空指针异常
        CCRepeatForever repeat = CCRepeatForever.action(animate);
        sprite.runAction(repeat);
    }
    //僵尸后空翻跳跃
    private void demo(){
        sprite.setAnchorPoint(0.5f,0.5f);
        CCJumpBy action = CCJumpBy.action(2,ccp(100,100),150,2);
        //表示延时一秒执行，也是CCAction
        CCDelayTime delay = CCDelayTime.action(1);
        CCRotateBy rotate = CCRotateBy.action(1,360);
        CCSequence se = CCSequence.actions(delay,rotate);
        //多个动作同时执行
        CCSpawn spawn = CCSpawn.actions(action,se);
        CCSequence s = CCSequence.actions(spawn,spawn.reverse());
        CCRepeatForever repeat = CCRepeatForever.action(s);
        sprite.runAction(repeat);
    }
    //颜色渐变
    private void tint(){
        CCLabel label = CCLabel.labelWithString("hello","Roboto-Thin.ttf",20);
        label.setColor(ccc3(100,50,0));
        //通过导演拿到屏幕尺寸
        CGSize winSize = CCDirector.sharedDirector().winSize();
        label.setPosition(ccp(winSize.width/2,winSize.height/2));
        this.addChild(label);
        CCTintBy tint = CCTintBy.action(3,ccc3(50,-50,100));
        CCSequence s = CCSequence.actions(tint,tint.reverse());
        //动作永远循环
        CCRepeatForever repeat = CCRepeatForever.action(s);
        label.runAction(repeat);
    }
    //闪烁
    private void blink(){
        CCBlink action = CCBlink.action(10,50);
        sprite.runAction(action);
    }
    //加速度
    private void ease(){
        CCMoveBy move = CCMoveBy.action(5,ccp(200,0));
        CCEaseIn action = CCEaseIn.action(move,5);//参2表示速率,渐快
        sprite.runAction(action);
    }
    private void bezier(){
        CCBezierConfig config = new CCBezierConfig();
        config.controlPoint_1 = ccp(100,50);
        config.controlPoint_2 = ccp(150,100);
        config.endPosition = ccp(200,50);
        CCBezierBy action = CCBezierBy.action(3,config);
        sprite.runAction(action);
    }
    private void fade(){
        CCFadeIn action = CCFadeIn.action(2);//淡入
        sprite.runAction(action);
    }
    private void jump(){
        //参三表示跳跃最高点，参四表示跳跃次数
        CCJumpBy action = CCJumpBy.action(3,ccp(100,100),200,1);
        //action.reverse()表示动作逆向执行
        //顺序执行一系列动作
        CCSequence s = CCSequence.actions(action,action.reverse());
        sprite.runAction(s);
    }
    private void scale(){
        CCScaleTo action = CCScaleTo.action(3,2);//缩放比例,参二为缩放比例
        sprite.runAction(action);
    }
    //僵尸逆时针旋转90度，达到目的
    private void rotateTo(){
        CCRotateTo action = CCRotateTo.action(3,270);
        sprite.setAnchorPoint(0.5f,0.5f);
        sprite.runAction(action);
    }
    //僵尸旋转270度
    private void rotateBy(){
        CCRotateBy action = CCRotateBy.action(3,360);
        sprite.setAnchorPoint(0.5f,0.5f);
        sprite.runAction(action);
    }
    //僵尸移动
    private void moveTo(){
        //参数1：时间；参数2：位置
        CCMoveTo action = CCMoveTo.action(3,ccp(200,0));
        sprite.runAction(action);
    }
    private void moveBy(){
        //参数1：时间；参数2：位置
        CCMoveBy action = CCMoveBy.action(3,ccp(200,0));
        sprite.runAction(action);
    }
}
