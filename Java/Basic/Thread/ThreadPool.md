# 线程池(Thread Pool)

## 基础

什么是线程池，线程池就是拥有一定数量空闲线程的集合。当我们将任务交个线程池，则不需要为每个任务单独启线程。

使用线程池的好处，主要是有：

1. 降低资源的消耗。
2. 提高响应速度。
3. 提高线程的可管理性。

线程池的种类：

1. newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
2. newFixedThreadPool创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
3. newScheduledThreadPool创建一个定长线程池，支持定时及周期性任务执行。
4. newSingleThreadExecutor创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序（FIFO， LIFO， 优先级）执行。

## java.util.concurrent

### AtomicInteger

下面的代码片段，使用多个线程同时调用increase方法对race进行自增操作，并不能得到预期值，例如20个线程分别调用100次increase方法，最后得到的race值小于等于2000。这是因为volatile只能保证race的可见性，不能保证increase方法的原子性。
race++，是一个复合操作，具体分解为：

1. 读取race值
2. race加1
3. 将race写入主存中

在多线程环境下，有可能线程A将race读取到本地内存中，此时其他线程可能已经将race增大了很多，线程A依然对过期的本地缓存race进行自加，重新写到主存中，最终导致了race的结果不合预期。

```java
public static volatile int race = 0;
public static void increase() {
  race++;
}

```

改进increase方法，在其上加锁，使用synchronized修饰increase方法。但是这样的性能稍微差点。

```java
public static synchronized void increase() {
  race++
}
```

更好的方法是使用并发包中的AtomicInteger，AtomicInteger是Integer的原子操作类。

```java
public static AtomicInteger race = new AtomicInteger(0);
public static void increase() {
  race.getAndIncrement();
}
```

```java
public final int getAndIncrement() {
  return unsafe.getAndAddInt(this, valueOffset, 1);
}

```

```java
public final int getAndAddInt(Object o, long offset, int delta) {
  int v;
  do {
    v = getIntVolatile(o, offest);
  } while(!compareAndSwapInt(p, offest, v, v + delta));

  return v;
}

public native int getIntVolatile(Object obj, long offset);

public native boolean compareAndSwapInt(Object obj, long offset, int expect, int update);
```

compareAndSwapInt（var1, var2, var5, var5 + var4）其实换成compareAndSwapInt（obj, offset, expect, update）比较清楚，意思就是如果obj内的value和expect相等，就证明没有其他线程改变过这个变量，那么就更新它为update，如果这一步的CAS没有成功，那就采用自旋的方式继续进行CAS操作，取出乍一看这也是两个步骤了啊，其实在JNI里是借助于一个CPU指令完成的。所以还是原子操作。

这里的**compareAndSwapInt**就使用了CAS算法，即Compare And Swap(比较并交换)。将内存值与预期值进行比较，如果相等才将心智替换到内存中，并返回true表示操作成功，false表示操作失败。

CAS虽然很高效的解决了原子操作问题，但是CAS仍然存在三大问题。

- 循环时间长开销很大。
- 只能保证一个共享变量的原子操作。
- ABA问题。如果内存地址V初次读取的值是A，并且在准备赋值的时候检查到它的值仍然为A，那我们就能说它的值没有被其他线程改变过了吗？如果在这段期间它的值曾经被改成了B，后来又被改回为A，那CAS操作就会误认为它从来没有被改变过。这个漏洞称为CAS操作的“ABA”问题。Java并发包为了解决这个问题，提供了一个带有标记的原子引用类“AtomicStampedReference”，它可以通过**控制变量值的版本**来保证CAS的正确性。因此，在使用CAS前要考虑清楚“ABA”问题是否会影响程序并发的正确性，如果需要解决ABA问题，改用传统的互斥同步可能会比原子类更高效。

比如：

现有一个用单向链表实现的堆栈，栈顶为A，这时线程T1已经知道A.next为B，然后希望用CAS将栈顶替换为B：head.compareAndSet(A,B);在T1执行上面这条指令之前，线程T2介入，将A、B出栈，再pushD、C、A。而对象B此时处于游离状态：此时轮到线程T1执行CAS操作，检测发现栈顶仍为A，所以CAS成功，栈顶变为B，但实际上B.next为null，其中堆栈中只有B一个元素，C和D组成的链表不再存在于堆栈中，平白无故就把C、D丢掉了。

各种乐观锁的实现中通常都会用版本戳version来对记录或对象标记，避免并发操作带来的问题。

[Lock Free BST](https://github.com/arunmoezhi/LockFreeBST)

### unsafe
