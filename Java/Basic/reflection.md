# Reflection

```word
We conservatively check generic types via reflection to validate this -- see method comparableClassFor)
```

反射的作用，这里是用来检查两个元素是否使用CompareTo方法来排序

```java
static Class<?> comparableClassFor(Object x) {
  if (x instanceof Comparable) {
    Class<?> c; Type[] ts, as; Type t; ParameterizedType p;
    if ((c = x.getClass()) == String.class) // bypass checks
      return c;
    if ((ts = c.getGenericInterfaces()) != null) {
      for (int i = 0; i < ts.length; ++i) {
        if (((t = ts[i]) instanceof ParameterizedType) &&
          ((p = (ParameterizedType)t).getRawType() ==
          Comparable.class) &&
          (as = p.getActualTypeArguments()) != null &&
          as.length == 1 && as[0] == c) // type arg is c
            return c;
      }
    }
  }
  return null;
}
```
