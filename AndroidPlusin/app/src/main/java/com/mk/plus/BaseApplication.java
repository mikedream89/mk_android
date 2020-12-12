package com.mk.plus;

import android.app.Application;

import com.mk.proxy.plugin.PluginManager;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PluginManager.getInstance().setContext(this.getApplicationContext());
        PluginManager.getInstance().loadPlugin("plugin.apk");
    }
}
