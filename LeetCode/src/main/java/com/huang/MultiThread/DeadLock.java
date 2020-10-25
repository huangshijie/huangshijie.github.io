package com.huang.MultiThread;

/**
 * @Author : I325805
 * @Description:
 */
public class DeadLock {
    public static final String source1 = "source1";
    public static final String source2 = "source2";

    public static void main(String[] args) {
        Thread tA = new Thread(new LockA());
        Thread tB = new Thread(new LockB());
        tA.start();
        tB.start();
    }

    static class LockA implements Runnable {
        @Override
        public void run() {
            while(true){
                synchronized (DeadLock.source1) {
                    try {
                        System.out.println("LockA lock source1");
                        Thread.sleep(3000);
                        synchronized (DeadLock.source2) {
                            System.out.println("LockA lock source2");
                        }
                    }catch (InterruptedException e){

                    }
                }
            }
        }
    }
    static class LockB implements Runnable {
        @Override
        public void run() {
            while(true){
                synchronized (DeadLock.source2) {
                    try {
                        System.out.println("LockB lock source2");
                        Thread.sleep(3000);
                        synchronized (DeadLock.source1) {
                            System.out.println("LockB lock source1");
                        }
                    }catch (InterruptedException e){

                    }
                }
            }
        }
    }
}
