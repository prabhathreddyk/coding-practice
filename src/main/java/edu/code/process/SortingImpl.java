package edu.code.process;

import edu.code.process.utility.SortingUtility;

public class SortingImpl {

	public static void main(String[] args) {
		int[] nums = {5,1,1,2,0,0};
		SortingUtility util = new SortingUtility();
		for (int i : util.mergesortArray(nums)){
			System.out.print(i + " ");
		}
	}

}
