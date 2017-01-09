package org.lzh.algorithm;

/**
 * 二叉堆
 */
public interface BinaryHeap<T> {
    public void insert(T data);
    public int remove(T data);
    public void destroy(); //销毁堆
    public T poll(); //弹出堆顶元素
}
