package com.thread.base;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer类
 *
 * @author YeJian
 * @create 2018-05-15 23:27
 **/
public class TraditionalTimerTest {

    private static int x=0;


    public static void main(String[] args) {

        class MyTimerTask extends  TimerTask{

            public void run() {
                x=(x+1)%2;
                System.out.println("bombing");
                new Timer().schedule(new MyTimerTask() /*{
                    @Override
                    public void run() {
                        System.out.println("bombing");
                    }
                }*/,2000+2000*x);
            }
        }

        //延迟2s 开始炸 以后每隔2s炸一次 4s又炸一次 2s再炸一次 如此往复
        new Timer().schedule(new MyTimerTask()
        , 2000); //第一次到10s才炸 后每隔3秒都炸
        while (true){
            System.out.println(Calendar.getInstance().get(Calendar.SECOND));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 延迟10s 炸弹开始炸 以后每隔3s 炸一次
     */
    private void test1(){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("bombing");
            }
        }, 10000,3000); //第一次到10s才炸 后每隔3秒都炸
        while (true){
            System.out.println(Calendar.getInstance().get(Calendar.SECOND));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
