package com.huang.datastruct.map;

import java.util.HashMap;
import java.util.Objects;

/**
 * @auth： i325805
 */
public class ConstantObject {
    private int value;

    public ConstantObject(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        return true;
    }

//    @Override
//    public int hashCode() {
//        return 1;
//    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        ConstantObject that = (ConstantObject) o;
//        return value == that.value;
//    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public int[] sortedSquares(int[] nums) {

        int length = nums.length;
        int[] result = new int[length];

        for(int i=0, j=length-1, index=length-1; i<=j; index--) {
            if(nums[i]*nums[i] > nums[j]*nums[j]){
                result[index] = nums[i] * nums[i];
                i++;
            } else {
                result[index] = nums[j]*nums[j];
                j--;
            }

        }

        return result;
    }

    public static void main(String[] args) {
//        ConstantObject o1 = new ConstantObject(1);
//        ConstantObject o2 = new ConstantObject(2);
//        ConstantObject o3 = new ConstantObject(3);
//        ConstantObject o4 = new ConstantObject(4);
//
//        HashMap<ConstantObject, Integer> arr = new HashMap<>();
//        arr.put(o1, 1);
//        arr.put(o2, 2);
//        arr.put(o3, 3);
//        arr.put(o4, 4);
//
//        System.out.println(arr.get(o1));

        int[] input  = {-7,-3,2,3,11};
        ConstantObject o1 = new ConstantObject(1);
        System.out.println(o1.sortedSquares(input));
    }
}
