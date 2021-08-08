package edu.code.process;


public class Sudoku {

	 boolean solved = false;
	    public void solveSudoku(char[][] board) {
	        solveSudoku(board, 0,0);
	    }
	    
	    private void solveSudoku(char[][] board, int row, int col){
	        for(;row<9;row++){
	            for(;col<9;col++){
	                if (board[row][col] == '.'){                    
	                    break;
	                }
	            }
	            if (col < 9)
	            	break;
	            col=0;
	        }
	        if (row == 9){
	            solved = true;
	            return;
	        }
	            
	        for (int i=1;i<10;i++){
	            if (isValid(board, row, col, i)){
	                board[row][col] = (char) (i+'0');
	                int newRow = row;
	                int newCol = col+1;
	                if (newCol == 9){
	                    newRow++;
	                    newCol = 0;
	                }
	                solveSudoku(board,newRow,newCol);
	                if (solved)
	                    return;
	            }
	        }
	        if (!solved){
	            board[row][col] = '.';
	        }
	            
	        
	    }
	    
	    private boolean isValid(char[][] board, int row, int col, int val){
	        // check row and col
	        for (int y=0;y<9;y++){
	            if ( y != col && board[row][y] == val + '0'){
	                    return false;
	            }
	            if ( y != row && board[y][col] == val + '0'){
	                    return false;
	            }
	        }
	        
	        int i = row - (row%3);
	        int iEnd = i + 3;
	        int j = col - (col%3);
	        int jEnd = j + 3;
	        for (;i<iEnd;i++){
	        	j = col - (col%3);
	            for (;j<jEnd;j++){
	                if (i != row && j != col && board[i][j] == val + '0'){
	                    return false;
	                }                
	            }
	        }
	        return true;
	    }
	    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sudoku s = new Sudoku();
		//char[][] board = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
		char[][] board = {{'.','.','9','7','4','8','.','.','.'},{'7','.','.','.','.','.','.','.','.'},{'.','2','.','1','.','9','.','.','.'},{'.','.','7','.','.','.','2','4','.'},{'.','6','4','.','1','.','5','9','.'},{'.','9','8','.','.','.','3','.','.'},{'.','.','.','8','.','3','.','2','.'},{'.','.','.','.','.','.','.','.','6'},{'.','.','.','2','7','5','9','.','.'}};
		s.solveSudoku(board);

	}

}
