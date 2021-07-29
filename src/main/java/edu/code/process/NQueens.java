package edu.code.process;

import java.util.HashSet;
import java.util.Set;

public class NQueens {
	
	Set<String> result = new HashSet<String>();
    public int totalNQueens(int n) {

        for (int j=0;j<n;j++) {
            int[][] visited = new int[n][n];
            traverse(0,j, visited);
        }
        return result.size();
    }

    private void traverse(int row, int col, int[][] visited){
        int n = visited.length;
        if (row == visited.length-1){
            visited[row][col] = 1;
            StringBuilder sb = new StringBuilder();
            for(int x=0;x<n;x++){
                for (int y=0;y<n;y++){
                    if (visited[x][y] == 1)
                        sb.append(x).append(y);
                }
            }
            visited[row][col] = 0;
            result.add(sb.toString());
            return;
        }

        updatePosition(row,col,visited, true);

        // find next position
        for (int j=0;j<n;j++){
            if (visited[row+1][j] == 0){
                traverse(row+1,j, visited);
            }
        }
        //revert
        updatePosition(row,col,visited, false);
    }

    private void updatePosition(int row, int col, int[][] visited, boolean isPlaceQueen){
        int value = isPlaceQueen ? -1 : 1;
        int n = visited.length;
        // update all row and col as attacking 
        for (int i=0;i<n;i++){
            visited[row][i]+=value;
            visited[i][col]+=value;

            // dale diagonal down
            if (row +i < n && col +i <n)
                visited[row+i][col+i] +=value;
             // dale diagonal up
            if (row-i >=0 && col-i >=0)
                visited[row-i][col-i] +=value;
            // hill diagonal down
            if (row+i < n && col-i >=0)
                visited[row+i][col-i] +=value;
            // hill diagonal down
            if (row-i >=0 && col +i <n)
                visited[row-i][col+i] +=value;
        }

        visited[row][col] = isPlaceQueen ? 1 : 0;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NQueens nq= new NQueens();
		System.out.println(nq.totalNQueens(4));
	}

}
