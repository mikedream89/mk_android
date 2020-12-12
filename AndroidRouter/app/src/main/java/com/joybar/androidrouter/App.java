package com.joybar.androidrouter;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.joybar.librouter.IRouter;
import com.joybar.modulebase.application.ApplicationService;
import com.joybar.moduleshop.application.ShopApplication;
import com.joybar.moduleuser.application.UserReleaseApplication;
import com.me.obo.routerguider.RouterGuider;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import dalvik.system.DexFile;

public class App extends Application implements ApplicationService {

    private static App INSTANCE = null;
    private static final String TAG = "App";

    public static App getInstance() {
        Log.d(TAG,"get application");
        return INSTANCE;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        INSTANCE = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RouterGuider.inject(this);

        initRegister(this);

        loadModuleApplicationService();
    }

    //注册所有的Activity
    public void initRegister(Context context) {
        String ROUTER_MANAGER_PKN = "com.cn.routermanager.helper";
        String ROUTER_MANAGER_METHOD_NAME = "registerRouter";
        List<String> classNameList = getClassName(ROUTER_MANAGER_PKN, context);
        for (String className : classNameList) {
            try {
                System.out.println("classFullName=" + className);
                Class clazz = Class.forName(className);
                //确定此 类对象表示的类或接口是否与指定的 类参数表示的类或接口相同，或者是它的超类或超接口
                if(!IRouter.class.isAssignableFrom(clazz)){
                    continue;
                }
                Method method = clazz.getDeclaredMethod(ROUTER_MANAGER_METHOD_NAME);
                System.out.println("method=" + method);
                method.invoke(clazz);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取指定包名下 的所有类
     * @param packageName
     * @return
     */
    public List<String> getClassName(String packageName, Context context) {
        List<String> classNameList = new ArrayList<String>();
        try {
            DexFile df = new DexFile(context.getPackageCodePath());//通过DexFile查找当前的APK中可执行文件
            Enumeration<String> enumeration = df.entries();//获取df中的元素  这里包含了所有可执行的类名 该类名包含了包名+类名的方式
            while (enumeration.hasMoreElements()) {//遍历
                String className = enumeration.nextElement();
                if (className.contains(packageName)) {//在当前所有可执行的类里面查找包含有该包名的所有类
                    classNameList.add(className);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classNameList;
    }

    @Override
    public void loadModuleApplicationService() {
        UserReleaseApplication.getInstance().loadModuleApplicationService();
        ShopApplication.getInstance().loadModuleApplicationService();
    }

    @Override
    public Application getApplication() {
        return getInstance();
    }

}
