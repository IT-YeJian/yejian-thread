package model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author YeJian
 * @create 2018-05-13 21:11
 **/
public class WarnDelayed implements Delayed {


    /**
     * 姓名
     */
    private String name ;

    /**
     * 身份证
     */
    private String id;


    private long endTime;

    public WarnDelayed(String name,String id,long endTime){
        this.name=name;
        this.id=id;
        this.endTime=endTime;
    }

    public String getName(){
        return this.name;
    }

    public String getId(){
        return this.id;
    }

    /**
     * 用来判断是否到了截止时间
     * @param unit
     * @return
     */
    public long getDelay(TimeUnit unit) {
        return this.endTime - System.currentTimeMillis();
    }

    public int compareTo(Delayed o) {
        WarnDelayed other = (WarnDelayed)o;
        return endTime-other.endTime>0?1:0;
    }
}
