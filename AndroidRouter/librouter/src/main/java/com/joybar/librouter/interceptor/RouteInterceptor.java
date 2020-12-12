package com.joybar.librouter.interceptor;


import com.joybar.librouter.RouterRequest;

public interface RouteInterceptor {
    boolean isIntercepted(RouterRequest routerRequest);
}
