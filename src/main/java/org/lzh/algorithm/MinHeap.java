package org.lzh.algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 最小堆，以数组实现的堆
 * 第N节点的左孩子为(2N+1),右孩子为(2N+2),父节点为((N-1)/2)
 *
 * @author lzh
 * @create 2017-01-08 14:49
 */
public class MinHeap<T extends Comparable<T>> implements Heap<T> {
    private List<T> minHeap;
    MinHeap(){
        this.minHeap = new ArrayList<T>();
    }

    MinHeap(Collection<T> collection){
        Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()){
            insert(iterator.next());
        }
    }

    /**
     * 插入时，一般从尾部向上调整
     * @param start
     */
    private void filterUp(int start){
        if(start < 0){
            System.err.println("索引错误");
            return;
        }
        if(start == 0){
            return;
        }
        int cIndex =  start;
        int pIndex = (cIndex  - 1)/2;
        T tmp = minHeap.get(cIndex);
        //直到current=0还未找到合适结点，退出循环
        while (cIndex > 0){
            int cmp = minHeap.get(pIndex).compareTo(tmp);
            if(cmp <= 0){
                break;
            }
            else {
                minHeap.set(cIndex, minHeap.get(pIndex));
                cIndex = pIndex;
                pIndex = (pIndex  - 1)/2;
            }
        }
        minHeap.set(cIndex, tmp);
    }

    /**
     * 从start到end向下调整
     * @param start
     * @param end
     */
    private void filterDown(int start, int end){
        if(start > end){
            System.err.println("参数异常");
            return;
        }
        int cIndex = start;
        int lIndex = 2*start + 1;
        T tmp = minHeap.get(cIndex);
        //lIndex + 1不能超过end
        while (cIndex <= end && lIndex + 1 <= end){
            if(minHeap.get(lIndex).compareTo(minHeap.get(lIndex + 1)) > 0){
                //左右孩子选较小的
                lIndex++;
            }
            if(tmp.compareTo(minHeap.get(lIndex)) > 0){
                minHeap.set(cIndex, minHeap.get(lIndex));
                cIndex = lIndex;
                lIndex = 2*cIndex + 1;
            }
            else {
                break;
            }
        }
        minHeap.set(cIndex, tmp);
    }
    @Override
    public void insert(T data) {
        minHeap.add(data);
        filterUp(minHeap.size() - 1);
    }

    @Override
    public int remove(T data) {
        if(minHeap.isEmpty()){
            return -1;
        }
        int index = minHeap.indexOf(data);
        if(index < 0){
            return -1;
        }
        int size = minHeap.size();
        minHeap.set(index, minHeap.get(size - 1));
        minHeap.remove(size - 1);
        if(minHeap.size() > 1){
            filterDown(index, minHeap.size() - 1);
        }
        return 0;
    }

    @Override
    public void destroy(){
        minHeap.clear();
    }
    @Override
    public T poll() {
        T firstData = minHeap.get(0);
        remove(firstData);
        return firstData;
    }

    public String toString(){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < minHeap.size(); i++){
            sb.append(minHeap.get(i) + " ");
        }
        return sb.toString();
    }
}
