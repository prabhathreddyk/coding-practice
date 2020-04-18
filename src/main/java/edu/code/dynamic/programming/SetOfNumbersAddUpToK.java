package edu.code.dynamic.programming;

import java.util.Arrays;

public class SetOfNumbersAddUpToK {

	public static void main(String[] args) {
		int[] array = {2,4,6,10,3,1};
		System.out.println("Number of subsets: "+findSetOfNumbersAddToK(array, 6));
	}

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
}
