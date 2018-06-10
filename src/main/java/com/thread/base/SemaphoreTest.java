package com.thread.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 *
 * Semaphore 实现信号灯
 * 可以维护当前访问自身的线程个数
 * 并提供了同步机制
 * 使用Semaphore可以控制同事访问资源的线程个数
 * 例如一个文件允许的访问数
 * 另外 其构造函数Semaphore传入的参数可以决定
 *
 * 等待的线程 在有喜好等获取的情况下
 * 采取什么策略获取信号灯
 *
 * 单个信号量的Semaphore对象可以实现互斥的、
 * 功能，并且可以是由一个线程 获得了锁
 * 再由另一个线程释放 锁
 *  这可以用于  死锁 恢复的场景
 *  注意 syn 关键字或者 lock 的那些锁
 *  只能通过自己去释放 锁
 *
 *
 *
 *
 * @author YeJian
 * @create 2018-05-31 22:18
 **/
public class SemaphoreTest {


    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();

        Semaphore semaphore =  new Semaphore(1);

        Runnable runnable  =new Runnable() {
            @Override
            public void run() {

                int i=0;
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"线程 进入 开始计算" +
                            "可用"+(1-semaphore.availablePermits())+"个 信号");

                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                semaphore.release();
                System.out.println(Thread.currentThread().getName()+"end");
            }
        };
        threadPool.execute(runnable);

        while(true){
            if(semaphore.availablePermits()==0){
                System.out.println(Thread.currentThread().getName()+"我是保安锁 我来救你start");
                semaphore.release();
                System.out.println(Thread.currentThread().getName()+ "我是保安锁 我来救你end");
                break;
            }
        }
/*

        for (int i=1;i<=10;i++){

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程:"+Thread.currentThread().getName()+"进入," +
                            "-->当前已有"+(3-semaphore.availablePermits())+"并发");

                    try {
                        Thread.sleep((long)Math.random() *10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程"+Thread.currentThread().getName()+"即将离开");
                    semaphore.release();
                    System.out.println("线程"+Thread.currentThread().getName()+"已经离开,当前已有" +
                            ""+(3-semaphore.availablePermits())+"并发");
                }
            };
            threadPool.execute(runnable);
        }

*/

    }
}
