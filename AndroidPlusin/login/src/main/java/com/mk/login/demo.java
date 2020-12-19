package com.mk.login;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class demo {

    private static class work implements Callable<Boolean>{

        @Override
        public Boolean call() throws Exception {
            System.out.println("=================");
            return true;
        }
    }

    public static void main(String[] args) {
        FutureTask<Boolean> futureTask = new FutureTask(new work());
        try {
            Boolean aBoolean = futureTask.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread thread = new Thread(futureTask);
        thread.start();
    }
}
