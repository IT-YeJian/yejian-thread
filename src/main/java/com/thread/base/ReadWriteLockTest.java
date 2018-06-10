package com.thread.base;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * 多个读锁不互斥
 * 读锁和写锁互斥
 * 写锁和写锁互斥
 * 这是由jvm自己控制的
 * 我们只需要上好锁就可以了
 * 如果你的代码只读数据
 * 可以很多人同时读
 * 那就上读锁
 *
 * 如果你的代码修改数据
 * 只能有一个人在写
 * 且不能同时读取
 * 那就上写锁
 * 总之
 * 读的时候上读锁
 * 写的时候上写锁
 *
 * 例子
 *   三个 读
 *
 *   三个 写
 *
 *
 * @author YeJian
 * @create 2018-06-03 16:49
 **/
public class ReadWriteLockTest {


    private  static Object  data = null;

    public static void main(String[] args) {
        final Quiz q = new Quiz();

        for (int i=0;i<3;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    q.get();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                  while (true){
                      data =new Random().nextInt(10000);
                      q.put(data);
                  }

                }
            }).start();
        }
    }
   static class Quiz{

        private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        public void get(){
            readWriteLock.readLock().lock();
            try {
                System.out.println(Thread.currentThread().getName()+" 准备读了");
                Thread.sleep((long) Math.random()*1000);
                if(data==null){
                    readWriteLock.readLock().unlock();
                    readWriteLock.writeLock().lock();
                    try{
                        if(data ==null){
                            data = new Random().nextInt(10000);
                            put(data);
                        }
                    }finally {
                        readWriteLock.writeLock().unlock();
                    }
                    readWriteLock.readLock().lock();
                }
                System.out.println(Thread.currentThread().getName()+" 读到了数据"+data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                readWriteLock.readLock().unlock();
            }
        }

        public void put(Object data1){

            readWriteLock.writeLock().lock();
            try {
                System.out.println(Thread.currentThread().getName()+" 准备写入数据了");
                Thread.sleep((long) Math.random()*1000);
                data = data1;
                System.out.println(Thread.currentThread().getName()+" 写入了数据"+data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                readWriteLock.writeLock().unlock();
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

    }
}

