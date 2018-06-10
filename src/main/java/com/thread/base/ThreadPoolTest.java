package com.thread.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 * @author YeJian
 * @create 2018-05-23 22:22
 **/
public class ThreadPoolTest {


    public static void main(String[] args) {

        //固定线程个数的线程池
        /*ExecutorService threadPool = Executors
                .newFixedThreadPool(3);*/

        //基于缓存的线程池 内部个数不固定
/*
        ExecutorService threadPool = Executors
                .newCachedThreadPool();
*/

        //单个线程 好处:这个池子的线程的死了 就会再启动一个线程 反正就保证只有一个线程
        //面试 比如 如何在一个线程死了重新启动?
        ExecutorService threadPool = Executors
                .newSingleThreadExecutor();

        for (int j=1;j<=10;j++){
            final int task = j;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    for (int i=1;i<=10;i++){
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(
                                Thread.currentThread().getName() +" loop of " + i +" for task of" +
                                        ""+task);
                    }

                }
            });
            //任务都执行完才结束
            //threadPool.shutdown();
            //带有调度任务的 线程池 每隔一段时间执行一下任务
        }
        ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(3);

        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()
                        +" bombing");
            }
        },10L, TimeUnit.SECONDS);


    }
}
