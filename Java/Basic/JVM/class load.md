# Fundamentals

## 双亲委派机制

http://www.importnew.com/6581.html

https://www.cnblogs.com/wxd0108/p/6681618.html

https://www.cnblogs.com/gdpuzxs/p/7044963.html

https://blog.csdn.net/javazejian/article/details/73413292

### 定义

类加载器加载一个类，首先会在自己加载历史中查找，看有没有加载过这个类，加载过的话，就在cache中找到并返回。

> 同时给自己的父类加载器一个机会。
> 最后是自己
> 类加载器只允许看到自己加载过的或者父类、祖辈加载器加载过的类。 自己孩子加载过的类是看不到的。

### 作用

### 破坏双亲委派

## 类加载器的分类

一共有四种：

- bootstrap 类加载器，是JVM自带的，不能通过Java code实例化一个出来。主要功能是从制定路径加载核心系统类。一般这些个类存在$JAVA_HOME/jre/lib/rt.jar

- 拓展类加载器，bootstrao类加载器的子类，顾名思义就是用来加载拓展类，一般地址位于$JAVA_HOME/jre/lib/ext/rt.jar

- System class loader/ Application class loader。用户自定义的类加载器的父类都是它。可以通过ClassLoader.getSystemClassLoader()方法获得。

- 用户自定类加载器。网络加载器、热部署加载器


## 类加载过程

虚拟机将描述类的数据从class文件中加载到内存，并对数据进行校验、转换解析和初始化，最终形成可以被虚拟机直接使用的Java类型

![Class Life Circle](https://github.com/huangshijie/ImgRep/blob/master/class_life_circle.png)

类被加载到虚拟机内存中，从开始到卸载为止，一共经历了七个阶段，

### 加载

### 验证

### 准备

### 解析

### 初始化

## 显示加载 VS 隐式加载

cl.

forName()


findClass

defineClass

resolveClass


## 问题

### 类加载死锁

http://www.importnew.com/15268.html