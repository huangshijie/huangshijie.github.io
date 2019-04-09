# Optimize

## for循环的优化

- 减少对变量的重复计算

```java
for (int i = 0; i < args.length; i++) {
  ...
}
```

```java
for (int i = 0, length = args.length; i < length; i++) {
  ...
}
```

- 减少对临时变量的引用

```java
for (int i = 0, length = args.length; i < length; i++) {
  Object obj = new Object();
}
```

```java
Object obj = null;
for (int i = 0, length = args.length; i < length; i++) {
   obj = new Object();
}
```

## euqals()

尽量不要使用可能为空的对象来调用equals()方法

```java
MyObject obj = new MyObject();
String name = "sss";
if(obj.getName().equals(name)){
  // 这里obj有可能为null，getName()就会空指针异常的错
}
```

```java
MyObject obj = new MyObject();
String name = "sss";
if(name.equals(obj.getName())){
  // 这里obj有可能为null，但是也会返回false
}
```

## 底层使用可变数组的数据结构尽量指定长度

这是对ArrayList, HashMap等有扩容机制的数据结构来说

## String类尽量使用StringBuffer、StringBuilder

一个String对象会在内存中开辟一个空间。

## 尽量采用懒加载的策略，就是在需要的时候才创建

这个和上面的for循环优化，可以说是

## 数值运算时，尽量使用位移操作

结构优化：

    A. action层尽量不要做参数的校验和逻辑书写，它只负责请求的转发和响应，入参和出参

    B. Service层逻辑要清晰，尽量不要有if,for的出现，全部封装到公共方法去调用

    C. DAO层只提供持久层相关操作，如封装参数进map供持久层使用，尽量不要有逻辑在里面，所有的逻辑应都在service里完成

日志优化：

    A. 方法开始后、结束前书写日志

    B. 抓取异常时书写日志

    C. 特殊情况时书写。如需要提前return时

可读性优化：

    A. 类、方法必须要有注释

    B. 在一些比较大的逻辑前加上注释

    C. 一个完美的service层，应逻辑清晰，一行代码代表一个逻辑，通篇下来可读性非常强

异常优化：

    A. 异常应只用来进行错误处理，而不是逻辑处理（异常也是高开销的操作）

    B. 善用多种异常，但是在service层应只有一个 try cath来抓取多个异常，并分别处理

    C. 异常理应在service层全部处理完，不能再继续往上抛（理论上可以接续抛，但service本来就是处理逻辑的，尽量在本层处理完）
