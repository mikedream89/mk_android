package com.demo.thread;

public class MRunnable {
    public static class WorkRunnable implements Runnable{

        @Override
        public void run() {
            System.out.println(" 这是 runnable 线程");
        }
    }

    public static class WorkThread extends Thread{
        @Override
        public void run() {
            super.run();
            System.out.println(" 这是 继承Thread的 创建的线程");
        }
    }

    // thread runnable 都是没有返回值的
    public static void main(String[] args) {

        Thread thread = new Thread(new WorkRunnable());
        thread.start();

        WorkThread workThread = new WorkThread();
        workThread.start();
    }
}
