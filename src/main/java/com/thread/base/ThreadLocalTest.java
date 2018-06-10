package com.thread.base;

import java.util.Random;

/**
 * 线程范围内的共享数据
 *
 * @author YeJian
 * @create 2018-05-20 10:17
 **/
public class ThreadLocalTest {

    //private static ThreadLocal<User> local = new ThreadLocal<>();
    public static void main(String[] args) {

        for (int i=0;i<2;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int data =new Random().nextInt();
                    /*User user = new User();
                    user.setName("name"+data);
                    user.setAge(data);*/
                    User.getThreadInstance().setName("name"+data);
                    User.getThreadInstance().setAge(data);
                    System.out.println(Thread.currentThread().getName()+"" +
                            " set data "+ data);

      //              local.set(user);
        //            user.getData();

                    new User().getData();

                }
            }).start();
        }
    }
   static class User {

        private String name;

        private int age ;

        private User(){}

       //单例 饥汉模式 互斥
        public static /*synchronized*/ User getThreadInstance(){
            User instance = map.get();
            if(instance ==null){
                instance = new User();
                map.set(instance);
            }
            return instance;
        }
       //单例 饥汉模式
  //      private static User instance = null;//new User(); 饱汉模式
        //要到达 多个线程内都只有一个单例的写法
       private static ThreadLocal<User> map = new ThreadLocal<>();

        public void getData(){
            User user = getThreadInstance();
            System.out.println("get data "+ user.getName()+":"+user.getAge()+" from "+Thread.currentThread().getName());
        }
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

}

