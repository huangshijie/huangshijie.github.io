# Deque

不建议往里插null值，因为有些方法中，null是一个特殊的返回值。

## Stack

Java里有一个叫做Stack的类，却没有叫做Queue的类（它是个接口名字）。当需要使用栈时，Java已不推荐使用Stack，而是推荐使用更高效的ArrayDeque；既然Queue只是一个接口，当需要使用队列时也就首选ArrayDeque了（次选是LinkedList）。

在Java里不推荐使用Stack类，推荐使用Deque接口的实现类，应该是和Stack类的实现有关系。例如：

```java
Deque<Integer> stack = new ArrayDeque<Integer>();
```
