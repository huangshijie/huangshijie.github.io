# GC

## WHAT
Garbage Collection
- 收集算法是内存回收的方法论，理论依据。
- 垃圾收集器是内存回收的具体实现，依据不同的算法实现不同的垃圾回收器，例如商用的G1收集器。
  
  ![Garbage Collection in HotSpot VM](https://github.com/huangshijie/ImgRep/blob/master/Garbage%20Collection%20in%20HotSpot.jpg)

### 基本配置参数
- -Xmx 设置最大堆
- -XX:SurvivorRatio
- -XX:PretenureSizeThreshold
- -XX:HandlePromotionFailure
- -XX:+UseConcMarkSweepGC
- -XX:+UseParNewGC
- -XX:ParallelGCThreads
- -XX:MaxGCPauseMillis
- -XX:GCTimeRatio

### 收集算法
- 标记-清除算法
- 复制算法
- 标记-整理算法
- 分代收集算法

### 垃圾收集器
**HotSpot虚拟机**实现了七个收集器，分别用于不同的场合
- Serial Collector
  - 复制算法
  - Single thread，简单高效
  - 针对新生代
  - 运行在**Client模式**下的虚拟机比较好的选择
  - 在进行垃圾收集时，必须暂停其他所有的工作线程，直到收集结束
  - Stop The World(STW)
- ParNew Collector
  - Serial Collector的多线程版，除了多线程，其他行为、特点和Serial收集器一样
  - 运行在**Server模式**下的虚拟机中首选的**新生代**收集器
  - 由于有线程交互的开销，在单CPU的情况下，效率绝对没有Serial Collector高
  - **只有它能与CMS收集器配合工作**
- Parallel Scavenge Collector
  - 新生代收集器，吞吐量优先收集器
  - 并行的多线程收集器
  - 复制算法
  - 可控的吞吐量，吞吐量=运行用户代码时间/(运行用户时间+垃圾收集时间)，停顿时间越短越适合需要与用户交互的程序，良好的响应速度能提升用户体验。而高吞吐量可以高效的利用CPU时间，尽快完成程序的运算任务，主要适合在后台运算不需要太多交互的任务。
- Serial Old Collector
  - Serial收集器的老年代版本
  - 单线程
  - 标记-整理算法
  - 给Client模式下的虚拟机使用
- Parallel Old Collector
  - Parallel Scavenge的老年代版本
  - 多线程
  - 标记-整理算法
- Concurrent Mark Sweep Collector (CMS)
  - 标记-清除算法
- Garbage-First Garbage Collector 
  - reference link:
  [The Garbage-First Garbage Collector](https://www.oracle.com/technetwork/java/javase/tech/g1-intro-jsp-135488.html)
