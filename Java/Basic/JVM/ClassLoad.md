JVM instance

# 基础
## 介绍
通俗的说，有几个main函数就对应几个Java虚拟机。

JVM, Java virtual machine, 既然是machine，就说明和普通的计算机一样，一套字节码指令集，一组寄存器，一个栈，一个垃圾回收堆和一个存储方法域。

从Linux操作系统角度看，JVM是一个普通的应用程序进程，那么JVM具有一般操作系统进程的特点，一般的进程的内存结构和JVM进程的内存结构是类似的，

Linux把一个程序在内存中所占的空间分为：方法区、数据区、堆区、栈区，
对应地，JVM在内存中也有JVM方法区、JVM数据区、堆区和栈区。

代码区 中存放应用程序的机器代码，运行过程中代码不能被修改，具有只读和固定大小的特点。

数据区 中存放了应用程序中的全局数据，静态数据和一些常量字符串等，其大小也是固定的。

堆 是运行时程序动态申请的空间，属于程序运行时直接申请、释放的内存资源。

栈区 用来存放函数的传入参数、临时变量，以及返回地址等数据。未使用区是分配新内存空间的预备区域。

所以需要了解计算机原理。

JVM启动后，对操作系统来说，JVM是操作系统中的一个进程。这个进程的结构包括：类加载器子系统、运行时数据区、执行引擎和本地方法接口。

运行时数据区是JVM从操作系统申请来的堆空间和操作系统给JVM分配的栈空间的总称。JVM为了运行Java程序，又进一步对运行时数据区进行了划分，划分为Java方法区、Java堆、Java栈、PC寄存器、本地方法栈等，这里JVM从操作系统申请来的堆空间被划分为方法区和Java堆，操作系统给JVM分配的栈空间构成Java栈。

虚拟机把描述类的数据从class文件加载到内存，并对数据进行校验、转换解析和初始化，最终形成可以被虚拟机直接使用的Java类型，这就是虚拟机加载机制。

Java在发布初期，就分为Java语言规范和Java虚拟机规范发布，将语言和虚拟机分开，保证了只要能将代码编译成Java虚拟机能够识别的class二进制流，那么不管什么语言写的代码都能在Java虚拟机上运行。


## 生命周期

JVM有两种线程，**守护线程**和**非守护线程**。例如main就是一个非守护线程，GC就是一个守护线程。(在多线程中要看一看)

java的虚拟机中，只要有任何非守护线程还没有结束，java虚拟机的实例都不会退出，所以即使main函数这个非守护线程退出，但是由于在main函数中启动的匿名线程也是非守护线程，它还没有结束，所以JVM没办法退出。

Java虚拟机的生命周期，当一个java应用main函数启动时虚拟机也同时被启动，而只有当在虚拟机实例中的所有非守护进程都结束时，java虚拟机实例才结束生命。

![JVM Struture](https://github.com/huangshijie/ImgRep/blob/master/JVM.jpg)


# Java虚拟机类加载机制

![Class Life Circle](https://github.com/huangshijie/ImgRep/blob/master/class_life_circle.png)

类被加载到虚拟机内存中，从开始到卸载为止，一共经历了七个阶段，

## 加载

## 验证

## 准备

## 解析

## 初始化




# Classload 类加载器

主要的类加载器有两种系统级别的和用户自定义的。

系统级别的有，bootstrao classload和extend

将A.class装载到jvm的方法区，方法区中的这个字节文件会被虚拟机拿来new A字节码()，然后在堆内存生成了一个A字节码的对象，然后A字节码这个内存文件有两个引用一个指向A的class对象，一个指向加载自己的classLoader

1. 类信息：修饰符(public final)
是类还是接口(class,interface)
类的全限定名(Test/ClassStruct.class)
直接父类的全限定名(java/lang/Object.class)
直接父接口的权限定名数组(java/io/Serializable)
也就是 public final class ClassStruct extends Object implements Serializable这段描述的信息提取

2. 字段信息：修饰符(pirvate)
字段类型(java/lang/String.class)
字段名(name)
也就是类似private String name;这段描述信息的提取

3. 方法信息:修饰符(public static final)
方法返回值(java/lang/String.class)
方法名(getStatic_str)
参数需要用到的局部变量的大小还有操作数栈大小(操作数栈我们后面会讲)
方法体的字节码(就是花括号里的内容)
异常表(throws Exception)
也就是对方法public static final String getStatic_str ()throws Exception的字节码的提取
     
4. 常量池:
> 4.1 直接常量：
>> 1.1 CONSTANT_INGETER_INFO整型直接常量池
>> public final int CONST_INT=0;
>> 1.2 CONSTANT_String_info字符串直接常量池   
>> public final String CONST_STR="CONST_STR";
>> 1.3 CONSTANT_DOUBLE_INFO浮点型直接常量池
>> 等等各种基本数据类型基础常量池

> 4.2. 方法名、方法描述符、类名、字段名，字段描述符的符号引用
>> 也就是所以编译器能够被确定，能够被快速查找的内容都存放在这里，它像数组一样通过索引访问，就是专门用来做查找的。
>> 编译时就能确定数值的常量类型都会复制它的所有常量到自己的常量池中，或者嵌入到它的字节码流中。作为常量池或者字节码流的一部分，编译时常量保存在方法区中，就和一般的类变量一样。但是当一般的类变量作为他们的类型的一部分数据而保存的时候，编译时常量作为使用它们的类型的一部分而保存

5. 类变量：
就是静态字段( public static String static_str="static_str";)
虚拟机在使用某个类之前，必须在方法区为这些类变量分配空间。

6. 一个到classLoader的引用，通过this.getClass().getClassLoader()来取得为什么要先经过class呢？思考一下，然后看第七点的解释，再回来思考

7. 一个到class对象的引用，这个对象存储了所有这个字节码内存块的相关信息。所以你能够看到的区域，比如：类信息，你可以通过this.getClass().getName()取得

8. 方法表

# Runtime data area 运行时数据区

## Java方法区或Permanent区(线程共享)

## Java堆 (线程共享)

## Java栈

## 本地方法栈

## PC 寄存器

# Execution Engine 执行引擎

# Native Interface 本地方法接口

首先，当一个程序启动之前，它的class会被类装载器装入方法区(不好听，其实这个区我喜欢叫做Permanent区)，执行引擎读取方法区的字节码自适应解析，边解析就边运行（其中一种方式），然后pc寄存器指向了main函数所在位置，虚拟机开始为main函数在java栈中预留一个栈帧（每个方法都对应一个栈帧），然后开始跑main函数，main函数里的代码被执行引擎映射成本地操作系统里相应的实现，然后调用本地方法接口，本地方法运行的时候，操纵系统会为本地方法分配本地方法栈，用来储存一些临时变量，然后运行本地方法，调用操作系统API等等。 


https://www.cnblogs.com/xuningchuanblogs/p/7688332.html
