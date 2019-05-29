# Spring

## Spring Annotation

@Profile

@ConditionalOnProperty

### @Qualifier

---

### @Autowired

#### 构造器注入

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

---

@Scope

@Component

@Configuration

---

### @Bean

@Bean是一个方法级别上的注解，主要用在@Configuration注解的类里，也可以用在@Component注解的类里。

@Repository org.springframework.stereotype.Repository

@Retention

@Target

@PersistenceContext

@PostConstruct

@PreDestroy

---

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