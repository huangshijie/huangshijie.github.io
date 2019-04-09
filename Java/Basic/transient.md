# 序列化相关的

## 序列化

```java
serialVersionUID = 1L

1L means 1 is in Long type. 1 could be regraded as Version Number.
If user defines a serialVersionUID for a class, Java will not generate this UID for this class.
In this situation, serialVersionUID is not changed. JVM will take .class as the same even if this class has already be changed.
```

将一个对象转换成一串二进制表示的字节数组，通过保存或转移这些字节数据来达到持久化的目的。

serialVersionUID就是在序列化和反序列化的时候，用来判断二进制字节数组能否转换成当前的class。

比如说我创建了一个class，并且基于该class实例化了一个对象，并且将这个对象以二进制数组的形式保存进了本地文件或者通过网络通信的方式传了出去。这个过程叫**序列化**。
在我反序列化的时候，需要将该二进制数组强制转换成该class的一个对象。但是这时候不能保证序列化的class，和当前class还是一致的，就需要验证serialVersionUID是否一致

### WHAT

#### 手动指定序列化过程

Java并不强求用户非要使用默认的序列化方式，用户也可以按照自己的喜好自己指定自己想要的序列化方式----只要你自己能保证序列化前后能得到想要的数据就好了。手动指定序列化方式的规则是：

进行序列化、反序列化时，虚拟机会首先试图调用对象里的writeObject和readObject方法，进行用户自定义的序列化和反序列化。如果没有这样的方法，那么默认调用的是ObjectOutputStream的defaultWriteObject以及ObjectInputStream的defaultReadObject方法。换言之，利用自定义的writeObject方法和readObject方法，用户可以自己控制序列化和反序列化的过程。

这是非常有用的。比如：

1. 有些场景下，某些字段我们并不想要使用Java提供给我们的序列化方式，而是想要以自定义的方式去序列化它，比如ArrayList的elementData、HashMap的table（至于为什么在之后写这两个类的时候会解释原因），就可以通过将这些字段声明为transient，然后在writeObject和readObject中去使用自己想要的方式去序列化它们

2. 因为序列化并不安全，因此有些场景下我们需要对一些敏感字段进行加密再序列化，然后再反序列化的时候按照同样的方式进行解密，就在一定程度上保证了安全性了。要这么做，就必须自己写writeObject和readObject，writeObject方法在序列化前对字段加密，readObject方法在序列化之后对字段解密

#### WriteObject

#### ReadObject

### HOW

#### class

```java
public class SerializableObject implements Serializable{
    private static final long serialVersionUID = 421823267041944041L;

    ....
}
```

#### 序列化对象

```java
public static void serializabelClass(File file) throws Exception {  
  OutputStream os = new FileOutputStream(file);
  ObjectOutputStream oos = new ObjectOutputStream(os);
  oos.writeObject(new SerializableObject("str0000", "str1111"));
  oos.close();
}
```

#### 反序列化对象

```java
public static void deserializableClass(File file) throws Exception {
  InputStream is = new FileInputStream(file);
  ObjectInputStream ois = new ObjectInputStream(is);
  SerializableObject so = (SerializableObject)ois.readObject();
  System.out.println("str0 = " + so.getStr0());
  System.out.println("str1 = " + so.getStr1());
  ois.close();
}

```

1. 在反序列化对象之前，修改**SerializableObject**的**serialVersionUID**为其他任意值，在进行反序列化操作的时候都会报java.io.InvalidClassException的错；
2. 在反序列化对象之前，修改**SerializableObject**的方法，但是不修改**serialVersionUID**，仍然是可以将该二进制数组强制转换成该对象。

总结下：按理说每次修改class之后，都应该修改**serialVersionUID**表示class已经被修改过了，但是如果想兼容之前版本class实例化出来的对象的话，可以不用修改**serialVersionUID**。

### WHY

#### 复杂序列化情况总结

虽然Java的序列化能够保证对象状态的持久保存，但是遇到一些对象结构复杂的情况还是比较难处理的，最后对一些复杂的对象情况作一个总结：

1. 当父类继承Serializable接口时，所有子类都可以被序列化
2. 子类实现了Serializable接口，父类没有，父类中的属性不能序列化（不报错，数据丢失），但是在子类中属性仍能正确序列化
3. 如果序列化的属性是对象，则这个对象也必须实现Serializable接口，否则会报错
4. 反序列化时，如果对象的属性有修改或删减，则修改的部分属性会丢失，但不会报错
5. 反序列化时，如果serialVersionUID被修改，则反序列化时会失败

### 引申

#### Object.clone()

Reference:
将对象转成二进制数组的时候，对应数组表达的意思