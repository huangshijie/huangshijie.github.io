# Synchronized

## Overview

synchronized，Java 关键字，同步锁，可以用于修饰实例方法、静态方法、代码块。主要作用有三个：

1. 确保线程互斥的访问同步代码
2. 保证共享变量的修改能够及时可见
3. 有效解决重排序问题

### Synchronized 怎么用

- 修饰实例方法(实例方法就是非 static 方法，是实例中的方法，不属于整个类)，所以该同步锁作用于当前实例，进入同步代码前需要获得当前实例的锁。
- 修饰静态方法(类方法，用 static 修饰的方法，属于整个类不属于某个对象)，作用于当前类对象加锁，进入同步代码前要获得当前对象的锁。
- 修饰代码块，指定加锁对象，对给定对象加锁，进入同步代码块前要获得指定对象的锁。就和常用的

  ```java
    synchronized(object) {
        ...
    }
  ```

### Synchronized 主要作用

- 确保线程互斥的访问同步代码。
- 保证共享变量的修改能够及时**可见**。
- 有效解决指令重排的问题。

## Synchronized 是基于什么原理

### 举例

通过如下代码演示：

```java
public class App {

    private synchronized void getHello() {
        System.out.println("say hello");
    }


    private static synchronized void getStaticHello() {
        System.out.println("say static hello");
    }

    public static void main( String[] args ) {

        synchronized(App.class) {
            System.out.println("Hello App");
        }

        App.getStaticHello();

        App app = new App();

        app.getHello();
    }
}

```

使用命令`javap -c App.class`对 class 进行反编译。结果如下：

```java
Compiled from "App.java"
public class org.huang.Util.App {
  public org.huang.Util.App();
    Code:
       0: aload_0
       1: invokespecial #8                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: ldc           #1                  // class org/huang/Util/App
       2: dup
       3: astore_1
       4: monitorenter
       5: getstatic     #15                 // Field java/lang/System.out:Ljava/io/PrintStream;
       8: ldc           #34                 // String Hello App
      10: invokevirtual #23                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      13: aload_1
      14: monitorexit
      15: goto          21
      18: aload_1
      19: monitorexit
      20: athrow
      21: invokestatic  #36                 // Method getStaticHello:()V
      24: new           #1                  // class org/huang/Util/App
      27: dup
      28: invokespecial #38                 // Method "<init>":()V
      31: astore_1
      32: aload_1
      33: invokespecial #39                 // Method getHello:()V
      36: return
    Exception table:
       from    to  target type
           5    15    18   any
          18    20    18   any
}
```

可以看到反编译的字节码中包含有'**monitorenter**'和'**monitorexit**'，每个对象都有一个监视器锁(monitor)。当 monitor 被占用，则处于锁定状态，线程执行 monitorenter 指令尝试获取 monitor 的所有权：

1. 如果 monitor 的进入数为 0，则该线程进入 monitor，然后进入数设置为 1，该线程即为 monitor 的所有者；
2. 如果线程

### 偏向锁

### 自旋锁

### 轻量级锁

### 重量级锁

### 锁的优缺点对比
