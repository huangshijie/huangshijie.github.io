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
import java.util.concurrent.Semaphore;

/**
 * @author: I325805
 * @description:
 */
public class Foo {

    Semaphore semaphore12, semaphore23;

    public Foo() {
        semaphore12 = new Semaphore(0);
        semaphore23 = new Semaphore(0);
    }

    public void first() throws InterruptedException{
        System.out.println("first");
        semaphore12.release();
    }

    public void second() throws InterruptedException {
        semaphore12.acquire();
        System.out.println("second");
        semaphore23.release();
    }

    public void third() throws InterruptedException {
        semaphore23.acquire();
        System.out.println("third");
    }
}
