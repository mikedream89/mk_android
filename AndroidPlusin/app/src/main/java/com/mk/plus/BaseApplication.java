package com.mk.plus;

import android.app.Application;

import com.mk.proxy.plugin.PluginManager;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PluginManager.getInstance().loadPlugin(this.getApplicationContext());
    }
}
