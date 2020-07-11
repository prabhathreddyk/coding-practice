package edu.code.process.utility;

public class Robot {
	int[][] a = {{1,1,1,1,1,0,1,1},{1,1,1,1,1,0,1,1},{1,0,1,1,1,1,1,1},{0,0,0,1,0,0,0,0},{1,1,1,1,1,1,1,1}};
	public boolean move(int r, int c){
		if (r >= 0 && r< a.length && c >= 0 && c< a.length)
			return a[r][c] == 1;
		return false;
	}
	public void turnRight() {
		System.out.println("Turn Right");
		
	}
	
	public void clean() {
		System.out.println("clean");
		
	}
	
	public void turnLeft() {
		System.out.println("turnLeft");
		
	}
	public void move() {
		// TODO Auto-generated method stub
		System.out.println("move");
	}

}
