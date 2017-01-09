package org.lzh.algorithm;

import org.junit.Before;
import org.junit.Test;

/**
 * 最大堆测试
 *
 * @author lzh
 * @create 2017-01-08 17:24
 */
public class MaxHeapTest {
    private BinaryHeap<Integer> maxHeap = new MaxHeap<>();

    @Before
    public void init(){
        int[] arrays = {5,8,4,1,9,3,2,7,12};
        for(int i : arrays){
            maxHeap.insert(i);
        }
    }
    @Test
    public void insert(){
        System.out.println("before insert maxHeap to String: " + maxHeap.toString());
        maxHeap.insert(6);
        System.out.println("after insert maxHeap to String: " + maxHeap.toString());
    }

    @Test
    public void remove(){
        System.out.println("before remove maxHeap to String: " + maxHeap.toString());
        maxHeap.remove(12);
        System.out.println("after remove maxHeap to String: " + maxHeap.toString());
    }
}
