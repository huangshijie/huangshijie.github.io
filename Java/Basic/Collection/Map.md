参考链接：

https://www.cnblogs.com/chengxiao/p/6059914.html

https://www.jianshu.com/p/03d0e77f182c

http://yikun.github.io/2015/04/01/Java-HashMap%E5%B7%A5%E4%BD%9C%E5%8E%9F%E7%90%86%E5%8F%8A%E5%AE%9E%E7%8E%B0/


作为一个存储结构来说，查找和插入可以看作是硬币的两面，查找快，插入势必会慢；插入快，查找势必会慢

举例最常见的就是：数组和链表这两种结构



## HashMap
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

```
HashMap<String, Integer> h = new HashMap<String, Integer>();
		
h.put("key1", 1);
h.put("kkk2", 2);
h.put("eee3", 3);
h.put("yyy4", 4);
h.put("kkk5", 5);
h.put("eee6", 6);
h.put("yyy7", 7);

for(Entry<String, Integer> entry : h.entrySet()) {
	System.out.println(entry.toString());
}
```
![Debug HashMap Instance](https://github.com/huangshijie/ImgRep/blob/master/debug%20hashmap%20instance.png)

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

```
final Node<K,V>[] resize()
```

#### 增

先遍历查看新增Node在原来的HashMap中存不存在，如果存在就覆盖，不存在就新增。如果新增之后，链表的长度大于设定的极限(threshold)，则将该链表进行树化(treeifyBin)。

hash()方法主要是调用Object.hashCode()方法，hashCode是
```
static final int hash(Object key)
```

```
final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) 
```

```
final TreeNode<K,V> putTreeVal(HashMap<K,V> map, Node<K,V>[] tab,
                                       int h, K k, V v)
```

```
final void treeifyBin(Node<K,V>[] tab, int hash)
```

```
// Callbacks to allow LinkedHashMap post-actions
void afterNodeAccess(Node<K,V> p) { }
void afterNodeInsertion(boolean evict) { }
void afterNodeRemoval(Node<K,V> p) { }
```


#### 删

#### 改

#### 查
get和containsKey都会调用getNode，是get方法的具体实现

正如HashMap可以被看作是数组与链表的结合体，令**i = (n - 1) & hash**，其中n为table的长度，hash为key的hash值，对象会存储在数组中坐标为 *i* 所在的位置。

在getNode中有两个参数，一个hash是hash(key)，即key的哈希值，另一个key就是key对象。
所以在根据这个key进行查找的时候，首先根据key的hash值获得数组中对应位置的**Node = table[(n - 1) & hash]**。

然后以这个Node为链表的第一个节点，进行遍历，中间也要考虑这个Node是否为TreeNode，是的话，用treeNode的方式getTreeNode进行遍历。不是的话，就说明这个链表是一个普通的链表，直接do...while...遍历

```
final Node<K,V> getNode(int hash, Object key)
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



