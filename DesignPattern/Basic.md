面向对象6大设计原则

1、开闭原则（Open Close Principle）

开闭原则就是说对扩展开放，对修改关闭。在程序需要进行拓展的时候，不能去修改原有的代码，实现一个热插拔的效果。

简单来说：就是为了使程序的扩展性好，易于维护和升级。

2、接口隔离原则（Interface Segregation Principle）

这个原则的意思是：使用多个隔离的接口，比使用单个接口要好。

还是一个降低类之间的耦合度的意思，从这儿我们看出，其实设计模式就是一个软件的设计思想，从大型软件架构出发，为了升级和维护方便。在开发过程当中回尽量地去降低依赖，降低耦合。

3、迪米特法则（Demeter Principle）

一个实体应当尽量少的与其他实体之间发生相互作用，使得系统功能模块相对独立。

4、单一职责原则（Single Responsibility Principle）

单一职责的定义:应该有且只有一个原因引起类的变更。换句话说就是一个接口只做一件事，即一个职责一个接口。

但是困难的是划分职责时并没有一个标准，最终都是需要从实际的项目去考虑。我们在设计的时候，尽量单一，然后对于其实现类就要多方面的考虑。

不能死套单一职责原则，否则会增加很多类，给维护带来不便。

5、里氏代换原则（Liskov Substitution Principle）

里氏代换原则(Liskov Substitution Principle LSP)面向对象设计的基本原则之一。 里氏代换原则中说，任何基类可以出现的地方，子类一定可以出现。

 LSP是继承复用的基石，只有当衍生类可以替换掉基类，软件单位的功能不受到影响时，基类才能真正被复用，而衍生类也能够在基类的基础上增加新的行为。

里氏代换原则是对“开-闭”原则的补充。实现“开-闭”原则的关键步骤就是抽象化。而基类与子类的继承关系就是抽象化的具体实现，所以里氏代换原则是对实现抽象化的具体步骤的规范。

6、依赖倒转原则（Dependence Inversion Principle）

这个是开闭原则的基础，具体内容：真对接口编程，依赖于抽象而不依赖于具体。

java中抽象指接口或抽象类，两者都不能直接被实例化的；细节就是实现类，实现接口或者集成抽象类而产生的也就细节，也就是可以可以加上yige 关键字new产生的对象。高层模块就是调用端，低层模块就是具体实现类。

依赖倒置原则在java中表现就是，模块间依赖通过抽象发生，实现类之间不发生直接依赖关系，其依赖关系是通过接口或者抽象类产生的。如果类与类直接依赖细节，那么就会直接耦合。如此一来当修改时，就会同时修改依赖者代码，这样限制了可拓展性。