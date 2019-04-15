# Inner Class

故如果定义了一个匿名内部类，并且希望它使用一个其外部定义的参数，那么编译器会要求该参数引用是final的。

留意外部类的方法的形参，当所在的方法的形参需要被内部类里面使用时，该形参必须为final。这里可以看到形参name已经定义为final了，而形参city 没有被使用则不用定义为final。为什么要定义为final呢？在网上找到本人比较如同的解释：
“这是一个编译器设计的问题，如果你了解java的编译原理的话很容易理解。  
首先，内部类被编译的时候会生成一个单独的内部类的.class文件，这个文件并不与外部类在同一class文件中。  
当外部类传的参数被内部类调用时，从java程序的角度来看是直接的调用例如：  

```java
public void dosome(final String a,final int b){  
  class Dosome{
    public void dosome(){
      System.out.println(a+b)
    }
  };  
  Dosome some=new Dosome();  
  some.dosome();  
}
```

从代码来看好像是那个内部类直接调用的a参数和b参数，但是实际上不是，在java编译器编译以后实际的操作代码是

```java
class Outer$Dosome{  
  public Dosome(final String a,final int b){  
    this.Dosome$a=a;  
    this.Dosome$b=b;  
  }  
  public void dosome(){  
    System.out.println(this.Dosome$a + this.Dosome$b);  
  }  
}
```

从以上代码看来，内部类并不是直接调用方法传进来的参数，而是内部类将传进来的参数通过自己的构造器备份到了自己的内部，自己内部的方法调用的实际是自己的属性而不是外部类方法的参数。  

这样理解就很容易得出为什么要用final了，因为两者从外表看起来是同一个东西，但实际上却不是，所以他们两者是可以任意变化的，也就是说在内部类中我对属性的改变并不会影响到外部的形参，而然这从程序员的角度来看这是不可行的，毕竟站在程序的角度来看这两个根本就是同一个，如果内部类该变了，而外部方法的形参却没有改变这是难以理解和不可接受的，所以为了保持参数的一致性，就规定使用final来避免形参的不改变。

简单理解就是，拷贝引用，为了避免引用值发生改变，例如被外部类的方法修改等，而导致内部类得到的值不一致，于是用final来让该引用不可改变。

## Anonymous Inner Class

### 定义

匿名内部类由于没有名字，所以它的创建方式有点儿奇怪。创建格式如下：

```java
new 父类构造器（参数列表）|实现接口（）  
{  
  //匿名内部类的类体部分  
}
```

在这里我们看到使用匿名内部类我们必须要继承一个**父类**或者实现一个**接口**，当然也仅能只继承一个父类或者实现一个接口。同时它也是没有class关键字，这是因为匿名内部类是直接使用new来生成一个对象的引用。当然这个引用是隐式的。

对于匿名内部类的使用它是存在一个缺陷的，就是它仅能被使用一次，**创建匿名内部类时它会立即创建一个该类的实例，该类的定义会立即消失**，所以匿名内部类是不能够被重复使用。对于上面的实例，如果我们需要对内部类进行多次使用，建议重新定义类，而不是使用匿名内部类。

### 注意

在使用匿名内部类的过程中，我们需要注意如下几点：

- 使用匿名内部类时，我们必须是继承一个类或者实现一个接口，但是两者不可兼得，同时也只能继承一个类或者实现一个接口。
- 匿名内部类中是不能定义构造函数的。
- 匿名内部类中不能存在任何的静态成员变量和静态方法。
- 匿名内部类为局部内部类，所以局部内部类的所有限制同样对匿名内部类生效。
- 匿名内部类不能是抽象的，它必须要实现继承的类或者实现的接口的所有抽象方法。

### 匿名内部类初始化

我们一般都是利用构造器来完成某个实例的初始化工作的，但是匿名内部类是没有构造器的！那怎么来初始化匿名内部类呢？使用构造代码块！利用构造代码块能够达到为匿名内部类创建一个构造器的效果。

