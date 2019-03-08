# JVM 内存区域

## Java 数据类型

Java虚拟机可以操作的数据类型可以分为两类：**原始类型**(Primitive type, 也就是原声或者基本类型)和**引用类型**(reference type)

与之对应的，也存在**原始值**(primitive value)和**引用值**(reference value)

![Java Data Type](https://github.com/huangshijie/ImgRep/blob/master/Java%20Data%20Type.png)

## 基本数据类型

包括**数值类型**、**Boolean类型**和**returnAddress类型**

### 数值类型

其中又分为**整数类型**和**浮点类型**

#### 整数类型

- byte，8位有符号二进制补码整数，默认为0，取值范围-2^(8-1) ~ 2^(8-1)-1，包括-128和127
- short，16位有符号二进制补码整数，默认为0，取值范围-2^(16-1) ~ 2^(16-1)-1，包括-32768和32767
- int，32位有符号二进制补码整数，默认为0，取值范围-2^(32-1) ~ 2^(32-1)-1，包括
- long，64位有符号二进制补码整数，默认为0，取值范围-2^(64-1) ~ 2^(64-1)-1，包括
- char，16位无符号整数表示的Unicode码，以UTF-16编码，默认为null的码点('\u0000')，取值范围0 ~ 2^16-1

#### 浮点类型

- float，值为单精度浮点数集合中的元素，或是单精度拓展指数集合中的元素，默认为正数0
- double，值为双精度浮点数集合中的元素，或者是双精度拓展集合中的元素，默认为正数0

集合不仅包含可数的非零值，而且包括了5个特殊的数值：正数零、负数零、正无穷大、负无穷大和NaN(Not a Number, 计算中出现的错误情况,NaN与任何值都不相等包括自身)


### Boolean类型

true和false，默认为false

在编译以后用int类型的值来替代

### returnAddress类型

指向某个操作码(opcode)的指针，returnAddress类型的值指向一条虚拟机指令的操作码，在Java语言之中并不存在相应类型，也无法在程序运行期间修改

## 引用类型

### 引用类型

类类型，数组类型和接口类型

#### 强引用

#### 软引用

#### 弱引用

#### 虚引用

## 运行时数据区

Java虚拟机有若干种运行时数据区，有的是随虚拟机启动而创建的，随虚拟机退出而销毁。有的则是与线程一一对应，随线程的开始和结束而创建和销毁。

![Java运行时数据区](https://github.com/huangshijie/ImgRep/blob/master/JAVA_RUNTIME_DATA_AREA.png)

### PC寄存器

在微机原理中，寄存器是中央处理器内的组成部分。寄存器是有限存贮容量的高速存贮部件，它们可用来暂存指令、数据和地址，提到的寄存器有指令寄存器(IR)和程序计数器(PC)。

而每条Java虚拟机线程都有自己的PC寄存器，一条Java虚拟机线程只会执行一个方法的代码，现在被执行的方法称为该线程的当前方法(current method)。如果这个方法不是native，PC寄存器就保存了Java虚拟机正在执行的字节码指令的地址，如果是native的，那么PC寄存器的值就是undefined。

PC寄存器的容量至少能保存一个returnAddress类型的数据或者一个平台相关的本地指针的指针。

PC寄存器的内容总是指向下一条将被执行指令的地址，这里的地址可以是一个本地指针，也可以是在方法区中相对应于该方法起始指令的偏移量。

### Java 虚拟机栈

每条Java虚拟机线程都有自己私有的Java虚拟机栈(Java Virtual Machine stack)，这个栈与线程同时创建，用于存储栈帧(Frame)。
用于存储局部变量和一些尚未算好的结果。
另外在方法调用和返回中也扮演了重要角色。
Java虚拟机栈使用的内存不需要保证是连续的。

#### 栈帧

用来存储数据和部分过程结果的数据结构，同时也用来处理动态链接、方法返回值和异常分派。

栈帧随方法的调用而创建，随方法结束而销毁。(无论方法正常完成还是异常完成，都算方法结束)

每个栈帧都有自己的本地变量表、操作数栈和指向当前方法所属的类的运行时常量池的引用。

**栈帧是线程本地私有的，不可能在一个栈帧之中引用另一个线程的栈帧**

- 局部变量表

- 操作数栈

- 动态链接


### 堆

在Java虚拟机中，堆是可供各个线程共享的**运行时内存区域**，也是供所有类实例和数组对象分配内存的区域。

在虚拟机启动的时候就被创建。存储了自动内存管理系统所管理的各种对象，GC就是在这块上做的。

JDK8取消了永久代(Permgen)，而是将原有数据迁移至Java Heap或是[Metaspace](https://blogs.oracle.com/poonam/about-g1-garbage-collector,-permanent-generation-and-metaspace)

```
In JDK 8, classes metadata is now stored in the native heap and this space is called Metaspace. 
```
所以Metaspace应该和堆分开看，Metaspace用的是内存中的本地堆。

### 方法区

方法区也是可供各个线程共享的**运行时内存区域**

用于存储每个类的结构信息，例如，运行时常量池(runtime constant pool)，字段和方法数据、构造函数和普通方法的字节码内容，还包括一些在类、实例、接口初始化时用到的特殊方法。

### 运行时常量池

是class文件中每个类或者接口的常量池表的运行时表示形式，包含了若干种不同的常量，从编译期可知的数值字面量到必须在运行期解析后才能获得的方法或字段引用。
在加载类和接口到虚拟机后，就创建对应的运行时常量池。为每个类型都维护一个常量池。



### 本地方法栈

支持native方法的执行的栈就是本地方法栈。如果JVM支持本地方法栈，那么这个栈就在线程创建的时候按线程分配。


### String Pool 字符串池

综上所属，可以知道**字符串池**是在堆上创建的。

一个有趣的String.intern相关问题
https://stackoverflow.com/questions/49964417/stringintern-the-confuse-about-the-result-of-the-intern

中文版

https://www.cnblogs.com/justcooooode/p/7603381.html

Returns a canonical representation for the string object.

A pool of strings, initially empty, is maintained privately by the class String.

When the intern method is invoked, if the pool already contains a string equal to this String object as determined by the equals(Object) method, then the string from the pool is returned. Otherwise, this String object is added to the pool and a reference to this String object is returned.

It follows that for any two strings s and t, s.intern() == t.intern() is true if and only if s.equals(t) is true.

All literal strings and string-valued constant expressions are interned. String literals are defined in section 3.10.5 of the The Java™ Language Specification.




---

https://www.zhihu.com/question/31203609/answer/576030121


值传递还是引用传递
实参将访问对象的地址传递给了形参，然后看形参有没有重新new一个对象，重新指向了一个新的地址空间。

如果指向了新的地址空间，这个方法中的操作都是在新的对象上做的，原来的对象就没有改变

传递过去之后