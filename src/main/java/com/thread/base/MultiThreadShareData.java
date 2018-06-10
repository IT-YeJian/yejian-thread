package com.thread.base;

/**
 * 多个线程共享数据
 *
 * @author YeJian
 * @create 2018-05-20 11:46
 **/
public class MultiThreadShareData {

    public static void main(String[] args) {
        new Thread().start();
        new Thread().start();
    }

    class ShareData implements Runnable{

        @Override
        public void run() {

        }

        private int j;

        public synchronized void incr(){
            j++;
        }

        public synchronized void decr(){
            j--;
        }
    }
}
