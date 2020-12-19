package com.mk.proxy.plugin;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity implements ProxyActivityInterface {
    protected Activity that;

    @Override
    public void attach(Activity proxyActivity) {
        this.that = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public <T extends View> T findViewById(int id) {
        return that.findViewById(id);
    }

    public void setContentView(View view) {
        if (that != null) {
            that.setContentView(view);
        }
    }

    public void setContentView(int layoutResID) {
        that.setContentView(layoutResID);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public void onBackPressed() {

    }

    public void startActivity(Intent intent) {
        Intent m = new Intent();
        //intent.getComponent().getClassName() 目的地包名加类名
        m.putExtra("className", intent.getComponent().getClassName());
        that.startActivity(m);
    }

    public Resources getResources() {
        if(that == null){
            return super.getResources();
        } else {
            return that.getResources();
        }
    }
}
