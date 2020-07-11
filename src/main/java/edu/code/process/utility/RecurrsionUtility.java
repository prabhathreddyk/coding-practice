package edu.code.process.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecurrsionUtility {

	public RecurrsionUtility() {
		super();
	}

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
    
    int count = 0;
    public int totalNQueens(int n) {
        if ( n < 2)
            return n;
        int[] colPositions = new int[n];
        int[] hills = new int[n+n];
        int[] dales = new int[n+n];
        placeQueen(0, colPositions, hills, dales);
        return count;
    }
    
    private void placeQueen(int row, int[] colPositions, int[] hills, int[] dales){
        for (int col=0;col<colPositions.length;col++){
            if ( isAvailable(row, col, colPositions, hills, dales)){
                colPositions[col] = 1;
                hills[row+col] = 1;
                dales[row-col+colPositions.length] = 1;
            
                if (row < colPositions.length-1)
                    placeQueen(row+1, colPositions, hills, dales);
                else
                    count++;
                
                // remove queen 
                colPositions[col] = 0;
                hills[row+col] = 0;
                dales[row-col+colPositions.length] = 0;
            }
        }
    }
    
    private boolean isAvailable(int row, int col, int[] colPositions, int[] hills, int[] dales){
        return colPositions[col] == 0 && hills[row+col] == 0 && dales[row-col+colPositions.length] == 0;
    }
    
    public void cleanRoom(Robot robot) {
        Set<Pair<Integer,Integer>> visited = new HashSet<Pair<Integer,Integer>>();
        cleanRoom(robot, visited, 1, 3, 0);
    }
    
    private void cleanRoom(Robot robot, Set<Pair<Integer,Integer>> visited, int x, int y, int d){
        visited.add(new Pair(x,y));
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int i = 0; i < 4; ++i) {
            int newD = (d + i) % 4;
            int newX = x+directions[newD][0];
            int newY = y+directions[newD][1];
            if (robot.move(newX, newY) && !visited.contains(new Pair(newX, newY))){
                cleanRoom(robot, visited, newX, newY, newD);
                goBack(robot);
            }
            robot.turnRight();
        } 
    }
    
    private void goBack(Robot robot){
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }
    
    /**
     * combinations
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        return combine(1, n, k, result);
        
    }
    
    private List<List<Integer>> combine(int start, int n, int k, List<List<Integer>> result){
        if (k==1){
            List<List<Integer>> o1 = new ArrayList<List<Integer>>();
            for (int i = start;i<=n;i++){
                List<Integer> o2 = new ArrayList<Integer>();
                o2.add(i);
                o1.add(o2);
            }
            return o1;
        }
        if (k>1 && n-start+1>=k){
            List<List<Integer>> r1 = combine(start+1, n, k-1, result);
            for (List<Integer> l1 : r1){
                l1.add(start);
            }
            List<List<Integer>> r2 = combine(start+1, n, k, result);
            r1.addAll(r2);
            return r1;
        }
            
        return new ArrayList<List<Integer>>();
    }
    
    
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        if (n==0){
            result.add("");
            return result;
        }
        for (int i=0;i<n;i++){
            for (String s1 : generateParenthesis(i)){
                for (String s2 : generateParenthesis(n-i-1)){
                    result.add("("+s1+")"+s2);
                }
            }
        }
        return result;   
    }
    
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0)
            return result;
        if (nums.length == 1){
            List<Integer> l1 = new ArrayList<Integer>();
            l1.add(nums[0]);
            result.add(l1);
            return result;
        }
        for (int i=0;i<nums.length;i++){
            int temp = nums[i];
            nums[i] = nums[0];
            nums[0] = temp;
            for (List<Integer> l1 : permute(Arrays.copyOfRange(nums, 1, nums.length))){                
                l1.add(0,nums[0]);
                result.add(l1);
            }
        }
        return result;
        
    }
    
    /**
     * Binary Search in Rotated Sorted Array  
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums==null || nums.length==0)
            return -1;
        int start = 0;
        int end = nums.length-1;
        int pivot = -1;
        if (nums[0] > nums[end]){
            while ( start <= end){
                pivot = start + (end-start)/2;
                if (nums[pivot] > nums[nums.length-1]){
                    start = pivot+1;
                } else{
                    end = pivot-1;
                }
            }
            pivot = start;
        }
        start = 0;
        end = nums.length-1;
        if (pivot != -1 ){
            if ( target <= nums[end]){
                start = pivot;
            } else{
                end = pivot-1;
            }
        }
        while (start <= end){
            pivot = start + (end-start)/2; 
            if (nums[pivot]>target){
                end = pivot-1;
            } else if (nums[pivot]<target){
                start = pivot+1;
            } else {
                return pivot;
            }
        }
        return -1;
    }
    
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        if (nums == null || nums.length == 0){
            return result;
        }
        int start = 0;
        int end = nums.length-1;
        while( start<=end){
            int pivot=start + (end-start)/2;
            if (nums[pivot] < target){
                start=pivot+1;
            }else if(nums[pivot] > target){
                end = pivot-1;
            }else{
                int temp = end;
                int oldPivot = pivot;
                end = pivot;
                while (start+1<end){
                    pivot = start+ (end-start)/2;
                    if (nums[pivot] < target ){
                        if (nums[pivot+1] == target){
                            start = pivot+1;
                            break;
                        }                            
                        start = pivot+1;
                    } else {
                    	if (nums[pivot-1]<target){
                    		end = pivot;
                    		break;
                    	}
                    		
                        end = pivot-1;
                    }
                }
                result[0]=nums[start] == target ? start :end;
                start = oldPivot;
                end= temp;
                while(start<end){
                    pivot=start+(end-start)/2;
                    if (nums[pivot] > target){
                        end = pivot-1;
                    }else{
                        if (nums[pivot+1]>nums[pivot]){
                            end = pivot;
                            break;
                        }
                        start= pivot+1;
                    }
                }
                result[1]=end;
                break;
            }
        }
        return result;
    }
    
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> result = new ArrayList<Integer>();
        if (arr == null || arr.length == 0)
            return result;
        if (x <= arr[0] || x >= arr[arr.length-1]){
            int i = x <= arr[0] ? 0 : arr.length-k;
            while(result.size()<k){
                result.add(arr[i++]);
            }
            return result;
        }
        int start = 0;
        int end = arr.length-1;
        int index= -1;
        while(start+1<end && index < 0){
            int pivot = start+(end-start)/2;
            if (x < arr[pivot]){
                if (x >= arr[pivot-1]){
                    index = Math.abs(arr[pivot-1]-x) < Math.abs(arr[pivot]-x) ? pivot-1 : pivot;
                }
                end = pivot-1;
            } else if (x > arr[pivot]){
                if (x <= arr[pivot+1]){
                    index = Math.abs(arr[pivot+1]-x) < Math.abs(arr[pivot]-x) ? pivot+1 : pivot;
                }
                start = pivot+1;
            } else{
                index = pivot;
            }
        }
        
        if (index < 0){
            index = Math.abs(arr[start]-x) <= Math.abs(arr[end]-x) ? start : end;
        }
        int left = index;
        int right = index;
        result.add(arr[index]);
        
        for (int i = 1;i<k;i++){
            if (left-1 < 0 || ( right+1 < arr.length && 
                               Math.abs(arr[left-1]-x) > Math.abs(arr[right+1]-x))){
                result.add(arr[++right]);
            }else{
                result.add(0, arr[--left]);
            }
        }     
        return result;
    }
    
    public boolean isPerfectSquare(int num) {
    	if (num<2)
            return true;
        int n = num/2;
        // Data type should be long to avoid integer overflow since we are multiplying pivot*pivot
        long max = n; 
        long min = 2;
        while (min <= max){
            long pivot = min + (max-min)/2;
            long val = pivot*pivot;
            if (val < num){
                min = pivot+1;
            }else if (val>num)
                max=pivot-1;
            else
                return true;
        }
        return false;
    }
    
    public char nextGreatestLetter(char[] letters, char target) {
        
        int start=0;
        int end=letters.length-1;
        while(start+1<end){
            int pivot = start + (end-start)/2;
            if ( target < letters[pivot]){
                if (target >= letters[pivot-1])
                    return letters[pivot];
                end = pivot;
            } else {
                if (target < letters[pivot+1])
                    return letters[pivot+1];
                start = pivot;
            }
        }
        
        return target < letters[start] ? letters[start]:letters[end] ;
        
    }
    
    public int arrangeCoins(int n) {
        if (n < 2)
            return n;
        long start = 1;
        long end = n;
        while (start+1 < end){
        	long pivot = start + (end-start)/2;
        	long val = (pivot*(pivot+1))/2;
            if (val < n){
                start = pivot;
            }else if (val > n){
                end = pivot;
            }else
                return (int)pivot;
        }
        long val = (end*(end+1))/2;
        return val == n ? (int)end : (int)start;
    }
    
    public int[] prisonAfterNDays(int[] cells, int N) {
        List<String> list = new ArrayList<String>();
        int i=1;
        for (;i<=N;i++){
            StringBuilder sb = new StringBuilder();
            int[] cellsPos = new int[cells.length];
            for (int j=1;j< cells.length-1;j++){
                if (cells[j-1] == cells[j+1])
                    cellsPos[j] = 1;
                sb.append(cellsPos[j]);
            }
            cells = cellsPos;
            if (list.contains(sb.toString())){
            	break;
            }
            list.add(sb.toString());
            
        }
        if ( i<N){
        	cells = new int[cells.length];
        	int index = (N-1) % list.size()-1;
        	index = index < 0 ? 0 : index;
        	for (int j=1;j<cells.length-1;j++){
        		cells[j] = Integer.valueOf(list.get(index).substring(j-1, j));
        	}
        }
        return cells;
    }
    
    public int hammingDistance(int x, int y) {
    	int temp = x^y;
    	int count=0;
    	while (temp !=0){
    		if ((1 & temp) == 1)
    			count++;
    		temp = temp >> 1;
    	}
    	return count;
    }
}
