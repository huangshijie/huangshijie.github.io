# LeetCode

## 1. 常见问题

## 2. 通用方法

### 2.1 数组操作方法

1. Arrays.copyOfRange 
方法返回值是int[]，但是不知道数组长度，可以先初始化一个比较长的数组，但是因为初始化之后，没有用到的位置也会被初始化，默认为0，然后可以用 Arrays.copyOfRange 来截取需要的部分，来返回结果数组

2. 交换数组根据数组长度

想要根据数组长度，比如优先处理长度比较小的，不用创建两个新的数组，可以调用自身，然后在传入参数上做文章

```java
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        ...
    }
}
```

### 2.2 字符串操作方法

## 3. 常见算法

### 3.1 深度优先算法 DFS


### 3.2 广度优先算法 BFS

### 双指针

#### 两个字符串做比较

