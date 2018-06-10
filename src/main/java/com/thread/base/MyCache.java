package com.thread.base;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 自定义的缓存
 *
 * @author YeJian
 * @create 2018-06-03 17:50
 **/
public class MyCache {


    public static void main(String[] args) {

    }
    private Map<String ,String > map = new HashMap();
    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    public Object getData(String id){
        rwl.readLock();
        String  value =null;
        try{
            value = map.get(id);
            if(value==null){
                rwl.readLock().unlock();
                rwl.writeLock().lock();
                try{
                    if(value ==null){
                        value = "aaaa";//实际是去查询数据库
                    }
                }finally {
                    rwl.writeLock().unlock();
                }
                rwl.readLock().lock();
            }
            return value;
        }finally {
            rwl.readLock().unlock();
        }
    }
}
