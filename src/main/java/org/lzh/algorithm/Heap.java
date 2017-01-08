package org.lzh.algorithm;

/**
 * Created by admin on 2017/1/8.
 */
public interface Heap<T> {
    public void insert(T data);
    public int remove(T data);
    public void destroy(); //销毁堆
    public T poll(); //弹出堆顶元素
}
