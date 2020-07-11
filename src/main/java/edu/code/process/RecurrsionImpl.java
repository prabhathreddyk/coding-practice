package edu.code.process;

import edu.code.process.utility.RecurrsionUtility;
import edu.code.process.utility.Robot;

public class RecurrsionImpl {

	public static void main(String[] args) {
		RecurrsionUtility util = new RecurrsionUtility();
//		System.out.println(util.getRow(3).toString());
//		
//		//pow
//		System.out.println(util.myPow(12, -4));
//		System.out.println(util.kthGrammar(5, 11));
		
//		System.out.println(util.totalNQueens(4));
//		util.cleanRoom(new Robot());
		int[] nums = {1,0,0,1,0,0,1,0};
		System.out.println(util.hammingDistance(1, 4));
		
	}

}
