package org.lzh.algorithm;

/**
 * 归并排序
 *
 * @author lzh
 * @create 2016-12-20 18:51
 */
public class MergeSort {
    private void mergeSortUp2Down(int[] a, int start, int end){
        if(a == null || start > end)
            return;
        if(start < end){
            int mid = (start + end)/2;
            mergeSortUp2Down(a, start, mid);
            mergeSortUp2Down(a, mid + 1, end);
            //a[start....mid] 和 a[mid.....end] 为两个有序数组
            //将这两个有序数组合并
            merge(a, start, mid, end);
        }

    }

    /**
     * 将一个数组中的两个相邻有序区间合并成一个
     * @param a 包含两个有序区间的数组
     * @param start 第1个有序区间的起始地址。
     * @param mid 第1个有序区间的结束地址。也是第2个有序区间的起始地址。
     * @param end 第2个有序区间的结束地址。
     */
    private void merge(int[] a, int start, int mid, int end) {
        //临时存储的目标数组
        int[] tmp = new int[end - start + 1];
        int i = start;  //第一个数组的起始索引
        int j = mid + 1;  //第二个数组的起始索引
        int k = 0;   //tmp的索引
        while (i <= mid && j <= end){
            if(a[i] <= a[j]){
                tmp[k++] = a[i++];
            }
            else{
                tmp[k++] = a[j++];
            }
        }
        //第一个数组中剩余，第二个已完；将第一个数组剩余添加至tmp
        while (i <= mid){
            tmp[k++] = a[i++];
        }
        //第二个数组剩余
        while (j <= end){
            tmp[k++] = a[j++];
        }
        //将tmp整合至a
        for(i = 0; i < k; i++){
            a[start + i] = tmp[i];
        }
        tmp = null;
    }

    private void printArr(int[] arr){
        int len = arr.length;
        for(int i = 0; i < len; i++){
            System.out.print(arr[i] + ",");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] test = {5,13,8,0,1,4,2,20,6,4,15,2,12,7,8,5,29,1,43,27,28,3,19,61,42,39,33,7,1,197,78,75,2,33};
        mergeSort.mergeSortUp2Down(test, 0, test.length - 1);
        mergeSort.printArr(test);
    }
}
