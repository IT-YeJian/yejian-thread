package com.thread.base;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Callable和Future带有返回值的线程
 *Future取得的结果类型和Callable
 * 返回的结果类型必须一致 这是通过反省来实现的
 *
 * Callable要采用ExecutorService的
 * submit方法提交
 * 返回的future对象可以取消任务
 * CompletionService用于提交
 * 一组Callable任务 其take方法返回已完成的
 * 一个Callable任务对应的Future对象
 *
 *
 * @author YeJian
 * @create 2018-05-24 22:47
 **/
public class CallableAndFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();


        Future<String> result = threadPool.submit(
                new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000);
                return "hello";
            }
        });

        System.out.println("等待结果");
        System.out.println("拿到结果:"+
                result.get());

        ExecutorService threadPool2 =
                Executors.newFixedThreadPool(10);
        CompletionService<Integer>
                completionService =
                new ExecutorCompletionService(threadPool2);

        for (int i = 1;i<=10;i++){
            final int seq = i;
            completionService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    Thread.sleep(new Random().nextInt(5000));
                    return seq;
                }
            });
        }

        for (int i=1;i<=10;i++){
            Future<Integer> take = completionService.take();
            Integer integer = take.get();
            System.out.println(integer);
        }

    }



}
