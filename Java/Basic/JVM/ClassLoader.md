# Fundamentals

## 类加载器

在虚拟机中，每一个类加载器都有自己独立的类名称空间。通过类本身和类加载器组合起来，才能确定一个类在虚拟机中的唯一性。
举个例子：两个类来自同一个class的二进制字节流，但是由不同的类加载器加载的，这两个就是不同的两个类。

## 分类

一共有四种：

- bootstrap 类加载器，是JVM自带的，不能通过Java code实例化一个出来。主要功能是从制定路径加载核心系统类。一般这些个类存在$JAVA_HOME/jre/lib/rt.jar

- 拓展类加载器，bootstrao类加载器的子类，顾名思义就是用来加载拓展类，一般地址位于$JAVA_HOME/jre/lib/ext/rt.jar

- System class loader/ Application class loader。用户自定义的类加载器的父类都是它。可以通过ClassLoader.getSystemClassLoader()方法获得。

- 用户自定类加载器。网络加载器、热部署加载器

### 双亲委派机制

[类加载器的工作原理](http://www.importnew.com/6581.html)

[Java自定义类加载器与双亲委派模型](https://www.cnblogs.com/wxd0108/p/6681618.html)

[Java类加载机制及自定义加载器](https://www.cnblogs.com/gdpuzxs/p/7044963.html)

[深入理解Java类加载器(ClassLoader)](https://blog.csdn.net/javazejian/article/details/73413292)

#### 定义

类加载器加载一个类，首先会在自己加载历史中查找，看有没有加载过这个类，加载过的话，就在cache中找到并返回。

> 同时给自己的父类加载器一个机会。
> 最后是自己
> 类加载器只允许看到自己加载过的或者父类、祖辈加载器加载过的类。 自己孩子加载过的类是看不到的。

![Class Loader Delegation Model](https://github.com/huangshijie/ImgRep/blob/master/Class_Loader_Delegation_Model.png)

其工作原理的是，如果一个类加载器收到了类加载请求，它并不会自己先去加载，而是把这个请求委托给父类的加载器去执行，如果父类加载器还存在其父类加载器，则进一步向上委托，依次递归，请求最终将到达顶层的启动类加载器，如果父类加载器可以完成类加载任务，就成功返回，倘若父类加载器无法完成此加载任务，子加载器才会尝试自己去加载，这就是双亲委派模式，即每个儿子都很懒，每次有活就丢给父亲去干，直到父亲说这件事我也干不了时，儿子自己想办法去完成。

#### 作用

避免重复加载

```word
重复加载也没有太大的危害，如果不是自己严格控制使用的类的classpath，就是指定使用哪个class，那么在实际使用的时候，java会自己按照加载顺序去找先加载的class来用。但是这种类重复加载的情况，必须避免，容易产生不宜察觉的bug，并且不同的版本之间还会有冲突。
```

#### 破坏双亲委派

- **线程上下文类加载器(Thread Context Classloader)**

- 用户对程序动态性的追求，例如**代码热替换**、**模块热部署**

```word
在写自定义ClassLoader的时候，使用代码 instance.getClass().getClassLoader()，返回的是sun.misc.Launcher$AppClassLoader@18b4aac2，是AppClassLoader？

原因是，因为自己写的classloader并没有重写loadclass方法，所以都是AppClassLoader加载的类，所以返回classLoader是AppClassLoader也是应该的。
或者更大的可能是，因为没有使用自定义classloader的findClass方法。下面代码和预期一致
```

```java
String filePathFirst = "C:\\Users\\i325805\\workspace\\Utils\\src\\main\\java\\com\\huang\\util\\classloader\\first\\ClassBeLoaded.class";

MyFirstClassLoader mfClassLoader = new MyFirstClassLoader(filePathFirst);
// Class<?> clf = Class.forName("com.huang.util.classloader.ClassBeLoaded", true, mfClassLoader); // Will use AppClassLoader to load class
Class<?> clf = mfClassLoader.findClass("com.huang.util.classloader.ClassBeLoaded");
Object of = clf.newInstance();

System.out.println(of);   // com.huang.util.classloader.ClassBeLoaded@4e25154f
System.out.println(of.getClass().getClassLoader());  // com.huang.util.classloader.MyFirstClassLoader@6d06d69c

String filePath = "C:\\Users\\i325805\\workspace\\Utils\\src\\main\\java\\com\\huang\\util\\classloader\\ClassBeLoaded.class";
MySecondClassLoader msClassLoader = new MySecondClassLoader(filePath);

// Class<?> cls = Class.forName("com.huang.util.classloader.ClassBeLoaded", true, msClassLoader); // Will use AppClassLoader to load class
Class<?> cls = msClassLoader.findClass("com.huang.util.classloader.ClassBeLoaded");
Object os = cls.newInstance();

System.out.println(os);  // com.huang.util.classloader.ClassBeLoaded@55f96302
System.out.println(os.getClass().getClassLoader()); // com.huang.util.classloader.MySecondClassLoader@5c647e05

System.out.println(cls.isInstance(of)); // false
System.out.println(cls.isInstance(os)); // true
```

## 类加载过程

虚拟机把描述类的数据从class文件(二进制字节流)加载到内存，并对数据进行校验、转换解析和初始化，最终形成可以被虚拟机直接使用的Java类型，这个就是虚拟机的类加载机制。

类从被加载到虚拟机内存到卸载出内存，整个生命周期包括如下七个阶段。加载、验证、准备、初始化、卸载，这个五个阶段的顺序是确定的。按照这个顺序，每个阶段依次开始，不一定需要前一个步骤结束，再继续。

![Class Life Circle](https://github.com/huangshijie/ImgRep/blob/master/class_life_circle.png)

类被加载到虚拟机内存中，从开始到卸载为止，一共经历了七个阶段，

### 加载

加载时类加载过程的一个阶段。在加载阶段，虚拟机需要完成以下3件事情：

1. 通过一个类的全限定名获取此类的二进制字节流
2. 将这个字节流所代表的静态存储结构转化为方法区的运行时数据结构
3. 在内存中生成一个代表这个类的java.lang.Class对象，作为方法区这个类的各种数据的访问入口

正是因为第一件事情的存在，使得类加载非常的灵活，二进制流可以来自很多地方。同时加载阶段，既可以使用系统提供的加载器加载，也可以使用用户自定义的加载器来控制字节流的获取方法。

### 验证

1. 文件格式验证：主要验证字节流是否符合Class文件格式规范，并且能被当前版本的虚拟机处理。主要验证点：  

   - 是否以魔数0xCAFEBABE开头
   - 主次版本号是否在当前虚拟机处理范围之内
   - 常量池的常量是否有不被支持的类型 (检查常量tag标志)
   - 指向常量的各种索引值中是否有指向不存在的常量或不符合类型的常量
   - CONSTANT_Utf8_info型的常量中是否有不符合UTF8编码的数据
   - Class文件中各个部分及文件本身是否有被删除的或者附加的其他信息...实际上验证的不仅仅是这些，关于Class文件格式可以参考我的深入理解JVM类文件格式，这阶段的验证是基于二进制字节流的，只有通过文件格式验证后，字节流才会进入内存的方法区中进行存储。

2. 元数据验证：主要对字节码描述的信息进行语义分析，以保证其提供的信息符合Java语言规范的要求。主要验证点：

   - 该类是否有父类（只有Object对象没有父类，其余都有）
   - 该类是否继承了不允许被继承的类（被final修饰的类）
   - 如果这个类不是抽象类，是否实现了其父类或接口之中要求实现的所有方法
   - 类中的字段、方法是否与父类产生矛盾（例如覆盖了父类的final字段，出现不符合规则的方法重载，例如方法参数都一致，但是返回值类型却不同）...

3. 字节码验证：主要是通过数据流和控制流分析，确定程序语义是合法的、符合逻辑的。在第二阶段对元数据信息中的数据类型做完校验后，字节码验证将对类的方法体进行校验分析，保证被校验类的方法在运行时不会做出危害虚拟机安全的事件。主要有：

   - 保证任意时刻操作数栈的数据类型与指令代码序列都能配合工作，例如不会出现类似的情况：操作数栈里的一个int数据，但是使用时却当做long类型加载到本地变量中
   - 保证跳转不会跳到方法体以外的字节码指令上
   - 保证方法体内的类型转换是合法的。例如子类赋值给父类是合法的，但是父类赋值给子类或者其它毫无继承关系的类型，则是不合法的。

4. 符号引用验证：最后一个阶段的校验发生在虚拟机将符号引用转化为直接引用的时候，这个转化动作将在连接的第三阶段解析阶段发生。符号引用是对类自身以外（常量池中的各种符号引用）的信息进行匹配校验。通常有：

   - 符号引用中通过字符串描述的全限定名是否找到对应的类
   - 在指定类中是否存在符合方法的字段描述符以及简单名称所描述的方法和字段
   - 符号引用中的类、方法、字段的访问性（private,public,protected、default）是否可被当前类访问符号引用验证的目的是确保解析动作能够正常执行，如果无法通过符号引用验证，那么将会抛出一个java.lang.IncompatibleClassChangeError异常的子类，如java.lang.IllegalAccessError、java.lang.NoSuchFieldError、java.lang.NoSuchMethodError等。

验证阶段非常重要，但不一定必要，如果所有代码极影被反复使用和验证过，那么可以通过虚拟机参数-Xverify: none来关闭验证，加速类加载时间。

### 准备

准备阶段的任务是为类或者接口的静态字段分配空间，并且默认初始化这些字段。这个阶段不会执行任何的虚拟机字节码指令，在初始化阶段才会显示的初始化这些字段，所以准备阶段不会做这些事情。假设有：

```java
public static int value = 123;
```

复制代码value在准备阶段的初始值为0而不是123，只有到了初始化阶段，value才会为123。

一种特殊情况是，如果字段属性表中包含ConstantValue属性，那么准备阶段变量value就会被初始化为ConstantValue属性所指定的值，比如上面的value如果这样定义：

```java
public static final int value = 123;
```

复制代码编译时，value一开始就指向ConstantValue，所以准备期间value的值就已经是123了。

### 解析

### 初始化

初始化是类加载的最后一步，在前面的阶段里，除了加载阶段可以通过用户自定义的类加载器加载，其余部分基本都是由虚拟机主导的。但是到了初始化阶段，才开始真正执行用户编写的java代码了。

在准备阶段，变量都被赋予了初始值，但是到了初始化阶段，所有变量还要按照用户编写的代码重新初始化。换一个角度，初始化阶段是执行类构造器**A()**方法的过程

在对类进行一个**主动引用**的时候，必须对类进行初始化。

主动引用主要有以下五种情况：

1. 使用new关键字实例化对象的时候、读取或者设置一个类的静态字段的时候(除去那种被final修饰放入常量池的静态字段)，调用一个类的静态方法的时候
2. 使用java.lang.reflect包的方法对类进行反射调用的时候，如果类没有初始化，就要先出发其初始化
3. 初始化一个类的时候，发现其父类没有初始化，则需要触发其父类的初始化
4. 包含main()方法的那个类
5. java.lang.invoke.MethodHandle

在情况一中，如果*Class A*中有变量*public static final String HELLOWORLD = "Hello World"*，因为有final修饰，该变量在编译阶段通过常量传播优化，就已经将该常量的值存储到了类A的常量池中，之后在其他类中对常量A.HELLOWORLD的引用实际转换成类A对自身常量池的引用。

## 显示加载 VS 隐式加载

cl.

forName()

findClass

defineClass

resolveClass

## 问题

### 类加载死锁

[类加载与锁](http://www.importnew.com/15268.html)