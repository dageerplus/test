package com.atguigu.synlock;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Notsafe {
    public static void main(String[] args) {
        List<String> set =new CopyOnWriteArrayList(new ArrayList());//new Vector<String>();//Collections.synchronizedSet(new HashSet<>());
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 6));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}
