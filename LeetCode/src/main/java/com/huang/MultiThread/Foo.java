package com.huang.MultiThread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Foo {
    private static Lock lock = new ReentrantLock();// 通过JDK5中的Lock锁来保证线程的访问的互斥

    public static void first() {
        System.out.println("first");
    }

    public static void second() {
        System.out.println("second");
    }

    public static void third() {
        System.out.println("third");
    }

    static class ThreadA extends Thread{
        @Override
        public void run() {

            while (true){
                lock.lock();
                first();
                lock.unlock();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }
    }

    static class ThreadB extends Thread{
        @Override
        public void run() {
            while (true){
                lock.lock();
                second();
                lock.unlock();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class ThreadC extends Thread{
        @Override
        public void run() {
            while (true){
                lock.lock();
                third();
                lock.unlock();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) {

        ThreadA a = new ThreadA();
        ThreadB b = new ThreadB();
        ThreadC c = new ThreadC();

        a.start();
        b.start();
        c.start();
    }
}
