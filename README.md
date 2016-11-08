# spiderKit

=========

该项目封装了抓取可能用到的组件包，包含算法、工具类

## SimHash
  一种局部敏感的hash算法，将高纬的特征向量降维到固定个Bit中(默认64)，通过计算汉明距离来判断
两篇文章的相似度。

- 已实现
  1. 文章分词，依赖nlp-lang的开源包
  2. 生成指纹
- 未实现
  1. 带权重的分词
  2. 分词未自己实现

## 布隆过滤器

- 已实现
  1. 默认采用5个不同hash算法，且所有hash算法从网上复用
  2. BitMap暂采用int数组，即32位的位图
  3. 构造布隆过滤器时，传入参数为申请多大内存用于bitmap的存储，单位为MB，传入大于5的整数
- 未实现（需改进）
  1. hash算法未做深入研究，其中原理模糊
  2. 该布隆过滤器为普通版本，暂未实现Counting Blomm Filter。CBF是在普通布隆过滤器的升级，即将
  bit位扩展为一个计数器，在插入元素时不仅仅置为1，而是进行+1操作，删除元素时进行-1操作。这样弥补
  了普通布隆过滤器无法删除元素的确定，但同时需要更多的空间。为每个Counter分配4位即能满足一般要求。

## TrieTree（字典树/前缀树）

- 已实现
  1. 
- 未实现
  1. 双数组字典树未完成