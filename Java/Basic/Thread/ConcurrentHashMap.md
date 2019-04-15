# ConcurrentHashMap

## JDK 1.7

在**JDK 1.7**中，ConcurrentHashMap使用分段锁技术，提高了对hashmap并发读写的速度。**分段锁**就是将数据分成一段一段的存储，然后再给每段数据配一把锁，而不是把锁上在整块集合对象上。这样当一个线程占用访问其中一个数据段的同时，其他段的数据也能被其他线程访问。

ConcurrentHashMap的实现，就是一个Segment类型的数组，Segment类型的构造函数如下，数据会存储在Segment中HashEntry类型的数组中(HashEntry<K,V>[] tab)。

```java
Segment(float lf, int threshold, HashEntry<K,V>[] tab) {
  this.loadFactor = lf;
  this.threshold = threshold;
  this.table = tab;
}
```

HashEntry类型的构造函数如下，每个HashEntry又可以作为构成一个链表的节点。

```java
HashEntry(int hash, K key, V value, HashEntry<K,V> next) {
  this.hash = hash;
  this.key = key;
  this.value = value;
  this.next = next;
}
```

### 操作

## JDK 1.8

<https://www.cnblogs.com/leesf456/p/5453341.html>

<http://www.cnblogs.com/huaizuo/archive/2016/04/20/5413069.html>

<http://www.importnew.com/22007.html>

在**JDK 1.8**中，ConcurrentHashMap的实现，直接使用Node数组+链表+红黑树的数据结构实现，并发控制使用Synchronized和CAS来实现。Synchronized都是给数组中的元素(即链表的头节点上锁)。因为是给数组中的元素上锁，所以与HashTable不一样，这样虽然提高了put和get的效率，但是也同样带来了，size()、isEmpty()等方法返回值不是很准确。

不再使用Segment类型，数据存储在一个Node类型的table和nextTable中。但是为了兼容性，还是保留了Segment的一个精简版的工具类型。

```java
transient volatile Node<K,V>[] table;

private transient volatile Node<K,V>[] nextTable;
```

Node类型构造函数如下

```java
Node(int hash, K key, V val, Node<K,V> next) {
  this.hash = hash;
  this.key = key;
  this.val = val;
  this.next = next;
}
```

### CAS算法

CAS（Compare-and-Swap），即比较并替换，是一种实现并发算法时常用到的技术。

CAS是一条CPU的原子指令，不会造成所谓的数据不一致问题。

在ConcurrentHashMap中，**putVal()**、**compute()**、**computeIfAbsent()**、**merge**和**transfer()**这几个方法中包含有**casTabAt()**方法。

```java
static final <K,V> boolean casTabAt(Node<K,V>[] tab, int i,
                                        Node<K,V> c, Node<K,V> v) {
    return U.compareAndSwapObject(tab, ((long)i << ASHIFT) + ABASE, c, v);
}
```

```java
/**
 * Atomically update Java variable to <tt>x</tt> if it is currently
 * holding <tt>expected</tt>.
 * @return <tt>true</tt> if successful
 */
public final native boolean compareAndSwapObject(Object o, long offset,
                                                     Object expected,
                                                     Object x);
```

#### Unsafe类

Unsafe类，提供给开发者操作内存空间的能力。

参考资料:
<https://www.cnblogs.com/pkufork/p/java_unsafe.html>

<https://www.jb51.net/article/140726.htm>

<http://ifeve.com/sun-misc-unsafe/>

sun.misc.Unsafe

### Put

1. 判断存储的key、value是否为空，若为空，则抛出异常，否则，进入步骤②

2. 计算key的hash值，随后进入无限循环，该无限循环可以确保成功插入数据，若table表为空或者长度为0，则初始化table表，否则，进入步骤③

3. 根据key的hash值取出table表中的结点元素，若取出的结点为空（该桶为空），则使用CAS将key、value、hash值生成的结点放入桶中。否则，进入步骤4

4. 若该结点的的hash值为MOVED，则对该桶中的结点进行转移，否则，进入步骤⑤

5. 对桶中的第一个结点（即table表中的结点）进行**加锁**，对该桶进行遍历，桶中的结点的hash值与key值与给定的hash值和key值相等，则根据标识选择是否进行更新操作（用给定的value值替换该结点的value值），若遍历完桶仍没有找到hash值与key值和指定的hash值与key值相等的结点，则直接新生一个结点并赋值为之前最后一个结点的下一个结点。进入步骤⑥

6. 若binCount值达到红黑树转化的阈值，则将桶中的结构转化为红黑树存储，最后，增加binCount的值。

```word
JDK1.8为什么使用内置锁synchronized来代替重入锁ReentrantLock，我觉得有以下几点
因为粒度降低了，在相对而言的低粒度加锁方式，synchronized并不比ReentrantLock差，在粗粒度加锁中ReentrantLock可能通过Condition来控制各个低粒度的边界，更加的灵活，而在低粒度中，Condition的优势就没有了
JVM的开发团队从来都没有放弃synchronized，而且基于JVM的synchronized优化空间更大，使用内嵌的关键字比使用API更加自然
在大量的数据操作下，对于JVM的内存压力，基于API的ReentrantLock会开销更多的内存，虽然不是瓶颈，但是也是一个选择依据
```