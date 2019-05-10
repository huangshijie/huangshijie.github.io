# Singleton(单例模式)

## Introduction

- 单例类只能有一个实例
- 单例类必须自己创建自己的实例
- 单例类必须给其他对象提供这唯一的实例
例如线程池、缓存、日志对象、一个Servlet类在Application中只有一个实例存在

所有的单例模式都是使用静态方法进行创建的，所以单例对象在内存中静态共享区中存储。

## 实现单例模式的方式

### 懒汉模式

### 饿汉模式

### 静态内部类

### static静态代码块

### 内部枚举类实现

## 设计实例

**Web计数器**

## Reference

- java.ejb.singleton 有这个annotation
ejb是Java EE服务器端组建模型，好像没什么人用，复杂笨重，调试麻烦。被轻量的RPC或者ORM取代，ejb的替换开源产品太多了:现在业务逻辑，在java上要用框架的有spring，远程调用，有webservice(apache cxf已经做得很好了，而且webservice又是通用标准)，mina(一个apache的NIO框架)，netty(现在性能最快的NIO框架，来自jboss).而且这些产品都是可移植，社区交流多，出了问题，google就找到了。(存疑？有时间看看RPC、ORM)
