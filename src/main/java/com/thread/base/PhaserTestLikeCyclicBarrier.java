package com.thread.base;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @author YeJian (jian.ye@downjoy.com)
 * @Description:
 * @date 2018/7/27 15:17
 * java多线程技术提供了Phaser工具类，Phaser表示“阶段器”，
 * 用来解决控制多个线程分阶段共同完成任务的情景问题
 * 。其作用相比CountDownLatch和CyclicBarrier更加灵活，
 * 例如有这样的一个题目：
 * 5个学生一起参加考试，一共有三道题，
 * 要求所有学生到齐才能开始考试，
 * 全部同学都做完第一题，
 * 学生才能继续做第二题，
 * 全部学生做完了第二题，
 * 才能做第三题，
 * 所有学生都做完的第三题，
 * 考试才结束。
 * 分析这个题目：这是一个多线程（5个学生）分阶段问题
 * （考试考试、第一题做完、第二题做完、第三题做完），
 * 所以很适合用Phaser解决这个问题。
 * Phaser有phase和party两个重要状态，
 * phase表示阶段，party表示每个阶段的线程个数，
 * 只有每个线程都执行了phaser.arriveAndAwaitAdvance();
 * 才会进入下一个阶段，否则阻塞等待。
 * 例如题目中5个学生(线程)都条用phaser.arriveAndAwaitAdvance();就进入下一题
 *
 * Phaser替代CyclicBarrier比较简单，CyclicBarrier的await()方法可以直接用Phaser的arriveAndAwaitAdvance()方法替代
CyclicBarrier与Phaser:CyclicBarrier只适用于固定数量的参与者,而Phaser适用于可变数目的屏障.

CountDownLatch主要使用的有2个方法
 * await()方法，可以使线程进入等待状态，在Phaser中，与之对应的方法是awaitAdvance(int n)。
 * countDown()，使计数器减一，当计数器为0时所有等待的线程开始执行，在Phaser中，与之对应的方法是arrive()
 */
public class PhaserTestLikeCyclicBarrier {

    private static void doExercise1() {
        long duration = (long)(Math.random()*10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private  static void doExercise2() {
        long duration = (long)(Math.random()*10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void doExercise3() {
        long duration = (long) (Math.random() * 10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws InterruptedException {

        //表示有5个学生 去做3道题
        Phaser phaser  =new Phaser(5);
        for (int i=1;i<=5;i++){
            final int num  = i;
            Thread t = new Thread(){
                @Override
                public void run() {
                    System.out.println("第"+num+"个学生到达考试" +System.currentTimeMillis());
                    phaser.arriveAndAwaitAdvance();

                    System.out.println("第"+num+"个学生做第1题时间...");
                    doExercise1();
                    System.out.println("第"+num+"个学生做第1题完成...");
      //              phaser.arriveAndDeregister(); 告知phaser对应的线程已经完成了当前阶段，并它不会参与到下一阶段的操作中。
                    phaser.arriveAndAwaitAdvance();

                    System.out.println("第"+num+"个学生做第2题时间...");
                    doExercise2();
                    System.out.println("第"+num+"个学生做第2题完成...");
                    phaser.arriveAndAwaitAdvance();

                    System.out.println("第"+num+"个学生做第3题时间...");
                    doExercise3();
                    System.out.println("第"+num+"个学生做第3题完成...");
                   // phaser.arriveAndDeregister();
                    phaser.arriveAndAwaitAdvance();

                }
            };
            Thread.sleep(1234);
            System.out.println("未达到考场的学生个数：" + phaser.getUnarrivedParties());
            t.start();
        }

        System.out.println("注册总个数：" + phaser.getRegisteredParties()+"==="+System.currentTimeMillis());
        System.out.println("注册总个数222：" + phaser.getArrivedParties()+"==="+System.currentTimeMillis());
       /* while(!phaser.isTerminated()) {
            System.out.println("完成个数：" + phaser.getRegisteredParties() + "\t" + System.currentTimeMillis());
            Thread.sleep(20);
        }*/
        System.out.println("end .....");
    }
}
