package com.joybar.librouter;

public interface InterceptorCallback {
    void onIntercept(Object result);
    void onContinue();
}
