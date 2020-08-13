# Spring

## 1 Java 标准注解
三种标准注解和四种元注解。

### 1.1.1 @Override
表示当前的方法定义将覆盖超类中的方法。如果你不小心拼写错误，或者方法签名对不上被覆盖的方法，编译器就会发出错误提示。

### 1.1.2 @Deprecated
如果程序员使用了注解为它的元素，那么编译器会发出警告信息。

### 1.1.3 @SuppressWarnings
关闭不当的编译器警告信息。在java SE5之前的版本中，也可以使用该注解，不过会被忽略不起作用。

## 2 Java元注解 Java Meta-Annotation
[Annotation Package Summary](https://docs.oracle.com/javase/8/docs/api/java/lang/annotation/package-summary.html)，

### 2.1 @Target
用来定义你的注解将应用于什么地方（例如是一个方法或者一个域）。@Target说明了Annotation所修饰的对象范围：Annotation可被用于 packages、types（类、接口、枚举、Annotation类型）、类型成员（方法、构造方法、成员变量、枚举值）、方法参数和本地变量（如循环变量、catch参数）。

用法例如：@Target(value=ANNOTATION_TYPE)

接受参数在如下表中
Enum Constants  | Description | Example
--|-- | --
ANNOTATION_TYPE | Annotation type declaration | 
CONSTRUCTOR | Constructor declaration 描述构造器 | 
FIELD | Field declaration (includes enum constants) 用于描述域 | 
LOCAL_VARIABLE | Local variable declaration 描述局部变量 | 
METHOD | Method declaration 描述方法 | 
PACKAGE | Package declaration 描述包 | 
PARAMETER | Formal parameter declaration 描述参数 | 
TYPE | Class, interface (including annotation type), or enum declaration 用于描述类、接口(包括注解类型) | 
TYPE_PARAMETER | Type parameter declaration 用来标注类型参数 | public <@TypeParameterAnnotation U> T foo(T t) {}
TYPE_USE | Use of a type 能标注任何类型名称 Use of a type | public static @TypeUseAnnotation class TypeUseClass<@TypeUseAnnotation T> extends @TypeUseAnnotation Object {      public void foo(@TypeUseAnnotation T t) throws @TypeUseAnnotation Exception {      }    }

### 2.2 @Inherited
指示自动继承批注类型。使用此注解声明出来的自定义注解，在使用此自定义注解时，如果注解在类上面时，子类会自动继承此注解，否则的话，子类不会继承此注解。这里一定要记住，使用Inherited声明出来的注解，只有在类上使用时才会有效，对方法，属性等其他无效。[Example](https://blog.csdn.net/snow_crazy/article/details/39381695)

### 2.3 @Retention
指示带批注类型的批注将保留多长时间。用来定义该注解在哪一个级别可用，在源代码中（SOURCE）、类文件中（CLASS）或者运行时（RUNTIME）。
表示在什么级别保存该注解信息。可选的参数值在枚举类型 RetentionPolicy 中，包括：

Emu | descr
-- | --
RetentionPolicy.SOURCE | 注解将被编译器丢弃
RetentionPolicy.CLASS | 注解在class文件中可用，但会被VM丢弃
RetentionPolicy.RUNTIME | VM将在运行期也保留注释，因此可以通过反射机制读取注解的信息。

### 2.4 @Documented
默认情况下，使用这个类型的annotation的注释，会被Javadoc或者类似工具记录下来

### 2.5 @Native (1.8新增)
指示可从本机代码引用定义常量值的字段。常常被代码生成工具使用。对于 @Native 注解不常使用，了解即可。

### 2.6 @Repeatable (1.8新增)
注释类型java.lang.annotation.Repeatable用于指示其声明（meta）注释的注释类型是可重复的。
允许在相同的程序元素中重复注解，在需要对同一种注解多次使用时，往往需要借助 @Repeatable 注解。Java 8 版本以前，同一个程序元素前最多只能有一个相同类型的注解，如果需要在同一个元素前使用多个相同类型的注解，则必须使用注解“容器”
1.8以前
```java
public @interface Roles {
    Role[] roles();
}

public @interface Role {
    String roleName();
}

public class RoleTest {
    @Roles(roles = {@Role(roleName = "role1"), @Role(roleName = "role2")})
    public String doString(){
        return "";
    }
}
```

1.8以后
```java
public @interface Roles {
    Role[] value();
}

@Repeatable(Roles.class)
public @interface Role {
    String roleName();
}

public class RoleTest {
    @Role(roleName = "role1")
    @Role(roleName = "role2")
    public String doString(){
        return "";
    }
}
```
不同的地方是，创建重复注解 Role 时加上了 @Repeatable 注解，指向存储注解 Roles，这样在使用时就可以直接重复使用 Role 注解。从上面例子看出，使用 @Repeatable 注解更符合常规思维，可读性强一点。

两种方法获得的效果相同。重复注解只是一种简化写法，这种简化写法是一种假象，多个重复注解其实会被作为“容器”注解的 value 成员的数组元素处理。

## 3 Spring Annotation

### 3.1 @SpringBootApplication

```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
    excludeFilters = {@Filter(
    type = FilterType.CUSTOM,
    classes = {TypeExcludeFilter.class}
), @Filter(
    type = FilterType.CUSTOM,
    classes = {AutoConfigurationExcludeFilter.class}
)}
)
public @interface SpringBootApplication {
  ...
}
```
springboot是通过注解@EnableAutoConfiguration的方式，去查找，过滤，加载所需的configuration, @ComponentScan扫描我们自定义的bean,@SpringBootConfiguration使得被@SpringBootApplication注解的类声明为注解类．因此@SpringBootApplication的作用等价于同时组合使用@EnableAutoConfiguration，@ComponentScan，@SpringBootConfiguration．

### 3.2 @SpringBootConfiguration

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
public @interface SpringBootConfiguration {
  ...
}
```

### 3.3 @EnableAutoConfiguration

注解启用自动配置，其可以帮助 SpringBoot 应用将所有符合条件的 @Configuration 配置都加载到当前 IoC 容器之中。

```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfigurationPackage
@Import({AutoConfigurationImportSelector.class})
public @interface EnableAutoConfiguration {
  String ENABLED_OVERRIDE_PROPERTY = "spring.boot.enableautoconfiguration";
  ...
}

```
### @Import

### 3.2 @Profile

### 3.3 @ConditionalOnProperty

### 3.4 @Qualifier

### 3.5 @Autowired

#### 1.4.1 构造器注入

```java
@Autowired
private User user;
private String school;


public UserAccountServiceImpl(){
  this.school = user.getSchool();
}
```

Java类会先执行构造方法，然后再给注解了@Autowired 的user注入值，所以在执行构造方法的时候，就会报错。

Java类的初始化的顺序: 静态变量或静态语句块–>实例变量或初始化语句块–>构造方法–>@Autowired

所以推荐使用构造器注入

```java
private User user;
private String school;

@Autowired
public UserAccountServiceImpl(User user) {
  this.school = user.getSchool();
}
```

如果bean作为一个单例模式来使用，默认为单例，建议在bean的声明上加final，如

```java
private final EnterpriseDbService service;

@Autowired
public EnterpriseDbController(EnterpriseDbService service) {
  this.service = service;
}
```


@Scope

@Component

@Configuration

Spring 配置类


### @Bean

@Bean是一个方法级别上的注解，主要用在@Configuration注解的类里，也可以用在@Component注解的类里。

@Repository org.springframework.stereotype.Repository

@PersistenceContext

@PostConstruct

@PreDestroy

### @Transactional

- 默认设置，是只会回滚运行时异常或error
- 可以注解在类、类方法、接口、接口方法上，但是不推荐注解在接口和接口方法上。另一方面得注解在public方法上，注解在private和protected方法上就是在内部调用的过程中是不会抛出异常的

#### propagation属性

事务的传播行为

---

## Spring Bean的完整生命周期

1. instantiate bean对象实例化
2. populate properties 封装属性
3. 如果Bean实现BeanNameAware 执行 setBeanName
4. 如果Bean实现BeanFactoryAware 或者 ApplicationContextAware 设置工厂 setBeanFactory 或者上下文对象 setApplicationContext
5. 如果存在类实现 BeanPostProcessor（后处理Bean） ，执行postProcessBeforeInitialization，BeanPostProcessor接口提供钩子函数，用来动态扩展修改Bean。(程序自动调用后处理Bean)
6. 如果Bean实现InitializingBean 执行 afterPropertiesSet
7. 调用 **\<bean init-method="init">** 指定初始化方法 init
8. 如果存在类实现 BeanPostProcessor（处理Bean） ，执行postProcessAfterInitialization
9. 执行业务处理
10. 如果Bean实现 DisposableBean 执行 destroy
11. 调用 **\<bean destroy-method="customerDestroy">** 指定销毁方法 customerDestroy

---

```word
Class.getResourceAsStream(String path)

```