package com.joybar.librouter;

import android.os.Bundle;

public class RouterRequest {

    private Rule rule;
    private InterceptorCallback interceptorCallback;
    private Bundle bundle;

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public InterceptorCallback getInterceptorCallback() {
        return interceptorCallback;
    }

    public void setInterceptorCallback(InterceptorCallback interceptorCallback) {
        this.interceptorCallback = interceptorCallback;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

}
