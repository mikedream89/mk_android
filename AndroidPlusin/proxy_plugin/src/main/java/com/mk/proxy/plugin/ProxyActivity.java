package com.mk.proxy.plugin;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Constructor;

/**
 * 代理Activity（插桩）
 * 把当前Activity的所有的属性都给到需要跳转的activity
 */
public class ProxyActivity extends AppCompatActivity {

    private String activityName;
    private ProxyActivityInterface pluginObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityName = getIntent().getStringExtra("className");
        try {
            //反射拿到要跳转的 activity
            Class<?> aClass = PluginManager.getInstance().getClassLoader().loadClass(activityName);
            Constructor<?> constructor = aClass.getConstructor(new Class[]{});
            pluginObj = (ProxyActivityInterface) constructor.newInstance(new Object[]{});
            //把当前ProxyActivity 的上下文 注入到需要跳转的activity中
            pluginObj.attach(this);
            pluginObj.onCreate(savedInstanceState);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startActivity(Intent intent) {
        if (activityName == null || activityName.isEmpty()) {
            //没有需要跳转的 activity
            return;
        }
        Intent intent1 = new Intent(this, ProxyActivity.class);
        intent1.putExtra("ClassName", activityName);
        super.startActivity(intent);
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
        if (pluginObj != null)
            pluginObj.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (pluginObj != null)
            pluginObj.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (pluginObj != null)
            pluginObj.onPause();
    }

}