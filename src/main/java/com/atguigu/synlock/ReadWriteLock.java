package com.atguigu.synlock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Cache {
    private volatile Map<String, String> map = new HashMap<>();
   // private Lock lock = new ReentrantLock();
    ReentrantReadWriteLock rwl=new ReentrantReadWriteLock();

    public void put(String key, String value) {
        rwl.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 写入开始");
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入结束");
        }finally {


            rwl.writeLock().unlock();
        }
    }

    public void get(String key) {
        rwl.readLock().lock();
        try {

            System.out.println(Thread.currentThread().getName() + "\t 读入开始");
            map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读入结束");
        }finally {
            rwl.readLock().unlock();
        }
    }
}

public class ReadWriteLock {
    public static void main(String[] args) {
        Cache ca = new Cache();

        for (int i = 0; i < 10; i++) {
            final int tempI = i;
            new Thread(() -> {
                ca.put(tempI + "", tempI + "");
            }, String.valueOf(i)).start();
        }
        for (int i = 0; i < 10; i++) {
            final int tempI = i;
            new Thread(() -> {
                ca.get(tempI + "");
            }, String.valueOf(i)).start();
        }
    }
}
