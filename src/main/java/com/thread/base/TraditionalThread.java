package com.thread.base;

/**
 * 传统线程创建方式
 *
 * @author YeJian
 * @create 2018-05-13 19:48
 **/
public
class TraditionalThread {


    public static void main(String[] args) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                while(true){
                    System.out.println(Thread.currentThread().getName());
                }
            }
        };
        thread.start();

        new Thread(new Runnable() {
            public void run() {

            }
        }){
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread --"+ Thread.currentThread().getName());
            }
        }.start();
    }
}
