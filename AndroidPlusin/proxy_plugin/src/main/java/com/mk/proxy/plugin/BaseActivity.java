package com.mk.proxy.plugin;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class BaseActivity implements ProxyActivityInterface {
    private Activity that;

    @Override
    public void attach(Activity proxyActivity) {
        this.that = proxyActivity;
    }

    public void setContentView(View view) {
        if(that != null){
            that.setContentView(view);
        }
    }

    public void setContentView(int layoutResID) {
        that.setContentView(layoutResID);
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
}
