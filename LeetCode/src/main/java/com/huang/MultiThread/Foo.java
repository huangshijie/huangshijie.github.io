package com.huang.MultiThread;

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
