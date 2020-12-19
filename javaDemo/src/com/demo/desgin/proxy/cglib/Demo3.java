package com.demo.desgin.proxy.cglib;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

//使用cglib实现动态代理（java的动态代理）， 比jdk的动态代理要简单
public class Demo3 {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Test.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("before print -------");
                Object result = methodProxy.invokeSuper(o, objects);
                System.out.println("after print -----");
                return result;
            }
        });
        Test test = (Test) enhancer.create();
        test.print();
    }
}

class Test {
    public void print(){
        System.out.println("这里是业务逻辑------");
    }
}