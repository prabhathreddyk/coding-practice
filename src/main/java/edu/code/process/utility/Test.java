package edu.code.process.utility;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		System.out.println(prison(10, 8, Arrays.asList(1, 4,5), Arrays.asList(2, 3,5, 6,7)));
	}
	
	public static long prison(int n, int m, List<Integer> h, List<Integer> v) {
	    // Write your code here
	        int hSeqCount = getMaxContinousBarsRemoved(h);
	        int vSeqCount = getMaxContinousBarsRemoved(v);
	        long result  = (hSeqCount+1) * (vSeqCount+1);
	        return result;
	    }

	    private static int getMaxContinousBarsRemoved(List<Integer> h){
	        int hSeqCount = 0;
	        if (h != null ){
	            if (h.size() ==1){
	                hSeqCount = 1;
	            }else{
	                Collections.sort(h);
	                int count = 1;
	                for (int i=1;i<h.size();i++){
	                    if (h.get(i) - h.get(i-1) > 1){
	                        if (hSeqCount == 0 || count > hSeqCount){	                        	
	                            hSeqCount = count;	       
	                        }
	                        count = 1;
	                    }else{
                            count++;
                        }
	                }
	                if (hSeqCount == 0 || count > hSeqCount){
	                    hSeqCount = count;
	                 }
	            }
	            
	        }
	        return hSeqCount;
	    }

}
