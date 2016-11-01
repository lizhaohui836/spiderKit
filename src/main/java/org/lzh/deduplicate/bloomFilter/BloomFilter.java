package org.lzh.deduplicate.bloomFilter;

import org.lzh.deduplicate.bloomFilter.filters.*;
import org.lzh.deduplicate.bloomFilter.iface.Filter;

/**
 * 布隆过滤器
 *
 * @author BFD_499
 * @create 2016-11-01 16:59
 */
public class BloomFilter {
    private int length;
    private Filter[] filters;
    /**
     * 实例化对象，mb代表要申请多少兆的bitmap，1Mb=1*8*1024*1024个位
     * 默认采用5个hash函数
     * @param mb
     */
    public BloomFilter(int mb) throws Exception {
        length = 5;
        filters = new Filter[length];
        //确定每个hash申请多少位
        float mbNum = mb / 5;
        long size = (long) (1L * mbNum * 1024 * 1024 * 8);
        filters[0] = new JavaFilter(size);
        filters[1] = new PHPFilter(size);
        filters[2] = new JSFilter(size);
        filters[3] = new PJWFilter(size);
        filters[4] = new SDBMFilter(size);
    }

    public void add(String str){
        for(Filter filter : filters){
            filter.add(str);
        }
    }

    public boolean contains(String str){
        for(Filter filter : filters){
            if(filter.contains(str) == false){
                return false;
            }
        }
        return true;
    }
}
