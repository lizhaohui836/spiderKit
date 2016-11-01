package org.lzh.deduplicate.bloomFilter;

import org.lzh.deduplicate.bloomFilter.iface.BitMap;

/**
 * 实现32位的bitmap
 *
 * @author BFD_499
 * @create 2016-11-01 16:18
 */
public class IntBitMap implements BitMap {
    private static final int MAX = Integer.MAX_VALUE;
    private static final int DEFAULT_SIZE = 93750000;
    private int[] BITS; //int数组，二维数组中的int占4字节32位

    public IntBitMap(){
        BITS = new int[DEFAULT_SIZE];
    }
    public IntBitMap(int size){
        BITS = new int[size];
    }

    public void add(long i) {
        //找出i对应bitmap中需要置1的位置,BITS[r][j]
        int r = (int) (i/BIT32);
        int j = (int) (i%BIT32);
        //对BITS[r]上的第j位做置1操作
        //1左移j位，与数字做|操作
        BITS[r] = (int) BITS[r] | (1 << j);
    }

    public boolean contains(long i) {
        //找出i对应bitmap中需要置1的位置,BITS[r][j]
        int r = (int) (i/BIT32);
        int j = (int) (i%BIT32);
        //判断BITS[r]上的第j位是否为1
        //数字无符号右移j位，与数字1做&操作，如果结果为1，则该位置为1；否则为0
        if((int) ((BITS[r] >>> j) & 1) == 1){
            return true;
        }
        return false;
    }
}
