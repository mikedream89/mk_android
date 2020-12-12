package com.mk.proxy.plugin;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;

/**
 * 代理Activity（插桩）
 * 把当前Activity的所有的属性都给到需要跳转的activity
 */
public class ProxyActivity extends Activity {

    private ProxyActivityInterface pluginObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String activityName = getIntent().getStringExtra("className");
        try {
            //反射拿到要跳转的 activity
            Class<?> aClass = PluginManager.getInstance().getClassLoader().loadClass(activityName);

            Object activity = aClass.newInstance();
            //判断activity是否是ProxyActivityInterface到子类
            if (activity instanceof ProxyActivityInterface) {
                pluginObj = (ProxyActivityInterface) activity;
                //把当前ProxyActivity 的上下文 注入到需要跳转的activity中
                pluginObj.attach(ProxyActivity.this);
                pluginObj.onCreate(savedInstanceState);
            }

//            //反射拿activity
//            Constructor<?> constructor = aClass.getConstructor(new Class[]{});
//            pluginObj = (ProxyActivityInterface) constructor.newInstance(new Object[]{});

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startActivity(Intent intent) {
        String activityName = intent.getStringExtra("className");
        //activityName 目的地地包名加类名
        if (activityName == null || activityName.isEmpty()) {
            //没有需要跳转的 activity
            return;
        }
        // 从ProxyActivity跳转到ProxyActivity 重新通过代理Activity在加载一个activity
        Intent intent1 = new Intent(ProxyActivity.this, ProxyActivity.class);
        intent1.putExtra("className", activityName);
        super.startActivity(intent1);
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getClassLoader();
    }

    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getResource();
    }

    @Override
    protected void onStart() {
        super.onStart();
        pluginObj.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pluginObj.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pluginObj.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        pluginObj.onStop();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        pluginObj.onBackPressed();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        pluginObj.onSaveInstanceState(outState);
    }
}