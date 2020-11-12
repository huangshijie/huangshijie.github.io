package com.huang.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : I325805
 * @description:
 */
public class LeetCode349 {
    public void quickSort(int[] arr,int low,int high) {
        int i,j,temp,t;
        if(low>high){
            return;
        }                                                                                                                                                  i=low;
        j=high;
        //temp就是基准位
        temp = arr[low];
        while (i<j){
            //先看右边，依次往左递减
            while (temp<=arr[j]&&i<j){
                j--;
            }
            //再看左边，依次往右递增
            while (temp>=arr[i]&&i<j){
                i++;
            }

            //如果满足条件则交换
            if (i<j){
                t=arr[j];
                arr[j]=arr[i];
                arr[i]=t;
            }
        }
        //最后将基准为与i和j相等位置的数字交换
        arr[low] = arr[i];
        arr[i] = temp;

        //递归调用左半数组
        quickSort(arr, low, j-1);
        //递归调用右半数组
        quickSort(arr, j+1, high);
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        quickSort(nums1, 0, nums1.length-1);
        quickSort(nums2, 0, nums2.length-1);
        Set<Integer> result = new HashSet<>();

        for (int i = 0, j = 0; i < nums1.length && j < nums2.length; ) {
            if (nums1[i] == nums2[j]) {
                result.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            }
        }

        int i=0;
        int[] arr = new int[result.size()];
        for(Integer num:result){
            arr[i]= num;
            i++;
        }
        return arr;
    }
}
