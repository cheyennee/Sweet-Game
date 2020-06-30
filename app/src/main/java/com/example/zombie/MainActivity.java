package com.example.zombie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    private GameUI gameUI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameUI = new GameUI(this);
        setContentView(gameUI);
    }
    //相应触摸时间，发射炮弹
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gameUI.handleTouch(event);
        return true;
    }
}
