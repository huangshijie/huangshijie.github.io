package com.huang.MultiThread;

/**
 * @Author : I325805
 * @Description:
 */
public class SimpleThreadLogic implements Runnable{

    @Override
    public void run() {
        while(true) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public static void main(String[] args) {
        SimpleThreadLogic task = new SimpleThreadLogic();
        for (int i = 0; i< 10; i++) {
            Thread thread = new Thread(task);
            thread.start();
        }
    }
}
