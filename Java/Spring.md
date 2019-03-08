@Profile

@Autowired

@Component

@Repository org.springframework.stereotype.Repository

@EnableCaching
---

**Bean的完整生命周期**
1. instantiate bean对象实例化
2. populate properties 封装属性
3. 如果Bean实现BeanNameAware 执行 setBeanName
4. 如果Bean实现BeanFactoryAware 或者 ApplicationContextAware 设置工厂 setBeanFactory 或者上下文对象 setApplicationContext
5. 如果存在类实现 BeanPostProcessor（后处理Bean） ，执行postProcessBeforeInitialization，BeanPostProcessor接口提供钩子函数，用来动态扩展修改Bean。(程序自动调用后处理Bean)
6. 如果Bean实现InitializingBean 执行 afterPropertiesSet
7. 调用<bean init-method="init"> 指定初始化方法 init
8. 如果存在类实现 BeanPostProcessor（处理Bean） ，执行postProcessAfterInitialization
9. 执行业务处理
10. 如果Bean实现 DisposableBean 执行 destroy
11. 调用<bean destroy-method="customerDestroy"> 指定销毁方法 customerDestroy

---

# IoC 控制反转

## 

将`


## banner
就是启动spring application 会出现的spring，可以关掉或者替换


## Customizing SpringApplication

## SpringApplicationBuilder
可以用来构造流构造器，或者有继承关系的

## Application Events Listeners

# Externalized Configuration



```
Spring 对象是不是线程安全的？

对象是通过反射new出来的，然后被保存在容器中
默认是单例bean
Spring容器只是实现了一个控制反转，并没有对Bean的特性进行一个改变
Spring中的bean是否安全，与ioc容器没有关系，取决于bean的本身
```

```
Spring bean是怎么被回收的？

也要分情况的，与Spring的生命周期有关

单例模式下：
所有的bean都被实例化放入Spring容器的map中
Spring中的单例对象，都不被回收，如果要回收只要将bean这个对象的引用置位null
```

HandlerMapping