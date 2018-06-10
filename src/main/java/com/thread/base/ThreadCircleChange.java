package com.thread.base;


/**
 * 循环交替循环10次
 * 接着主线程循环100次
 * 接着又回到10次
 * 接着又回到循环100
 * 如此循环50次
 * @author YeJian
 * @create 2018-05-18 22:31
 **/
public class ThreadCircleChange {

    public static void main(String[] args) {
        Buiness buiness = new Buiness();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1;i<=50;i++){
                    buiness.sub(i);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1;i<=50;i++){
                    buiness.main(i);
                }
            }
        }).start();
    }
}
class Buiness {

    boolean isSub = true;

    public  void sub(int i){

        synchronized(String.class){
            while (!isSub){
                try {
                    String.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int j=1;j<=10;j++){
                System.out.println("SUB Thread "+j + " loop of "+i);
            }
            isSub=false;
            String.class.notify();
        }

    }

    public  void main(int i){

        synchronized(String.class){
            while (isSub){
                try {
                    String.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int j=1;j<=100;j++){
                System.out.println("MAIN Thread "+j + " loop of "+i);
            }
            isSub= true;
            String.class.notify();
        }


    }
}
