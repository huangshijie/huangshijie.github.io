# ArrayList

## Introduction

- Java 9 增加了List.of(), Set.of(), Map.of()
- Colloctors 增加新方法filtering, flatMapping
- 实现了List接口，实现了所有list操作，允许各种元素，包括null，除了不同步，其他大致和Vector差不多
- 方法size, isEmpty, get, set, iterator, listIterator，不管数据多少，运行都是相同时间
- 方法add，运行时间与添加的个数相关，添加n个，需要O(n)
- 其他方法基本上是线性增长的时间
- 常数因子比LinkedList低，~~什么意思？~~
- ArrayList的capacity比arraylist的实际长度长，并且在add new element的时候，capacity会自动增长
- **不同步**
- iterator，快速失败机制

```word
ArrayList，内部有个申明为final的静态变量EMPTY_ELEMENTDATA和DEFAULTCAPACITY_EMPTY_ELEMENTDATA，是所有的arraylist共享的，声明final，可以防止被修改。
```

```word
Resizable-array implementation of the <tt>List</tt> interface. Implements all optional list operations, and permits all elements, including <tt>null</tt>. In addition to implementing the <tt>List</tt> interface, this class provides methods to manipulate the size of the array that is used internally to store the list. (This class is roughly equivalent to <tt>Vector</tt>, except that it is unsynchronized.)

 <p>The <tt>size</tt>, <tt>isEmpty</tt>, <tt>get</tt>, <tt>set</tt>, <tt>iterator</tt>, and <tt>listIterator</tt> operations run in constant time. The <tt>add</tt> operation runs in <i>amortized constant time</i>, that is, adding n elements requires O(n) time. All of the other operations run in linear time (roughly speaking). The constant factor is low compared to that for the <tt>LinkedList</tt> implementation.

 <p>Each <tt>ArrayList</tt> instance has a <i>capacity</i>. The capacity is the size of the array used to store the elements in the list. It is always at least as large as the list size. As elements are added to an ArrayList, its capacity grows automatically. The details of the growth policy are not specified beyond the fact that adding an element has constant amortized time cost.

 <p>An application can increase the capacity of an <tt>ArrayList</tt> instance before adding a large number of elements using the <tt>ensureCapacity</tt> operation. This may reduce the amount of incremental reallocation. 

 <p><strong>Note that this implementation is not synchronized.</strong> If multiple threads access an <tt>ArrayList</tt> instance concurrently, and at least one of the threads modifies the list structurally, it <i>must</i> be synchronized externally. (A structural modification is any operation that adds or deletes one or more elements, or explicitly resizes the backing array; merely setting the value of an element is not a structural modification.) This is typically accomplished by synchronizing on some object that naturally encapsulates the list. 

 If no such object exists, the list should be "wrapped" using the {@link Collections#synchronizedList Collections.synchronizedList} method. This is best done at creation time, to prevent accidental unsynchronized access to the list:<pre> List list = Collections.synchronizedList(new ArrayList(...));</pre>  

 <p><a name="fail-fast"> The iterators returned by this class's {@link #iterator() iterator} and {@link #listIterator(int) listIterator} methods are <em>fail-fast</em>:</a> if the list is structurally modified at any time after the iterator is created, in any way except through the iterator's own {@link ListIterator#remove() remove} or {@link ListIterator#add(Object) add} methods, the iterator will throw a {@link ConcurrentModificationException}.  Thus, in the face of concurrent modification, the iterator fails quickly and cleanly, rather than risking arbitrary, non-deterministic behavior at an undetermined time in the future.  

 <p>Note that the fail-fast behavior of an iterator cannot be guaranteed as it is, generally speaking, impossible to make any hard guarantees in the presence of unsynchronized concurrent modification.  Fail-fast iterators throw {@code ConcurrentModificationException} on a best-effort basis. Therefore, it would be wrong to write a program that depended on this exception for its correctness:  <i>the fail-fast behavior of iterators should be used only to detect bugs.</i>
```