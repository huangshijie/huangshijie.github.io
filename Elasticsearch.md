# Elasticsearch

## Common terms

- cluster

- node

- shard

在 Elasticsearch 中文档是 不可改变 的，不能修改它们。 相反，如果想要更新现有的文档，需要 重建索引 或者进行替换， 我们可以使用相同的 index API 进行实现，在 索引文档 中已经进行了讨论。update API 必须遵循同样的规则。 从外部来看，我们在一个文档的某个位置进行部分更新。然而在内部， update API 简单使用与之前描述相同的 检索-修改-重建索引 的处理过程。

_index 、 _type 和 _id 的组合可以唯一标识一个文档。

elasticsearch 控制并发
> 乐观并发控制

---

bulk
> bulk API 需要有换行符的有趣格式, 使用换行符字符来识别和解析小的 action/metadata 行来决定哪个分片应该处理每个请求。
---

mget

---

路由

```groovy
shard = hash(routing) % number_of_primary_shards
```

---

## 搜索

当索引一个文档的时候，Elasticsearch 取出所有字段的值拼接成一个大的字符串，作为 _all 字段进行索引。

- 映射（Mapping）
  描述数据在每个字段内如何存储

- 分析（Analysis）
  全文是如何处理使之可以被搜索的

- 领域特定查询语言（Query DSL）
  Elasticsearch 中强大灵活的查询语言

深度分页？？？
在分布式系统中，对结果排序的成本随分页的深度成指数上升。这就是 web 搜索引擎对任何查询都不要返回超过 1000 个结果的原因。

### 倒排索引

[Elasticsearch－基础介绍及索引原理分析](https://www.cnblogs.com/dreamroute/p/8484457.html)
[时间序列数据库的秘密 (2)——索引](https://www.infoq.cn/article/database-timestamp-02?utm_source=infoq&utm_medium=related_content_link&utm_campaign=relatedContent_articles_clk)

### mapping

### 分析