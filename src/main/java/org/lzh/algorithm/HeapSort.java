package com.lzh.algorithm;

/**
 * 堆排序
 *
 * @author lzh
 * @create 2016-12-20 14:35
 */
public class HeapSort {
    /**
     * 树节点调整，父亲节点、左孩子、右孩子，将最大放置父亲节点
     * @param a
     * @param start
     * @param end
     */
    private void maxHeapDown(int[] a, int start, int end){
        int current = start;
        int left = 2*current + 1;
        int temp = a[start];
        //从current节点依次遍历子节点完成节点调整
        for(;left < end; current = left, left = 2*left + 1){
            //左节点小于右节点，当前节点转换至右节点
            if(left < end && a[left] < a[left + 1]){
                left++;
            }
            if(temp >= a[left]){
                break;
            }
            else {
                a[current] = a[left];
                a[left] = temp;
            }
        }
    }

    private void heapSortAsc(int[] a, int size){
        int i,tmp;
        for(i = (size-1)/2;i >= 0;i--){
            maxHeapDown(a, i, size - 1);
        }

        for(i = size - 1; i>= 0; i--){
            tmp = a[0];
            a[0] = a[i];
            a[i] = tmp;
            maxHeapDown(a, 0, i - 1);
        }
    }

    public static void main(String[] args) {
        int[] test = {5,13,8,0,1,4,2,20,6,4,15,2,12,7,8,5,29,1,43,27,28,3,19,61,42,39,33,7,1,197,78,75,2,33};
        HeapSort heapSort = new HeapSort();
        heapSort.heapSortAsc(test, test.length);
        int i;
        for(i = 0; i < test.length; i++){
            System.out.print(test[i] + ",");
        }
    }
}
