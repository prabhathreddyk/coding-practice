package edu.code.dynamic.programming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SetOfNumbersAddUpToK {

	public static void main(String[] args) {
		int[] array = {7,2,4,6,10,3,1};
//		System.out.println("Number of subsets: "+findSetOfNumbersAddToK(array, 10));
//		System.out.println("Number of subsets: "+findSetOfNumbersAddToK(array, 10, 0));
		System.out.println("Number of subsets: "+findSetOfNumbersAddToK(array, 10, 0, new HashMap<String, Integer>()));

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
	
	/**
	 * Dynamic programming including memoization Efficient than above function 
	 * @param array
	 * @param k
	 * @param index
	 * @return
	 */
	private static int findSetOfNumbersAddToK(int[] array, int k, int index, Map<String, Integer> mem){
//		System.out.println("k="+k + ", index="+index);
		if ( k == 0){
			return 1;
		}
		if (index > array.length-1){
			return 0;
		}
		if (mem.containsKey(k+":"+index)){
			System.out.println("Hit : "+ k+":"+index+ ", count="+ mem.get(k+":"+index));
			return mem.get(k+":"+index);
		}
		int count = 0;
		if (k-array[index] >= 0){
			count+=findSetOfNumbersAddToK(array, k-array[index], index+1, mem);
		}
		count+=findSetOfNumbersAddToK(array, k, index+1, mem);
		mem.put(k+":"+index, count);
		return count;
	}
}
