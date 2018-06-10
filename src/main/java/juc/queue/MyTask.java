package juc.queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 队列中要调度的任务
 *
 * @author YeJian
 * @create 2018-05-13 20:30
 **/
public class MyTask<T extends Runnable> implements Delayed {


    /**
     * 到期时间
     */
    private final long time;

    /**
     * 任务对象
     */
    private final T task;

    private static final AtomicLong atomic = new AtomicLong(0);

    private final long n;

    public MyTask(long timeout,T t){
        this.time = System.nanoTime()+timeout;
        this.task = t;
        this.n = atomic.getAndIncrement();
    }


    public long getDelay(TimeUnit unit) {
       return unit.convert(this.time - System.nanoTime(),TimeUnit.NANOSECONDS);
    }

    public int compareTo(Delayed other) {
            if(other == this){
                return 0;
            }

            if(other instanceof MyTask){
                MyTask x = (MyTask) other;
                long diff = time - x.time;
                if(diff<0){
                    return -1; //当前排前面
                }else if(diff>0){
                    return 1;////否则other排前面
                }else if(n<x.n){
                    return -1; //当前对象排前面
                }else{
                    return 1;
                }
            }
            long d =getDelay(TimeUnit.NANOSECONDS) - other.getDelay(TimeUnit.NANOSECONDS);
            return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
    }

    public T getTask(){
        return  this.task;
    }

    @Override
    public int hashCode() {
        return task.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof MyTask) {
            return object.hashCode() == hashCode() ? true : false;
        }
        return false;
    }
}
