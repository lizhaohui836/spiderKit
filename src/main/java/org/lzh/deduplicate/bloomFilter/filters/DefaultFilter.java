package org.lzh.deduplicate.bloomFilter.filters;

import org.lzh.deduplicate.bloomFilter.iface.Filter;

/**
 * 默认过滤器
 *
 * @author BFD_499
 * @create 2016-11-01 16:56
 */
public class DefaultFilter extends Filter {

    public DefaultFilter(long size){
        super(size);
    }
    @Override
    public long myHashCode(String str) {
        long hash = 0;

        for (int i = 0; i < str.length(); i++) {
            hash = 31 * hash + str.charAt(i);
        }

        if(hash<0){
            hash *= -1 ;
        }

        return hash % size;
    }
}
