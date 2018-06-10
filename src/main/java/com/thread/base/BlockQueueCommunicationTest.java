package com.thread.base;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 阻塞队列的通信
 * 用两个具有一个空间的队列来实现通信
 *
 * 主线程 20次
 * 子线程10
 * 如此往复50次
 * @author YeJian
 * @create 2018-06-03 20:51
 **/
public class BlockQueueCommunicationTest {


    public static void main(String[] args) {
        Business business = new Business();
        new Thread(new Runnable() {
            @Override
            public void run() {
                    for (int i = 1; i <= 50; i++) {
                        business.sub(i);
                    }
            }
        }).start();


            for (int i = 1; i <= 50; i++) {
                business.main(i);
            }

    }

    static class  Business {

        BlockingQueue<Integer> bOne = new ArrayBlockingQueue<Integer>(1);

        BlockingQueue<Integer> bTwo = new ArrayBlockingQueue<Integer>(1);
        //这是匿名构造函数  实例化多少次 就 执行多少次 .和 static修饰的时候则是类加载 执行一次
        {
            try {
                bTwo.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void sub(int i){
            try {
                bTwo.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int j=1;j<=10;j++){
                System.out.println("this sub "+ j + "loop of "+i);
            }
            try {
                bOne.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void main(int i){
            try {
                bOne.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int j=1;j<=20;j++){
                System.out.println("this main  "+ j + "loop of "+i);
            }
            try {
                bTwo.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
