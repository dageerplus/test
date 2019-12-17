package com.atguigu.synlock;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class NotSageMap {
    public static void main(String[] args) {
        Map<String,String>  map=new ConcurrentHashMap<>();//ConcurrentHaspMap  == CopyOnWriteArrayList

        for(int i=1;i<10;i++){
            new Thread(()->{
             map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,6));
                System.out.println(map);

            },String.valueOf(i)).start();
        }
    }
}
