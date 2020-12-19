package com.demo.desgin.proxy;

//静态代理
//在 print 方法的前面和后面加入日志
public class Demo1 {
    public static void main(String[] args) {
        Proxy proxy = new Proxy(new Test());
        proxy.print();

    }
}

interface IPrint {
    void print();
}

class Proxy implements IPrint{
    IPrint iPrint;
    public Proxy(IPrint iPrint){
        this.iPrint = iPrint;
    }
    @Override
    public void print() {
        System.out.println("before print ------");
        iPrint.print();
        System.out.println("after print ------");
    }
}

class Test implements IPrint {
    @Override
    public void print() {
        System.out.println("这里是业务逻辑----");
    }
}
