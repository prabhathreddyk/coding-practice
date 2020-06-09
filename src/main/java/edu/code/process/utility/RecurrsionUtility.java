package edu.code.process.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecurrsionUtility {

	public List<Integer> getRow(int rowIndex) {
        
        int[] row = new int[rowIndex+1];
        List<Integer> list = new ArrayList<Integer>(rowIndex+1);
        for (int i : getRow(row, rowIndex, 0))
        	list.add(i);
        
        return list;
    }
    
    private int[] getRow(int[] row, int rowIndex, int currentIndex) {
        if (rowIndex >= currentIndex){
            row[currentIndex] = 1;
            int prev = 1;
            int curr = 0;
            for (int i=1;i<currentIndex;i++){
                curr = row[i];
            	row[i] =  prev+row[i];
                prev=curr;
            }
            getRow(row, rowIndex, currentIndex+1);
        }
        return row;
    }
    
    public double myPow(double x, int n) {
        if (x == 1 || x == 0)
            return x;
        if (n ==0){
            return 1;
        }
        
        if ( n < 0){
            if (n == Integer.MIN_VALUE)
                return x < 0 ? 1 : 0;
            return 1/calcPow(x,n > 0 ? n : -n);
        }else
            return calcPow(x, n);
    }
    
    private double calcPow(double x, int n){
        if (n == 1)
            return x;
        double half = myPow(x, n/2);
        if (n%2 == 0)
            return half*half;
        else
            return half*half*x;
    }
    
    public int kthGrammar(int N, int K) {
    	if (N == 1)
    		return 0;
    	int val = kthGrammar(N-1, K%2 == 0 ? K/2 : K/2+1);
    	if ((K%2 == 0 && val == 0) ||  (K%2 == 1 && val == 1)){
    		return 1;
    	} else{
    		return 0;
    	}
    }
    
}
