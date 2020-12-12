package com.joybar.librouter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.joybar.librouter.interceptor.RouteInterceptor;


public interface IRouterManagerService {

    IRouterManagerService buildRule(Rule rule);

    IRouterManagerService withExtra(Bundle bundle);

    IRouterManagerService withInterceptorCallback(InterceptorCallback interceptorCallback);

    IRouterManagerService addInterceptor(RouteInterceptor routeInterceptor);

    boolean isIntercepted();

    void navigate(Context context);

    void navigate(Context context,int flag);

    void navigate(Activity activity,int requestCode);


}
