# 比较类

对象怎样才算相等
```
引用相等
引用到堆上同一个对象的两个引用是相等的，就是引用相等。 ==

对象相等
堆上两个不同对象在意义上相同，就是对象相等。(所以说，平时说对象对象，要确认这个指的是堆上的对象)
```
两个对象相等的条件是它们的hashCode相等，而且调用以另一个对象为参数的equals时返回true：
```
if (foo.equals(bar) && foo.hashCode() == bar.hashCode()) {
    // 两个引用指向同一个对象或者两个对象是相等的 
}
```
我们知道所有的类都继承自Object类，而Object类默认的equals方法是使用==进行比较：
```
public boolean equals(Object obj) {
    return (this == obj);
}
```
Object类的hashCode函数：
```
public native int hashCode();
```
是一个native函数，而且返回值类型是整形；实际上，该native方法将对象在内存中的地址作为哈希码返回，可以保证不同对象的返回值不同。

因此，要比较两个对象的相等性，首先需要重写equals和hashCode方法。

---

符号 ^ ，位异或运算

经常看到代码包含
```
Objects.hashCode(e1) ^ Objects.hashCode(e2)
```