```java
public class Outer {
  public static void main(String[] args) {
    Outer outer = new Outer();
    Inner inner = outer.getInner("Inner", "gz");
    System.out.println(inner.getName());
  }
  // 注意这里的形参city，由于它没有被匿名内部类直接使用，而是被抽象类Inner的构造函数所使用，所以不必定义为final。
  public Inner getInner(final String name, String city) {
    return new Inner(name, city) {
      private String nameStr = name;
  
      public String getName() {
        return nameStr;
      }
    };
  }
}
  
 abstract class Inner {
     Inner(String name, String city) {
         System.out.println(city);
     }
  
     abstract String getName();
}
```

```java
public class Outer {
  public static void main(String[] args) {
    Outer outer = new Outer();
    Inner inner = outer.getInner("Inner", "gz");
    System.out.println(inner.getName());
    System.out.println(inner.getProvince());
  }
  
  public Inner getInner(final String name, final String city) {
    return new Inner() {
      private String nameStr = name;
      private String province;
  
      // 实例初始化
      {
        if (city.equals("gz")) {
          province = "gd";
        }else {
          province = "";
        }
      }
  
      public String getName() {
        return nameStr;
      }
  
      public String getProvince() {
        return province;
      }
    };
  }
}
  
interface Inner {
     String getName();
     String getProvince();
}
```

### 相关问题

#### 双括号实例化( Double Brace Initialization )

使用双括号实例化，在某种情况下可以减少代码量，用一行表达式。

但是不推荐使用

##### 常规方式

```java
@Test
public void whenInitializeSetWithoutDoubleBraces_containsElements() {
  Set<String> countries = new HashSet<String>();
  countries.add("India");
  countries.add("USSR");
  countries.add("USA");
  
  assertTrue(countries.contains("India"));
}
```

上面的代码，做了三步：

1. 创建了HashSet的实例
2. 将国家一个个加到实例中
3. 最后判断India在不在实例中

##### 使用双括号方式

```java
@Test
public void whenInitializeSetWithDoubleBraces_containsElements() {
  Set<String> countries = new HashSet<String>() {
    {
      add("India");
      add("USSR");
      add("USA");
    }
  };
  assertTrue(countries.contains("India"));
}
```

上面的代码则做了以下三步：

1. 创建了HashSet的匿名内部类
2. 提供了一个包含add方法的对象实例化代码块，并且在这个实例化代码块中将国家名一个个加进去。
3. 最后一步和上面的一样

##### 比较

使用双括号的优势：

- 比较少的代码量
- 代码可读性更高

缺点：

- 初始化方式不是很多人都了解；
- 每次使用都会创建额外一个类；
- 有内存泄漏的风险;
- 如果要拓展的类被标记为final，则不能使用;
- 不支持菱形操作符;

> 在Java7中对泛型的支持得到了增强，消除了冗余的泛型实例化类型指定，例如在new对象时可以简化书写，将new后面<>内的内容省掉，这个<>也被称为菱形运算符，使用菱形运算符编译器会自动推导泛型的实例化类型。示例代码：

```java
Map<Integer, List<String>> map = new HashMap<>();
```

##### 改进

- Java 8 的Stream API:

```java
@Test
public void whenInitializeUnmodifiableSetWithDoubleBrace_containsElements() {
  Set<String> countries = Stream.of("India", "USSR", "USA")
    .collect(collectingAndThen(toSet(), Collections::unmodifiableSet));
  
  assertTrue(countries.contains("India"));
}
```

- Java 9的集合工厂方法

```java
List<String> list = List.of("India", "USSR", "USA");
Set<String> set = Set.of("India", "USSR", "USA");
```

##### 参考

- [Java Double Brace Initialization](https://www.baeldung.com/java-double-brace-initialization)
- [Efficiency of Java “Double Brace Initialization”?](https://stackoverflow.com/questions/924285/efficiency-of-java-double-brace-initialization)