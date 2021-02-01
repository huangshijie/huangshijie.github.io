package com.huang.MultiThread.Interrupted;

public class InterruptedThread extends Thread{
    @Override
    public void run() {
        // isInterrupted
        while(true){
            System.out.println("thread is running");
        }
        // System.out.println("thread is interrupted.");
    }

    public static void main(String[] args) throws InterruptedException {
        InterruptedThread thread = new InterruptedThread();
        thread.start();
        Thread.sleep(3000);
        thread.interrupt();
    }
}
