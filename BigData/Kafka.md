# Kafka

## Consumer

## Producer

## Kafka事务


## Kafka面试题

1. 为什么使用Kafka？
在我们的场景里，一方面是为了解耦，放的时候只管将所有的消息都往kafka的topic里放，然后会有专门的线程管理consumer去消费topic里的message。另一方面，是为了让程序更加健壮，之前因为生成swagger需要很长的时间，现在的话，可以让他慢慢生成，model先创建出来，或者先处理其他的逻辑

2. 