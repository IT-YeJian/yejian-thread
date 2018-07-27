package com.thread.base;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 *  Fork/Join RecursiveTask带有返回值的
 *           RecursiveAction没有返回值
 *           采用 “工作窃取”模式（work-stealing）：
 *           当执行新的任务时它可以将其拆分分成更小的任务执行，
 *           并将小任务加到线程队列中，然后再从一个随机线程的队列中偷一个并把它放在自己的队列中。
 *            相对于一般的线程池实现，fork/join框架的优势体现在对其中包含的任务的处理方式上.在一般的线程池中，
 *           如果一个线程正在执行的任务由于某些原因无法继续运行，那么该线程会处于等待状态。而在fork/join框架实现中，
            如果某个子问题由于等待另外一个子问题的完成而无法继续运行。那么处理该子问题的线程会主动寻找其他尚未运行的子问题来执行.
            这种方式减少了线程的等待时间，提高了性能
 * @author YeJian (jian.ye@downjoy.com)
 * @Description:
 * @date 2018/7/27 14:11
 */
public class ForkJoinTest {

    public static void main(String[] args) {
        //需要该pool的支持才行
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinSumCalculate(0,100000000L);

        Long sum =pool.invoke(task);
        System.out.println(sum);

    }
}

/**
 *  Fork/Join RecursiveTask带有返回值的
 *           RecursiveAction没有返回值
 */
class ForkJoinSumCalculate extends RecursiveTask<Long>{

    private static final long serialVersionUID = 2935087628674408191L;

    private long start;

    private long end;
    //临界值
    private static final long THURSHOLD =10000L ;

    public ForkJoinSumCalculate(long start,long end){
        this.start= start;
        this.end  = end;

    }
    @Override
    protected Long compute() {
        long length  = end - start;
        //如果到了临界值 就不再拆分了 直接返回改任务的总和
        if(length<=THURSHOLD){
            long sum = 0;
            for (long i = start;i<=end;i++){
                sum+=i;
            }
            return sum;
        }else{ //继续拆分
            long middle = (end + start)/2;
            ForkJoinSumCalculate left = new ForkJoinSumCalculate(start,middle);
            left.fork();//进行拆分压入线程队列
            ForkJoinSumCalculate right = new ForkJoinSumCalculate(middle+1,end);
            right.fork();//进行拆分压入线程队列
            return left.join()+right.join();
        }
    }
}
