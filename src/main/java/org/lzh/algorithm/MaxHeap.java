package org.lzh.algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 最大堆
 *
 * @author lzh
 * @create 2017-01-08 16:47
 */
public class MaxHeap<T extends Comparable<T>> implements BinaryHeap<T> {
    private List<T> maxHeap;
    MaxHeap(){
        this.maxHeap = new ArrayList<T>();
    }
    MaxHeap(Collection<T> collection){
        Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()){
            insert(iterator.next());
        }
    }
    /**
     * 向上调整，插入时从末尾开始
     * @param start
     */
    private void filterUp(int start){
        if(start < 0){
            System.err.println("索引错误");
        }
        if(start == 0){
            return;
        }
        int current = start;
        int parrent = (current - 1)/2;
        T tmp = maxHeap.get(current);
        //直到current=0还未找到合适结点，退出循环
        while (current > 0){
            int cmp = tmp.compareTo(maxHeap.get(parrent));
            if(cmp > 0){
                maxHeap.set(current, maxHeap.get(parrent));
                current = parrent;
                parrent = (current - 1)/2;
            }
            else {
                break;
            }
        }
        maxHeap.set(current, tmp);
    }

    /**
     * 向下调整，一般删除元素时
     * @param start
     * @param end
     */
    private void filterDown(int start, int end){
        if(start > end){
            System.err.println("参数异常");
            return;
        }
        int current = start;
        int left = 2*current + 1;
        T tmp = maxHeap.get(current);
        //left + 1不能超过end
        while (left + 1 <= end && current <= end){
            if(maxHeap.get(left + 1).compareTo(maxHeap.get(left)) > 0){
                //左右孩子选较大
                left++;
            }
            int cmp = tmp.compareTo(maxHeap.get(left));
            if(cmp < 0){
                maxHeap.set(current, maxHeap.get(left));
                current = left;
                left = 2*current + 1;
            }
            else {
                break;
            }
        }
        maxHeap.set(current, tmp);
    }
    @Override
    public void insert(T data) {
        int size = maxHeap.size();
        maxHeap.add(data);
        filterUp(size);
    }

    @Override
    public int remove(T data) {
        if(maxHeap.isEmpty()){
            return -1;
        }
        int index = maxHeap.indexOf(data);
        if(index < 0){
            return -1;
        }
        int size = maxHeap.size();
        maxHeap.set(index, maxHeap.get(size - 1));
        maxHeap.remove(size - 1);
        if(maxHeap.size() > 1){
            filterDown(index, maxHeap.size() - 1);
        }
        return 0;
    }

    @Override
    public void destroy(){
        maxHeap.clear();
    }

    @Override
    public T poll() {
        T firstData = maxHeap.get(0);
        remove(firstData);
        return firstData;
    }

    public String toString(){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < maxHeap.size(); i++){
            sb.append(maxHeap.get(i) + " ");
        }
        return sb.toString();
    }
}
