# Container

问题是：
Spring能不能把Controller层注入到Service层

父容器Spring Context加载Service和Dao，子容器Spring MVC 加载Controller，所以就有父子容器的问题。

而Spring boot就一个容器，不存在父子容器

Context

org.springframework.context.ApplicationContext接口用于完成容器的配置，初始化，管理bean。一个Spring容器就是某个实现了ApplicationContext接口的类的实例。也就是说，从代码层面，Spring容器其实就是一个ApplicationContext。

在普通的JAVA工程中，我们可以通过代码显式new一个ClassPathXmlApplicationContext或者FileSystemXmlApplicationContext来初始化一个Spring容器。

1. Spring BeanFactory 容器
它是最简单的容器，给 DI 提供了基本的支持，它用 org.springframework.beans.factory.BeanFactory 接口来定义。BeanFactory 或者相关的接口，如 BeanFactoryAware，InitializingBean，DisposableBean，在 Spring 中仍然存在具有大量的与 Spring 整合的第三方框架的反向兼容性的目的。

2. Spring ApplicationContext 容器
该容器添加了更多的企业特定的功能，例如从一个属性文件中解析文本信息的能力，发布应用程序事件给感兴趣的事件监听器的能力。该容器是由 org.springframework.context.ApplicationContext 接口定义。
