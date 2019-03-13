# Java基础

- hashCode与equals的区别联系

```
为了满足两个对象使用equals()返回相等的情况下，hashcode也必须相等。
hashcode不等的话，也必然不equals。
```

- 自动拆装箱

## Collection 集合
- 如何判断Set不重复
```
(e==null ? e2==null : e.equals(e2))
```
- concurrenthashmap
```
分块，大数组套小数组，小数组里套链表

```
- HashMap实现原理
```
数组里的元素是单链表
```
- Treemap实现
- equals为什么要重写hashcode方法
- List和set区别    java 常用集合list与Set,Map区别及适用场景总结
- linklist和arraylist区别,应用场景     Vector,ArrayList,LinkedList的区别与适用场景

## JVM
- 双亲委派
```
一个类加载器在加载类的时候，会将这个类交给自己父类尝试加载，然后递归上去，父类加载不了的再交还给子类加载。
```
- 类加载器有几种，分别做什么用的
```
bootstrap加载器
拓展类加载器
应用加载器
用户自定义加载器
```
- JVM加载过程
```
加载-连接-初始化
```
- 数组和链表在内存中表型形式
```
数组内存连续的，链表内存不连续
```
- 序列化有什么优缺点
- JVM垃圾回收
- jvm参数，gc参数，
- 内存泄漏如何检测

## 多线程
- 线程与进程的区别
```
进程是操作系统分配资源的最小单元，线程是操作系统调度的最小单元。一个程序至少有一个进程,一个进程至少有一个线程。
```

- 线程池，线程池有几种,干什么用。
- JAVA多线程实现
- JAVA线程和进程区别
- 线程有几种状态，怎么互相转化
- 启动线程方式
- 线程同步
- 并发操作怎么控制
- lock和synchronized
- JMM
```
主内存、每个线程的私有内存
```
- 在java中守护线程和本地线程区别？
```
java中的线程分为两种：守护线程（Daemon）和用户线程（User）。任何线程都可以设置为守护线程和用户线程，通过方法Thread.setDaemon(bool on)；true则把该线程设置为守护线程，反之则为用户线程。Thread.setDaemon()必须在Thread.start()之前调用，否则运行时会抛出异常。两者的区别： 唯一的区别是判断虚拟机(JVM)何时离开，Daemon是为其他线程提供服务，如果全部的User Thread已经撤离，Daemon 没有可服务的线程，JVM撤离。也可以理解为守护线程是JVM自动创建的线程（但不一定），用户线程是程序创建的线程；比如JVM的垃圾回收线程是一个守护线程，当所有线程已经撤离，不再产生垃圾，守护线程自然就没事可干了，当垃圾回收线程是Java虚拟机上仅剩的线程时，Java虚拟机会自动离开。扩展：Thread Dump打印出来的线程信息，含有daemon字样的线程即为守护进程，可能会有：服务守护进程、编译守护进程、windows下的监听Ctrl+break的守护进程、Finalizer守护进程、引用处理守护进程、GC守护进程。
```

# 框架
- 自己实现spring的bean注入，byname
- bean的生命周期
- byname bytype
- spring的mvc生命周期，底层实现
- Spring IOC Spring AOP是干嘛的
- Spring中autowire和resourse关键字的区别

# 设计模式
- 懒汉模式，变量上要不要加volatile
- 抽象工厂和工厂方法模式的区别
- 工厂模式的思想

# 计算机网路
- HTTP状态码
- http缓存
- DNS解析
- http 302 https具体数据传输流程
- HTTP/HTTPS过程
- OSI 7层模型
- TCP三次握手
- TCP连接断开过程，为什么要三次握手
- 三次握手，
- 超时重传
- TCP保证可靠性，
- 拥塞控制，
- 滑动窗口协议
- 由http升级为https需要哪些操作
- Session,Cookie区别
- Post和get区别
- sendRedirect, foward区别

# 算法类
- 排序算法
- 排序算法
- 排序
- 平衡二叉树
- 红黑树
- 介绍堆和栈
 
# 数据库类
- 聚簇索引和非聚簇索引(出现次数少)
- 事务的ACID
- 索引的实现机制（B+树），优缺点
- 数据库隔离级别
- 数据库四大特性，
- 事务隔离级别