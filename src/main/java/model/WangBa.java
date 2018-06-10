package model;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * 网吧类
 *
 * @author YeJian
 * @create 2018-05-13 21:12
 **/
public class WangBa implements Runnable {


    private DelayQueue<WarnDelayed> queue = new DelayQueue<WarnDelayed>();

    public boolean open =true;


    public void shangji(String name,String id,int money){
        long endTime =500*60*money+System.currentTimeMillis();
        //long nanoTime = TimeUnit.NANOSECONDS.convert(endTime, TimeUnit.MILLISECONDS);
        WarnDelayed man = new WarnDelayed(name,id,endTime);
        System.out.println("网名"+man.getName()+" 身份证"+man.getId()+"交钱"+money+"块 过期时间在"+1000*60*money+System.currentTimeMillis()+",开始上机...");
        this.queue.add(man);
    }

    public void run() {
        while(open){
            try {
                System.out.println("检查中...");
                WarnDelayed man = queue.take();
                xiaji(man);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void xiaji(WarnDelayed man){
        System.out.println("网名"+man.getName()+" 身份证"+man.getId()+"时间到下机...");
    }
}
