package juc.queue;

import model.WangBa;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延迟队列测试类
 *
 * 假设镇上开了一家无限大的网吧，每人交一块钱可以上一分钟网(过年时的网吧坑爹程度差不多就这样)；

 网吧比较老式没有先进的管理系统，为了知道哪个家伙时间到该下机了，老板得一台一台地去看，
 上网的人一多等老板把整个网吧转
 一遍有些家伙早超时了，而且老板要一遍一遍地检查也累个半死；
 * @author YeJian
 * @create 2018-05-13 21:05
 **/
public class WarnDelayedTest {

    public static void main(String[] args) {
        try{
            System.out.println("网吧开始营业");
            WangBa yejian = new WangBa();
            Thread open = new Thread(yejian);
            open.start();

            yejian.shangji("路人甲", "123", 1);
            yejian.shangji("路人乙", "234", 2);
            yejian.shangji("路人丙", "345", 1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}


