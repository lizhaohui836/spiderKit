package org.lzh.algorithm;

import org.junit.Before;
import org.junit.Test;

/**
 * 最小堆测试
 *
 * @author lzh
 * @create 2017-01-08 16:27
 */
public class MinHeapTest {
    private BinaryHeap<Integer> minHeap = new MinHeap<>();

    @Before
    public void init(){
        int[] arrays = {5,8,4,1,9,3,2,7,12};
        for(int i : arrays){
            minHeap.insert(i);
        }
    }
    @Test
    public void insert(){
        System.out.println("before insert minHeap to String: " + minHeap.toString());
        minHeap.insert(6);
        System.out.println("after insert minHeap to String: " + minHeap.toString());
    }

    @Test
    public void remove(){
        System.out.println("before remove minHeap to String: " + minHeap.toString());
        minHeap.remove(1);
        System.out.println("after remove minHeap to String: " + minHeap.toString());
    }
}
