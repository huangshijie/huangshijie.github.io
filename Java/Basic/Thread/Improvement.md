# 快问快答

1. 什么是多线程中的上下文切换？

多线程会共同使用一组计算机上的CPU，而线程数大于给程序分配的CPU数量时，为了让各个线程都有执行的机会，就需要轮转使用CPU。不同的线程切换使用CPU发生的切换数据等就是上下文切换。

2. 什么是死锁，产生死锁的必要条件？

死锁是指两个或两个以上的进程（或线程）在执行过程中，因争夺资源而造成的一种互相等待的现象，若无外力作用，它们都将无法推进下去。

产生死锁的必要条件：
- 互斥条件：所谓互斥就是进程在某一时间内独占资源。该资源每次只能被一个进程占用。
- 请求与保持条件：一个进程因请求资源而阻塞时，对已获得的资源保持不放。
- 不剥夺条件: 进程已获得资源，在末使用完之前，不能强行剥夺。
- 循环等待条件:若干进程之间形成一种头尾相接的循环等待资源关系。T1占有R0，然后又申请R1...Tn占有Rn-1又申请R0，形成环路。

这是一个经典的锁顺序死锁
```java
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
```

如何避免出现死锁的情况？
- 开放调用
- 各个线程使用相同的顺序去调用资源
- 让持有资源的时间有限

3. 什么是活锁？

任务或者执行者没有被阻塞，由于某些条件没有满足，导致一直重复尝试，失败，尝试，失败。并未产生线程阻塞，但是由于某种问题的存在，导致无法继续执行的情况。例如：

- 消息重试。当某个消息处理失败的时候，一直重试，但重试由于某种原因，比如消息格式不对，导致解析失败，而它又被重试。解决办法，这种时候一般是将不可修复的错误不要重试，或者是重试次数限定
- 相互协作的线程彼此响应从而修改自己状态，导致无法执行下去。比如两个很有礼貌的人在同一条路上相遇，彼此给对方让路，但是又在同一条路上遇到了。互相之间反复的避让下去。解决办法，这种时候可以选择一个随机退让，使得具备一定的随机性

活锁和死锁的区别：处于活锁的实体是在不断的改变状态，所谓的“活”， 而处于死锁的实体表现为等待；活锁有可能自行解开，死锁则不能。

4. 什么是饥饿？

线程饥饿是另一种活跃性问题，也可以使程序无法执行下去。如果一个线程因为处理器时间全部被其他线程抢走而得不到处理器运行时间，这种状态被称之为饥饿，一般是由高优先级线程吞噬所有的低优先级线程的处理器时间引起的。这里饿的是处理器运行时间，自身程序得不到运行。

Java中导致饥饿的原因：
- 高优先级线程吞噬所有的低优先级线程的CPU时间。
- 线程被永久堵塞在一个等待进入同步块的状态，因为其他线程总是能在它之前持续地对该同步块进行访问。
- 线程在等待一个本身也处于永久等待完成的对象(比如调用这个对象的wait方法)，因为其他线程总是被持续地获得唤醒。

解决饥饿的方案被称之为“公平性” – 即所有线程均能公平地获得运行机会。

在Java中实现公平性方案，需要:
- 使用锁，而不是同步块。
- 公平锁。
- 注意性能方面。