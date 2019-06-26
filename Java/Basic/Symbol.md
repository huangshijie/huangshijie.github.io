# 比较类

## 对象怎样才算相等

```word
默认情况下也就是从超类Object继承而来的equals方法与‘==’是完全等价的，比较的都是对象的内存地址，但我们可以重写equals方法，使其按照我们的需求的方式进行比较，如String类重写了equals方法，使其比较的是字符的序列，而不再是内存地址。

引用相等
引用到堆上同一个对象的两个引用是相等的，就是引用相等。 ==

对象相等
堆上两个不同对象在意义上相同，就是对象相等。(所以说，平时说对象对象，要确认这个指的是堆上的对象)

```word
两个对象相等的条件是它们的hashCode相等，而且调用以另一个对象为参数的equals时返回true：
```

```java
if (foo.equals(bar) && foo.hashCode() == bar.hashCode()) {
    // 两个引用指向同一个对象或者两个对象是相等的 
}
```

---

## 重写equals()时为什么也得重写hashCode()

**重写equals方法时需要重写hashCode方法，主要是针对Map、Set等集合类型的使用**:

1. Map、Set等集合类型存放的对象必须是唯一的；

2. 集合类判断两个对象是否相等，是先判断equals是否相等，如果equals返回TRUE，还要再判断HashCode返回值是否ture,只有两者都返回ture,才认为该两个对象是相等的。

**同时**,

- equals()相等的两个对象，hashcode()一定相等； 
- equals()不相等的两个对象，却并不能证明他们的hashcode()不相等。换句话说，equals()方法不相等的两个对象，hashcode()有可能相等。（我的理解是由于哈希码在生成的时候产生冲突造成的）。 
- 反过来：hashcode()不等，一定能推出equals()也不等；hashcode()相等，equals()可能相等，也可能不等。

拿String类举例，String中同时，重写了equals()和hashcode()方法。
在equals方法中，首先调用 "==" 判断两个对象的内存地址是否一样，如果一样说明，他们引用到的是堆上同一个对象，所以相等。
或者通过两个对象按顺序一个个char进行比较，如果每个char都相等，则说明他们相等。
同样，重写的hashcode()方法中，是以31为权，每一位为字符的ASCII值进行运算，用自然溢出来等效取模。
哈希计算公式可以计为s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
关于为什么取31为权，主要是因为31是一个奇质数，所以31*i=32*i-i=(i<<5)-i，这种位移与减法结合的计算相比一般的运算快很多。

```java
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof String) {
            String anotherString = (String)anObject;
            int n = value.length;
            if (n == anotherString.value.length) {
                char v1[] = value;
                char v2[] = anotherString.value;
                int i = 0;
                while (n-- != 0) {
                    if (v1[i] != v2[i])
                        return false;
                    i++;
                }
                return true;
            }
        }
        return false;
    }
```

```java
    public int hashCode() {
        int h = hash;
        if (h == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 31 * h + val[i];
            }
            hash = h;
        }
        return h;
    }
```

解释下第3点的使用范围，我的理解是在object、String等类中都能使用。在object类中，hashcode()方法是本地方法，返回的是对象的地址值，而object类中的equals()方法比较的也是两个对象的地址值，如果equals()相等，说明两个对象地址值也相等，当然hashcode()也就相等了；
在String类中，equals()返回的是两个对象内容的比较，当两个对象内容相等时(比较的是内容的值)， Hashcode()方法根据String类的重写（第2点里面已经分析了）代码的分析，也可知道hashcode()返回结果也会相等。以此类推，可以知道Integer、Double等封装类中经过重写的equals()和hashcode()方法也同样适合于这个原则。当然没有经过重写的类，在继承了object类的equals()和hashcode()方法后，也会遵守这个原则。

[](https://blog.csdn.net/javazejian/article/details/51348320)

- 在重写equals方法时，还是需要注意如下几点规则的。

- 自反性。对于任何非null的引用值x，x.equals(x)应返回true。

- 对称性。对于任何非null的引用值x与y，当且仅当：y.equals(x)返回true时，x.equals(y)才返回true。

- 传递性。对于任何非null的引用值x、y与z，如果y.equals(x)返回true，y.equals(z)返回true，那么x.equals(z)也应返回true。

- 一致性。对于任何非null的引用值x与y，假设对象上equals比较中的信息没有被修改，则多次调用x.equals(y)始终返回true或者始终返回false。

- 对于任何非空引用值x，x.equal(null)应返回false。

我们知道所有的类都继承自Object类，而Object类默认的equals方法是使用==进行比较：

```java
public boolean equals(Object obj) {
    return (this == obj);
}
```

Object类的hashCode函数：

```java
public native int hashCode();
```

是一个native函数，而且返回值类型是整形；实际上，该native方法将对象在内存中的地址作为哈希码返回，可以保证不同对象的返回值不同。

因此，要比较两个对象的相等性，首先需要重写equals和hashCode方法。

---

符号 ^ ，位异或运算，相同取0，相反取1。

经常看到代码包含

```java
Objects.hashCode(e1) ^ Objects.hashCode(e2)
```

可以用来做不需要额外空间的整数交换

```java
a ^= b;
b ^= a;
a ^= b; // 交换完成
```

## 位移类

```word
>>
右移，也叫带符号右移，若该数为正的时候，高位补0，若为负，则高位补1；

>>>
无符号右移，也叫逻辑右移，不论正负都补0；
```

```word
<<

```

## 与运算

& 与运算效率更高
