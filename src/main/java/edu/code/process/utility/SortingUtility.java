package edu.code.process.utility;

import java.util.Arrays;

public class SortingUtility {

	public int[] mergesortArray(int[] nums) {
        if (nums == null || nums.length < 2)
            return nums;
        
        return mergesort(nums, 0, nums.length-1);
    }
    
    private int[] mergesort(int[] nums, int start, int end){
        if (start == end)
            return Arrays.copyOfRange(nums, start, start+1);
        int length = end - start + 1;
        int m = length/2;
        int[] leftArray = mergesort(nums, start, start+m-1); 
        int[] rightArray = mergesort(nums, start+m, end);
        int[] result = new int[length];
        int i=0;
        int j=0;
        int index = 0;
        while(i<leftArray.length && j<rightArray.length){            
            if (leftArray[i]>rightArray[j]){
                result[index++] = rightArray[j++];
            } else{
                result[index++] = leftArray[i++];
            }
        }
        
        while (i<leftArray.length){
            result[index++] = leftArray[i++];
        }
        while (j<rightArray.length){
            result[index++] = rightArray[j++];
        }
        return result;
    }
}
