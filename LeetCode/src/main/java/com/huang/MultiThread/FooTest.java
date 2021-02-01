package com.huang.MultiThread;

import org.junit.Test;

/**
 * @author: I325805
 * @description:
 */
public class FooTest {

    @Test
    public void fooTest() throws InterruptedException {
        Foo foo = new Foo();

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    foo.first();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    foo.second();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    foo.third();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadB.start();
        Thread.sleep(3000);
        threadA.start();

        threadC.start();
    }
}
