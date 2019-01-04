序列化相关的

## 序列化
```
serialVersionUID = 1L

1L means 1 is in Long type. 1 could be regraded as Version Number.
If user defines a serialVersionUID for a class, Java will not generate this UID for this class.
In this situation, serialVersionUID is not changed. JVM will take .class as the same even if this class has already be changed.
```
将一个对象转换成一串二进制表示的字节数组，通过保存或转移这些字节数据来达到持久化的目的。

serialVersionUID就是在序列化和反序列化的时候，用来判断二进制字节数组能否转换成当前的class。

比如说我创建了一个class，并且基于该class实例化了一个对象，并且将这个对象以二进制数组的形式保存进了本地文件或者通过网络通信的方式传了出去。这个过程叫**序列化**。
在我反序列化的时候，需要将该二进制数组强制转换成该class的一个对象。但是这时候不能保证序列化的class，和当前class还是一致的，就需要验证serialVersionUID是否一致

**class**
```
public class SerializableObject implements Serializable{
    private static final long serialVersionUID = 421823267041944041L;

    ....
}
```

**序列化对象**
```
public static void serializabelClass(File file) throws Exception {  
  OutputStream os = new FileOutputStream(file);
  ObjectOutputStream oos = new ObjectOutputStream(os);
  oos.writeObject(new SerializableObject("str0000", "str1111"));
  oos.close();
}
```

**反序列化对象**
```
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

总结下：按理说每次修改class之后，都应该修改**serialVersionUID**表示class已经被修改过了，但是如果想兼容之前版本class实例化出来的对象的话，可以不用修改**serialVersionUID**。

### WHAT
#### 复杂序列化情况总结
虽然Java的序列化能够保证对象状态的持久保存，但是遇到一些对象结构复杂的情况还是比较难处理的，最后对一些复杂的对象情况作一个总结：
1. 当父类继承Serializable接口时，所有子类都可以被序列化
2. 子类实现了Serializable接口，父类没有，父类中的属性不能序列化（不报错，数据丢失），但是在子类中属性仍能正确序列化
3. 如果序列化的属性是对象，则这个对象也必须实现Serializable接口，否则会报错
4. 反序列化时，如果对象的属性有修改或删减，则修改的部分属性会丢失，但不会报错
5. 反序列化时，如果serialVersionUID被修改，则反序列化时会失败
   
### HOW

### WHY

Reference:
将对象转成二进制数组的时候，对应数组表达的意思