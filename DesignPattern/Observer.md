发布订阅模式 Subject&Observer

对象之间定义一对多的依赖，当一个对象改变状态，其他依赖他的对象都会收到通知并自动更新

>**通俗说法**
>被观察者(Subject)，内部维护一个观察者(Observer)表.
>同时Observer对象内部也有一个指向Subject的引用
>Observer注册观察Subject，就是在Subject的表中add该Observer对象。
>当Subject状态有变化之后，调用Subject内部通知方法，即遍历Observer表，调用每个Observer的相应方法。
>
>当Observer取消观察Subject，则将该Observer对象从Subject内部表中移去。

