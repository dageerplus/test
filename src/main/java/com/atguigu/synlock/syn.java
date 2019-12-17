package com.atguigu.synlock;

import java.util.concurrent.locks.Lock;

class Ticket{
    int i=0;

    public synchronized void in() throws InterruptedException {
        if(i!=0) {
            this.wait();
        }
            ++i;
            System.out.println("this is "+Thread.currentThread().getName()+i);
            this.notifyAll();


    }
    public synchronized void out() throws InterruptedException {
        if(i==0) {
            this.wait();
        }
            --i;
            System.out.println("this is "+Thread.currentThread().getName()+i);
            this.notifyAll();

    }
}

public class syn {
    public static void main(String[] args) {
        Ticket ticket=new Ticket();
        new Thread(()->{for(int i=0;i<10;i++) {
            try {
                ticket.in();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            },"a").start();

        new Thread(()->{for(int i=0;i<10;i++) {
            try {
                ticket.out();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        },"b").start();
    }
}
