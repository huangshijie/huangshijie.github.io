进程
操作系统调度的最小单位

线程
进程中，调度的最小单位

线程状态
新建（New）：创建后尚未启动的线程
运行（Runable）：包括了操作系统线程中的Running和Ready，处于此状态的线程可能正在执行或者等待cpu分配时间片
无限期等待（Waiting）：等待被其他线程显式唤醒，执行wait或者join方法或者LockSupport的park方法
限期等待（Timed Waiting）：一定时间后会由系统自动唤醒
阻塞（Blocked）：等待获取到一个排它锁
结束（Terminated）：线程执行结束

线程生命周期
创建-就绪-运行-阻塞-死亡

# Thread常用方法

**原子操作**
i++是原子的并不对

Thread.yield()
向调度程序提示当前线程愿意放弃当前对处理器的使用。调度程序可以忽略此提示。yield是一种启发式的尝试，用于改善线程之间的相对进程，否则会过度使用CPU。它的使用应该与详细的分析和基准测试相结合，以确保它实际上具有预期的效果。很少适合使用这种方法。它可能对调试或测试有用，因为在调试或测试中，它可能有助于根据竞争条件重现错误。在设计并发控制结构时也可能有用，比如{java.util.concurrent.locks}包中的那些。

Thread.join()
t.join()方法阻塞调用此方法的线程(calling thread)，直到线程t完成，此线程再继续；通常用于在main()主线程内，等待其它线程完成再结束main()主线程。
main()主线程中有很多其他线程，

Thread.start()

Thread.run()

Thread.currentThread()

Thread.interrupt()
终止当前线程
- 抛出异常终止线程
- return终止

```
Thread类中，run和start的区别？

start方法就是调用的线程是并行的，会启动一个新线程，新线程会执行相应的run()方法。start()不能被重复调用。

run方法则实际上是串行的，失去多线程的特性。从源码角度看，run方法在Thread类中就是直接调用run方法。一个普通的成员方法。
可以被重复调用。单独调用run()的话，会在当前线程中执行run()，而并不会启动新线程！
```
run方法源码如下
```
@Override
public void run() {
    if (target != null) {
        target.run();
    }
}
```
start方法源码如下
```
public synchronized void start() {
    /**
     * This method is not invoked for the main method thread or "system"
     * group threads created/set up by the VM. Any new functionality added
     * to this method in the future may have to also be added to the VM.
     *
     * A zero status value corresponds to state "NEW".
     */
    if (threadStatus != 0)
        throw new IllegalThreadStateException();

    /* Notify the group that this thread is about to be started
     * so that it can be added to the group's list of threads
     * and the group's unstarted count can be decremented. */
    group.add(this);

    boolean started = false;
    try {
        start0();
        started = true;
    } finally {
        try {
            if (!started) {
                group.threadStartFailed(this);
            }
        } catch (Throwable ignore) {
            /* do nothing. If start0 threw a Throwable then
              it will be passed up the call stack */
        }
    }
}
```
