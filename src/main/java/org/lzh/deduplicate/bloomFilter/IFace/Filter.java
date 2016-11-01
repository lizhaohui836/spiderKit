package org.lzh.deduplicate.bloomFilter.iface;

import org.lzh.deduplicate.bloomFilter.IntBitMap;

/**
 * Filter抽象类
 *
 * @author BFD_499
 * @create 2016-11-01 16:48
 */
public abstract class Filter {
    protected long size;
    protected BitMap bmp;

    public Filter(long size){
        this.size = size;
        bmp = new IntBitMap( (int) size/32);
    }

    /**
     * 为某过滤器增加字符串
     * @param str
     */
    public void add(String str){
        long code = this.myHashCode(str);
        bmp.add(code);
    }

    /**
     * 判断某过滤器是否包含字符串
     * @param str
     * @return
     */
    public boolean contains(String str){
        long code = this.myHashCode(str);
        return bmp.contains(code);
    }
    /**
     * 根据不同的实现，返回各自的hashcode
     * @param str
     * @return
     */
    public abstract long myHashCode(String str);
}
