Set接口，继承Collection接口

包含无序不重复元素的集合，所谓不重复就是不包含一对元素e1和e2，使得e1.equals(e2)，并且该集合只包含一个null元素

# HashSet
内部主要有个HashMap实例,HashSet的构造函数，就是实例化一个HashMap实例。所以很多构造函数都是基于HashMap
```
private transient HashMap<E,Object> map;
```
HashSet的add方法调用的是HashMap的put方法。

# TreeSet

# LinkedHashSet


```java
Set.add(Object value);


/**
 * 源码：
 * Adds the specified element to this set if it is not already present.
 * More formally, adds the specified element <tt>e</tt> to this set if
 * this set contains no element <tt>e2</tt> such that
 * <tt>(e==null&nbsp;?&nbsp;e2==null&nbsp;:&nbsp;e.equals(e2))</tt>.
 * If this set already contains the element, the call leaves the set
 * unchanged and returns <tt>false</tt>.
 *
 * @param e element to be added to this set
 * @return <tt>true</tt> if this set did not already contain the specified
 * element
 */
public boolean add(E e) {
    return map.put(e, PRESENT)==null;
}
```