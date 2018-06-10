package com.thread.base;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 阻塞队列的例子
 *
 * 两个线程放数据
 * 1个线程取数据
 * @author YeJian
 * @create 2018-06-03 20:38
 **/
public class BlockingQueueTest {


    public static void main(String[] args) {
        //声明一个阻塞队列
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(3);

        for (int i=0 ;i<=2;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        try {
                            Thread.sleep((long) Math.random() *1000);
                            System.out.println(Thread.currentThread().getName()+" 准备放入数据了");
                            queue.put(1);
                            System.out.println("已放入数据，当前队列数据个数是"+queue.size());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName()+" 准备取出数据了");
                        queue.take();
                        System.out.println("已经取出数据了,当前队列数据个数是"+queue.size());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
