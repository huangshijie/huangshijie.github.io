# 比较类

## 对象怎样才算相等
```
默认情况下也就是从超类Object继承而来的equals方法与‘==’是完全等价的，比较的都是对象的内存地址，但我们可以重写equals方法，使其按照我们的需求的方式进行比较，如String类重写了equals方法，使其比较的是字符的序列，而不再是内存地址。

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
---

## 重写equal()时为什么也得重写hashCode()

https://blog.csdn.net/javazejian/article/details/51348320

- 在重写equals方法时，还是需要注意如下几点规则的。

- 自反性。对于任何非null的引用值x，x.equals(x)应返回true。

- 对称性。对于任何非null的引用值x与y，当且仅当：y.equals(x)返回true时，x.equals(y)才返回true。

- 传递性。对于任何非null的引用值x、y与z，如果y.equals(x)返回true，y.equals(z)返回true，那么x.equals(z)也应返回true。

- 一致性。对于任何非null的引用值x与y，假设对象上equals比较中的信息没有被修改，则多次调用x.equals(y)始终返回true或者始终返回false。

- 对于任何非空引用值x，x.equal(null)应返回false。


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

# 位移类
``` 
>> 
右移，若该数为正的时候，高位补0，若为负，则高位补1；

>>> 
无符号右移，也叫逻辑右移，不论正负都补0；

```