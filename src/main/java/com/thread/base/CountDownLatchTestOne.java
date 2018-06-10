package com.thread.base;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 犹如倒计时计数器
 * 调用CountDownLatch对象的countDown方法就将计数器减1
 * 当计数器到达0时候 则所有等待着或者单个等待着开始执行
 *
 * 者直接通过代码来说明 CountDownLatch的作用
 *
 *
 * 百米赛跑
 *
 *  1号运动员就位
 *  2号运动员就位
 *  3号运动员就位
 *  裁判即将发布命令
 * 裁判发布 跑 的命令 等待结果
 *  1号开始跑
 *  2号开始跑
 *  3号开始跑
 *
 *  1 告诉 裁判结果
 *  2 告诉裁判结果
 *  3 告诉裁判结果

 *  裁判获得所有比赛结果
 * @author YeJian
 * @create 2018-05-30 22:58
 **/
public class CountDownLatchTestOne {

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newCachedThreadPool();
        //裁判
        final CountDownLatch cdOrer = new CountDownLatch(1);
        //运动员
        final CountDownLatch cdPlayer = new CountDownLatch(3);

        for (int i=1;i<=3;i++){
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() +" 已经就位");
                        cdOrer.await();

                        System.out.println(Thread.currentThread().getName()+" 已开始跑");
                        Thread.sleep(1000);

                        System.out.println(Thread.currentThread().getName()+" 告诉 裁判结果");
                        cdPlayer.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            threadPool.execute(runnable);
        }




        try {

            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName()+" 即将发布命令");

            cdOrer.countDown();
            System.out.println(Thread.currentThread().getName()+" 等待所有比赛结果");

            cdPlayer.await();
            System.out.println(Thread.currentThread().getName()+" 获得比赛结果");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPool.shutdown();

    }
}
