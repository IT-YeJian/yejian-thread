package com.thread.base;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 栅栏
 *
 * 表示大家彼此等待
 * 大家 集合好 后 才开始出发,
 * 分散活动后又在指定地点集合碰面
 *
 * 好比郊游 先各自从家里出发到公司集合后
 * 再同时出发到公园游玩 在指定地点集合后在同时就餐
 应用场景
CyclicBarrier 适用于多线程结果合并的操作，用于多线程计算数据，最后合并计算结果的应用场景。
比如，我们需要统计多个 Excel 中的数据，然后等到一个总结果。我们可以通过多线程处理每一个 Excel 
  ，执行完成后得到相应的结果，最后通过 barrierAction 来计算这些线程的计算结果，得到所有Excel的总和
 * @author YeJian
 * @create 2018-06-01 23:37
 **/
public class CyclicBarrierTest {


    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();

        //设置三个地点 公司门口  公园   农家乐
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3,new Runnable() {
            @Override
            public void run() {
                //该操作由最后一个到达屏障的线程执行
                System.out.println(Thread.currentThread().getName()+" I is last come ");
            }
        });

        for (int i=0;i<3;i++){
            Runnable runnable= new Runnable() {
                @Override
                public void run() {
                    try {
                       Thread.sleep((long)(Math.random()*10000));
                        System.out.println("线程"+Thread.currentThread().getName()+" 到达公司门口"+(cyclicBarrier.getNumberWaiting()==2?"人到齐了 继续走啊":"正在等候"));
                        cyclicBarrier.await();

                      Thread.sleep((long)(Math.random()*10000));
                        System.out.println("线程"+Thread.currentThread().getName()+"..到达公园"+(cyclicBarrier.getNumberWaiting()==2?"人到齐了 继续走啊":"正在等候"));
                        cyclicBarrier.await();
                       Thread.sleep((long)(Math.random()*10000));
                        System.out.println("线程"+Thread.currentThread().getName()+"..到达农家乐"+(cyclicBarrier.getNumberWaiting()==2?"人到齐了 继续走啊":"正在等候"));
                        cyclicBarrier.await();

                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            };
            threadPool.execute(runnable);
        }

    }
}
