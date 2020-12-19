package com.demo.desgin.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//动态代理
//应用场景：例如Retrofit网络加载框架、Spring中AOP的实现等。
public class Demo2 {
    public static void main(String[] args) {
        Test2 test2 = new Test2();
        // 应该要生成自动创建的代理类，但是没有生成？？？？
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
        IPrint2 test33 = (IPrint2) Proxy.newProxyInstance(Test2.class.getClassLoader(),
                new Class[]{IPrint2.class},
                new Test2Handler(test2));
        test33.print();
    }
}

class Test2Handler implements InvocationHandler {

    private IPrint2 iPrint2;

    public Test2Handler(IPrint2 iPrint2) {

        this.iPrint2 = iPrint2;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //proxy 是 test33
        System.out.println("before print------");
        //print 的返回值
        Object o = method.invoke(this.iPrint2, args);
        System.out.println("after print------");
        return o;
    }
}

interface IPrint2 {
    void print();
    void print2();
}

class Test2 implements IPrint2 {

    @Override
    public void print() {
        System.out.println("这里是业务逻辑-----");
    }

    @Override
    public void print2() {
        System.out.println("这是test2中的第二个print-----");
    }
}
