package com.huang.MultiThread;

/**
 * @Author : I325805
 * @Description:
 */
public class WhileTrueTest {
    public void run(int num) throws InterruptedException {
        while(true) {
            System.out.println(num);
            Thread.sleep(6000);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for(int i = 0; i<10; i ++) {
            WhileTrueTest test = new WhileTrueTest();
            test.run(i);
        }
    }
}
