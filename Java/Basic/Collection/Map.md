参考链接：
https://www.cnblogs.com/chengxiao/p/6059914.html

https://www.jianshu.com/p/03d0e77f182c

作为一个存储结构来说，查找和插入可以看作是硬币的两面，查找快，插入势必会慢；插入快，查找势必会慢

举例最常见的就是：数组和链表这两种结构



## HashMap
### WHAT
下面就是HashMap的主体，一个Node<K, V>的数组
```
transient Node<K,V>[] table;
```

Node<K, V>作为存储里的主体，链表中的一个元素
```
static class Node<K,V> implements Map.Entry<K,V> {
    final int hash;
    final K key;
    V value;
    Node<K,V> next;
    ...
}
```

### HOW
#### 构造函数
一共有四个构造函数，前三种是使用自定义或者默认初试容量(initialCapacity)和加载因子(loadFactor)来创建一个空的HashMap；第四个是使用一个相同映射的特殊Map，来创建一个新的HashMap，这里的初试容量和加载因子都是取的默认值。

注意这里前三种，在初始化的时候，并没有给table开辟内存空间，第四种会根据**Map<? extends K, ? extends V> m**的大小来设置table的最大容量，加载因子还是使用默认的

```
public HashMap(int initialCapacity, float loadFactor) 

public HashMap(int initialCapacity)

public HashMap()

public HashMap(Map<? extends K, ? extends V> m)
```

#### 增

#### 删

#### 改

#### 查

getNode
```
final Node<K,V> getNode(int hash, Object key) {
    Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
    if ((tab = table) != null && (n = tab.length) > 0 &&
        (first = tab[(n - 1) & hash]) != null) {
        if (first.hash == hash && // always check first node
            ((k = first.key) == key || (key != null && key.equals(k))))
            return first;
        if ((e = first.next) != null) {
            if (first instanceof TreeNode)
                return ((TreeNode<K,V>)first).getTreeNode(hash, key);
            do {
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    return e;
            } while ((e = e.next) != null);
        }
    }
    return null;
}
```
#### 其他


### WHY
- 允许value和key为null
- 不同步
- HashMap与HashTable大致相同，除了HashMap是不同步的，并且HashTable不接受为null的key和value
- 可以把HashMap看成数组加链表的实现，如果元素太多了的话，会自动转成树就是(treefied，树化)来提高查找速度
- HashMap由数组+链表组成，数组是HashMap的主体，链表主要是为了解决hash冲突
- 一个HashMap实例有两个参数影响它的性能：初始容量和装载因子，容量是散列表中的桶数，初始容量只是创建散列表时的容量。装载因子是衡量散列表完整性的度量标准，允许在容量自动增加之前获得。当散列表中的Entry数超过了装载因子和当前容量的乘积，hash表就rehashed（即内部数据结构重建），使散列表大约有两倍buckets数。
```
例如，当散列表的Entry数超过0.75*16时，就rehashed。因为碰撞，此时数组中可能还没有12个桶被占用。
```
而为什么默认的装载因子是0.75，源代码如下：
```
static final float DEFAULT_LOAD_FACTOR = 0.75f;
```
作为一般规则，默认装载因子（.75）在时间和空间成本之间提供了良好的权衡。较高的值会减少空间开销，但会增加查找成本（反映在HashMap类的大多数操作中，包括get和put）。在设置其初始容量时，应考虑映射中的预期条目数及其装载因子，以便最小化重新散列操作的数量。如果初始容量大于最大条目数除以装载因子，则不会发生重新装载操作。
- 如果说很多key有相同的hashcode，会降低hash table的表现。所以需要通过compareable，如果key支持java.lang.comparable，再进行一次排序插入进链表中，来降低影响

TREEIFY_THRESHOLD


HashMap扩容机制



