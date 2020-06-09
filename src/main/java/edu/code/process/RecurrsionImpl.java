package edu.code.process;

import edu.code.process.utility.RecurrsionUtility;

public class RecurrsionImpl {

	public static void main(String[] args) {
		RecurrsionUtility util = new RecurrsionUtility();
		System.out.println(util.getRow(3).toString());
		
		//pow
		System.out.println(util.myPow(12, -4));
		System.out.println(util.kthGrammar(5, 11));
	}

}
