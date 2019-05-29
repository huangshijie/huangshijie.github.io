**static, final, transient关键字的作用**
```
static
 修饰变量、修饰方法-> 类加载的顺序, 如果类之间想共享数据为了保证数据不被修改建议怎么做
 静态块 -> 
 静态内部类 -> 如何实例化内部类，为什么要使用内部类，面向对象的特性有哪些
 静态导包 -> 优缺点，过度地使用静态导入会在一定程度上降低代码的可读性。
```

Private 是否安全？反射照样可以访问private的

```
final
 final、finally与finalize的区别 1
```

```
transient
 接着问，序列化，反序列化，
```

---
**Given a sorted linked list, delete all duplicates such that each element appear only once.**
接下去问集合相关内容

> **ArrayList的大小是如何自动增加的**
> ```
> private void grow(int minCapacity) {
>   // overflow-conscious code
>   int oldCapacity = elementData.length;
>   int newCapacity = oldCapacity + (oldCapacity >> 1);
>   if (newCapacity - minCapacity < 0)
>     newCapacity = minCapacity;
>   if (newCapacity - MAX_ARRAY_SIZE > 0)
>     newCapacity = hugeCapacity(minCapacity);
>   // minCapacity is usually close to size, so this is a win:
>   elementData = Arrays.copyOf(elementData, newCapacity);
> }
> ```
> 
>  - \>> 和\>>> 的区别
> HashSet与HashMap怎么判断集合元素重复？ -> hashcode 和 equals
> 浅拷贝和深拷贝

---
**线程之间如何共享数据**
> 什么是线程安全？
> 四个线程统计数据，第五个线程进行汇总计算，大致说下思路
>> 1. join
>> 2. concurrent, java.util.concurrent.CountDownLatch
CountDownLatch是通过一个计数器来实现的，计数器的初始值为线程的数量。每当一个线程完成了自己的任务后，计数器的值就会减1。当计数器值到达0时，它表示所有的线程已经完成了任务，然后在闭锁上等待的线程就可以恢复执行任务。

---
**说出一种设计模式，并且简单写下它的实现**

---
**SQL优化**
> 数据库索引
> 数据库索引的数据结构基础，B+tree

---
Spring reference
Spring Bean的生命周期
Git
Cloud Foundry Application Development
Kafka, Cassandra, Spark

zookeeper 
kafka 提供了两套 consumer API：

1. The high-level Consumer API
2. The SimpleConsumer API

Linux管道命令