package juc.queue;

import model.Student;
import model.Times;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * 学生交卷
 *
 * @author YeJian
 * @create 2018-05-13 22:49
 **/
public class StudentDelayesTestQueue {

    public static void main(String[] args)  throws InterruptedException {
        DelayQueue<Student> queue = new DelayQueue<>();
        queue.add(new Student("范冰冰"));
        queue.add(new Student("成  龙"));
        queue.add(new Student("李一桐"));
        queue.add(new Student("宋小宝"));
        queue.add(new Student("吴  京"));
        queue.add(new Student("绿巨人"));
        queue.add(new Student("洪金宝"));
        queue.add(new Student("李云龙"));
        queue.add(new Student("钢铁侠"));
        queue.add(new Student("刘德华"));
        queue.add(new Student("戴安娜"));
        queue.add(new Student("submit", Times.SUBMIT_TIME.getValue(), TimeUnit.SECONDS));
        while (true) {
            Student s = queue.take(); // 必要时进行阻塞等待
            if (s.getName().equals("submit")) {
                System.out.println("时间已到，全部交卷！");
                // 利用Java8 Stream使尚未交卷学生交卷
                queue.parallelStream()
                        .filter(v -> v.getExpire() >= s.getExpire())
                        .map(Student::submit)
                        .forEach(System.out::println);
                System.exit(0);
            }
            System.out.println(s);
        }
    }
}

