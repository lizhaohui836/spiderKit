package org.lzh.deduplicate.bloomFilter.iface;

/**
 * 位图数据结构，定义add 与 contains方法
 * Created by BFD_499 on 2016/11/1.
 */
public interface BitMap {

    public static final int BIT32 = 32;
    public static final int BIT64 = 64;
    public void add(long i);
    public boolean contains(long i);

}
