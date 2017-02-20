/*
 * Board.java: A class to construct a grid board, which
 * is a 2D array of int values, upon which any game that requires
 * a grid board can be played.
 * 
 * Written by Mark Guan
 * Period 6
 * 24 November 2013
 */
public class Board {
	
	private int [][] board;
	private int numRows;
	private int numColumns;
	
	
	 // Constructs a grid board using a given number of rows and columns
	 // Parameters: two ints that represent the number of rows and columns
	public Board(int numRows, int numColumns){
		board = new int [numRows] [numColumns];
		this.numRows=numRows;
		this.numColumns = numColumns;	
	}
	
	//Constructs a grid board using a given 2D array
	// Parameters: a 2D array
	public Board(int[][] givenInput){
		board = givenInput;
		this.numRows= givenInput.length;
		this.numColumns = givenInput[0].length;
	}
	
	
	/*
	 * Prints a string representation of a 2D array 
	 * Parameters: does not require parameters 
	 * Returns: an string representing the contents of a given grid board 
	 */
	public String toString(){
		String BoardAsAString = "";
			for (int row = 0; row < numRows; row++){	
				for (int col = 0; col < numColumns; col++){
					BoardAsAString +=  board[row][col] + " ";
				}	
				BoardAsAString += "\n";
			}
		return BoardAsAString;	
	}
	
	
	/*
	 * Sets a cell on a given board to an int value representing its state
	 * Parameters: three int values that represent the row and column number, as well as the state 
	 * that the cell will be set to, either 0 or 1
	 * Returns: the original value of the changed piece
	 */
	public int setCell(int row, int column, int pieceType){
		int removedPiece = board[row][column];
		board[row][column] = pieceType;
		return removedPiece;
	}
	

	/*
	 * Checks whether or not the cell is occupied with a living cell
	 * Parameters: two int values represent the row number and column number
	 * Returns: a boolean value representing whether or not the cell is occupied with a living cell
	 */
	public boolean isOccupied(int row, int col){
		return board[row][col] == 1;
	}
	
	
	/*
	 * Gets a cell on a given board
	 * Parameters: two int values represent the row number and column number
	 * Returns: an int value representing the state of the cell on a given grid board
	 */
	public int getCell(int row, int col){
		if (isValidLoc(row,col)){
			return board[row][col];
		}
		return 0;
	}
	
	
	/*
	 * Clears a given grid board and resets all the cells on the board to an int value of 0, representing its state
	 * Parameters: does not require parameters
	 * Returns: does not return anything 
	 */
	public void clearBoard(){
		board = new int [numRows] [numColumns];
	}
	
	
	/*
	 * Gets the number of rows of a given grid board
	 * Parameters: does not require parameters 
	 * Returns: an int value representing the number of rows of a given grid board 
	 */
	public int getNumRows(){
		return this.numRows;
	}
	
	/*
	 * Gets the number of columns of a given grid board
	 * Parameters: does not require parameters 
	 * Returns: an int value representing the number of columns of a given grid board 
	 */
	public int getNumColumns(){
		return this.numColumns;
	}
	
	
	/*
	 * Validates the position of a cell on a given grid board
	 * Parameters: two int values represent the row number and column number
	 * Returns: a boolean value representing the possibility of the existence of a cell on a given grid board
	 */
	public boolean isValidLoc(int row, int column){
		if(row >= this.numRows || row < 0){
			return false;
		}
		if(column >= this.numColumns || column<0){
			return false;
		}
		return true;
	}
	
	
	
	
}
