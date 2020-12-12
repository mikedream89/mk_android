package com.mk.proxy.plugin;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * 插件管理
 * 1. 加载已经下载的apk
 * 2. 加载apk的资源文件
 */
public class PluginManager {
    private static PluginManager pluginManager;
    private DexClassLoader loader;
    private Resources resources;
    public String pluginActivity;
    private Context context;

    private PluginManager(){}
    // 创建单例
    public static PluginManager getInstance(){
        if(pluginManager == null){
            pluginManager = new PluginManager();
        }
        return pluginManager;
    }

    public void setContext(Context context){
        this.context = context;
    }

    // 加载已经下载好的插件
    public void loadPlugin(String  pluginName){
        //创建一个私有的路径，/data/data/包名/plugin
        File pluginDir = context.getDir("plugin", Context.MODE_PRIVATE);
        String pluginPath = new File(pluginDir, pluginName).getAbsolutePath();

        loader = new DexClassLoader(pluginPath, pluginDir.getAbsolutePath(), null, context.getClassLoader());

        //通过PackageManager 获取所有 activity
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = packageManager.getPackageArchiveInfo(pluginPath, PackageManager.GET_ACTIVITIES);
        //取所有activity中第一个activity， 是AndroidManifest.xml 中第一个节点的activity， 必须强制规定这个
        pluginActivity = packageInfo.activities[0].name;

        //获取资源文件通过 assetManager
        Class<AssetManager> assetManagerClass = AssetManager.class;
        try {
            //创建 assetManager 对象
            AssetManager assetManager = assetManagerClass.newInstance();
            Method addAssetPathMethod = assetManagerClass.getMethod("addAssetPath", String.class);
            addAssetPathMethod.setAccessible(true);
            addAssetPathMethod.invoke(assetManager, pluginPath);
            //创建 resources
            resources = new Resources(assetManager, context.getResources().getDisplayMetrics(), context.getResources().getConfiguration());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //对外提供插件的classLoader
    public ClassLoader getClassLoader() {
        return loader;
    }

    //插件中的Resource
    public Resources getResource() {
        return resources;
    }
}
