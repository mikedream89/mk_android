package com.demo.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MCallable {

    // callable 是有返回值的，runnable是没有返回值的
    public static class WorkThread implements Callable<Boolean>{
        @Override
        public Boolean call() throws Exception {
            System.out.println("这是线程 callable");
            return false;
        }
    }

    public static void main(String[] args) {
        FutureTask<Boolean> futureTask = new FutureTask<>(new WorkThread());
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            Boolean aBoolean = futureTask.get();
            System.out.println(" 这个是 callable 返回值"+ aBoolean);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
