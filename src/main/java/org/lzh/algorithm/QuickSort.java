package org.lzh.algorithm;

/**
 * 快速排序
 *
 * @author lzh
 * @create 2016-12-20 10:43
 */
public class QuickSort {
    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] test = {5,13,8,0,1,4,2,20,6,4,15,2,12,7,8,5,29,1,43,27,28,3,19,61,42,39,33,7,1,197,78,75,2,33};
        quickSort.quickSort(test, 0, test.length-1);
        printArr(test);
    }
    private void quickSort(int[] a, int left, int right){
        if(left < right){
            int temp = a[left];
            int i = left;
            int j = right;
            while (i < j){
                //用j从后往前，只要temp<=a[j]，j一直往左
                while (i < j && temp < a[j]){
                    j--;
                }
                //temp>a[j],把a[j]值填入i坑里，坑往后移,i++
                if(i < j){
                    a[i] = a[j];
                    i++;
                }
                //与第一种操作相反
                while (i < j && temp > a[i]){
                    i++;
                }
                if(i < j){
                    a[j] = a[i];
                    j--;
                }
            }
            //最后把剩下的i坑填入最初的temp值，此时，temp左边都比他小，右边都比他大
            a[i] = temp;
            //递归
            quickSort(a, left, i -1 );
            quickSort(a, i + 1, right);
        }
    }
    private static void printArr(int[] arr){
        int len = arr.length;
        for(int i = 0; i < len; i++){
            System.out.print(arr[i] + ",");
        }
        System.out.println();
    }
}
