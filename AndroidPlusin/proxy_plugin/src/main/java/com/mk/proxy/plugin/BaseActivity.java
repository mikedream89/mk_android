package com.mk.proxy.plugin;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class BaseActivity implements ProxyActivityInterface {
    protected Activity that;

    @Override
    public void attach(Activity proxyActivity) {
        this.that = proxyActivity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

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
        return that.getResources();
    }
}
