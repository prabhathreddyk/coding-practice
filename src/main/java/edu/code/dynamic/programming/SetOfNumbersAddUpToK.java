package edu.code.dynamic.programming;

import java.util.Arrays;

public class SetOfNumbersAddUpToK {

	public static void main(String[] args) {
		int[] array = {7,2,4,6,10,3,1};
		System.out.println("Number of subsets: "+findSetOfNumbersAddToK(array, 6));
		System.out.println("Number of subsets: "+findSetOfNumbersAddToK(array, 6, 0));
	}

	/**
	 * Easy to understand sub array but creates more sub arrays
	 * @param array
	 * @param k
	 * @return
	 */
	private static int findSetOfNumbersAddToK(int[] array, int k){
//		System.out.println(Arrays.toString(array) + " , k="+k);
		if ( k == 0){
			return 1;
		}
		if (array.length == 0){
			return 0;
		}
		int count = 0;
		if (k-array[0] >= 0){
			count+=findSetOfNumbersAddToK(Arrays.copyOfRange(array, 1, array.length), k-array[0]);
		}
		count+=findSetOfNumbersAddToK(Arrays.copyOfRange(array, 1, array.length), k);
		return count;
	}
	
	/**
	 * Efficient than above function 
	 * @param array
	 * @param k
	 * @param index
	 * @return
	 */
	private static int findSetOfNumbersAddToK(int[] array, int k, int index){
//		System.out.println(Arrays.toString(array) + " , k="+k + ", index="+index);
		if ( k == 0){
			return 1;
		}
		if (index > array.length-1){
			return 0;
		}
		int count = 0;
		if (k-array[index] >= 0){
			count+=findSetOfNumbersAddToK(array, k-array[index], index+1);
		}
		count+=findSetOfNumbersAddToK(array, k, index+1);
		return count;
	}
}
