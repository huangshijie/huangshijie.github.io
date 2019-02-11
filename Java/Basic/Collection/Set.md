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


