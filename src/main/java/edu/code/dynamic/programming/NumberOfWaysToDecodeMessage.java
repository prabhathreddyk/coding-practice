package edu.code.dynamic.programming;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * If a = 1, b=2, c=3 ..., z=26 
 * Data "12" can be decoded as "ab" (a-1, b-2) or "L" (l-12)
 * This function will return number of ways we can decode a message for given data string
 * @author prabhathkatta
 *
 */
public class NumberOfWaysToDecodeMessage {
	
	static AtomicInteger atomicInteger1 = new AtomicInteger();  
	static AtomicInteger atomicInteger2 = new AtomicInteger();  
	public static void main(String[] args) {
		String data = "1111111111111111";
		System.out.println("Number of ways to decode : " + num_ways(data.toCharArray(),0) + ", Iterations : "+ atomicInteger1.get());

		System.out.println("Number of ways to decode : " + num_ways(data.toCharArray(),0, new int[data.length()])+ ", Iterations : "+ atomicInteger2.get());
	}

	private static int num_ways(char[] data, int index) {
		atomicInteger1.incrementAndGet();
		if (data.length <2){
			return 1;
		}
		
		if (index >= data.length -1){
			return 1;
		}
		int count = 0;
		int i1 = Character.getNumericValue(data[index]);
		int i2 = Character.getNumericValue(data[index+1]);
		count+=num_ways(data, index+1);
		
		if ((i1*10)+i2 < 27){
			count+=num_ways(data, index+2);
		}
		return count;
	}
	
	/**
	 * Efficient than above function 
	 * @param data
	 * @param index
	 * @param mem
	 * @return
	 */
	private static int num_ways(char[] data, int index, int[] mem) {
		atomicInteger2.incrementAndGet();
		if (data.length <2){
			return 1;
		}
		
		if (index >= data.length -1){
			return 1;
		}
		if (mem[index] > 0){
			//System.out.println("Hit Index : "+ index + " , count : "+mem[index]);
			return mem[index];
		}
		int count = 0;
		int i1 = Character.getNumericValue(data[index]);
		int i2 = Character.getNumericValue(data[index+1]);
		count+=num_ways(data, index+1, mem);
		
		if ((i1*10)+i2 < 27){
			count+=num_ways(data, index+2, mem);
		}
		mem[index] = count;
		return count;
	}

}
