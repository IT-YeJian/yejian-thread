package com.thread.base;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 同步交换工具
 * 用于实现两个线程之间的数据交换
 * 每个线程在完成一定的事务后想与对方交换数据
 *
 *
 * 第一个先拿出数据的人将一直等待第二个人拿着数据来到时
 * 才能彼此交换数据
 * @author YeJian
 * @create 2018-06-02 13:39
 **/
public class ExchangerTest {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        final Exchanger exchanger = new Exchanger();

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String data1 = "凉粉";
                try {
                    Thread.sleep((long)Math.random()*10000);
                    System.out.println("我是卖"+data1+" 的在此等候");
                    String data2 = (String) exchanger.exchange(data1);
                    System.out.println("卖"+data1+" 的获取到了"+data2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadPool.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    String data2 = "2元钱";
                    System.out.println("我是顾客 我拿着"+data2+" 也到了");
                    Thread.sleep((long)Math.random()*10000);
                    String data1 = (String) exchanger.exchange(data2);
                    System.out.println("顾客用"+data2+" 换回了"+data1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
