package com.atguigu.synlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Reentrant{
    //ReentrantLock  Reentrant:可重入
    int flag=1;
    Lock lock=new ReentrantLock();
    Condition c1=lock.newCondition();
    Condition c2=lock.newCondition();
    Condition c3=lock.newCondition();

        public void prin5() {
            lock.lock();
            try {
                while (flag != 1) {
                    c1.await();
                }
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName()+"AAAA");
                }
                flag = 2;
                c2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
        public void prin10(){
            lock.lock();
            try {
                while (flag !=2) {
                    c2.await();
                }
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName()+"BBBBBB");
                }
               flag=3;
                c3.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        }
        public void prin15(){
            lock.lock();
            try {
                while (flag !=3) {
                    c3.await();
                }
                for (int i = 0; i < 15; i++) {
                    System.out.println(Thread.currentThread().getName()+"CCCCCC");
                }
               flag=1;
                c1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        }


}

public class ThreadOrderAccess {
    public static void main(String[] args) {
        Reentrant shareResource = new Reentrant();

        new Thread(()->{
            for(int i=0;i<10;i++) {
                shareResource.prin5();
            }

        },"a:").start();

        new Thread(()->{
            for(int i=0;i<10;i++) {
                shareResource.prin10();
            }

        },"b:").start();

        new Thread(()->{
            for(int i=0;i<10;i++) {
                shareResource.prin15();
            }

        },"c:").start();
    }


    }

