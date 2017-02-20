/*
 * Rules.java: A class to carries out game actions 
 * based on the rules of Conway's Game of Life onto a given grid board. 
 * 
 * Written by Mark Guan
 * Period 6
 * 24 November 2013
 */

import java.util.Random;

public class Rules {
	private Board board;
	
	/*
	 * Constructs a board needed to play Conway's Game of Life
	 *  based on two int values, representing the number of rows and 
	 *  columns of a grid board 
	 */
	public Rules (int row, int column){
		board = new Board(row,column);
	}
	
	/*
	 * Constructs a board needed to play Conway's Game of Life based on a given 2D array
	 */
	public Rules (int [] [] input){
		board = new Board(input);
	}
	
	/*
	 * Constructs a board needed to play Conway's Game of Life based on a given Board
	 */
	public Rules (Board board){
		this.board = board;
	}
	
	
	/* Obtains the number of neighboring cells that are in an "alive" state around a given cell on the board
	 * Parameters: two int values, representing the row number and column number
	 * Returns: an int value representing the number of neighboring cells that are alive a given cell on a grid board has
	 */
	public int getNumofOccupiedNeighbors(int row, int column){
		int NumofOccupiedNeighbors = 0;
		//bottom right
		if (board.isValidLoc(row + 1, column + 1) && board.getCell(row + 1, column + 1) == 1)
			NumofOccupiedNeighbors++;
		//right
		if (board.isValidLoc(row, column + 1) && board.getCell(row, column + 1) == 1)
			NumofOccupiedNeighbors++;
		//top right
		if (board.isValidLoc(row - 1, column + 1) && board.getCell(row - 1, column + 1) == 1)
			NumofOccupiedNeighbors++;
		//above
		if (board.isValidLoc(row - 1, column) && board.getCell(row - 1, column) == 1)
			NumofOccupiedNeighbors++;
		//top left
		if (board.isValidLoc(row - 1, column - 1) && board.getCell(row - 1, column - 1) == 1)
			NumofOccupiedNeighbors++;
		//left
		if (board.isValidLoc(row, column - 1) && board.getCell(row, column - 1) == 1)
			NumofOccupiedNeighbors++;
		//bottom left
		if (board.isValidLoc(row + 1, column - 1) && board.getCell(row + 1, column - 1) == 1)
			NumofOccupiedNeighbors++;
		//below
		if (board.isValidLoc(row + 1, column) &&  board.getCell(row + 1, column) == 1)
			NumofOccupiedNeighbors++;
		
		return NumofOccupiedNeighbors;
	}
	
	/* Creates the next generation of the board based of whether or not the cell is going to be alive in the next Generation 
	 * Parameters: does not have parameters
	 * Returns: does not return anything
	 */
	public void nextGen(){
		Board copy = new Board(board.getNumRows(),board.getNumColumns());
		for (int row = 0; row < board.getNumRows(); row++){	
			for (int col = 0; col < board.getNumColumns(); col++){
				if(isAlive(row,col)){
					copy.setCell(row, col, 1);
				}
				else{
					copy.setCell(row, col, 0);
				}
			
			}	
		}
		board=copy;
	}
	
	
	/*
	 * Gets the number of rows of the board
	 * Parameters: does not require parameters 
	 * Returns: an int value representing the number of rows of a given grid board 
	 */
	public int getNumRows(){
		return board.getNumRows();
	}
		
	/*
	 * Gets the number of columns of the board
	 * Parameters: does not require parameters 
	 * Returns: an int value representing the number of columns of a given grid board 
	 */
	public int getNumColumns(){
		return board.getNumColumns();
	}
	
	/*Sets a cell on a given grid board to an int value representing its state
	 * by delegating its work to the setCell method in the board class
	 * Parameters: three int values that represent the row and column number, as well as the state 
	 * that the cell will be set to, either 0 or 1
	 * Returns: the original value of the changed piece
	 */
	public int setCell(int row, int column, int pieceType){
		int removedPiece = board.getCell(row, column);;
		board.setCell(row, column, pieceType);
		return removedPiece;
	}
	
	/*
	 * Gets a cell the board
	 * Parameters: two int values represent the row number and column number
	 * Returns: an int value representing the state of the cell on a given grid board
	 */
	public int getCell(int row, int col){
		return board.getCell(row, col);
	}
	
	/* Determines whether a cell is "alive" or "dead" based upon the rules of Conway's Game of Life
	 * Parameters: Parameters: two int values, representing the row number and column number
	 * Returns: a boolean value representing whether or not the cell will be alive
	 * in the next generation based on the rules of Conway's Game of Life
	 */
	public boolean isAlive(int row, int col){
		if(getNumofOccupiedNeighbors(row, col) == 3){
			return true;
		}
		if(getNumofOccupiedNeighbors(row,col)==2){
			return (board.getCell(row,col)==1);
		}
		return false;
		
	}
	
	/*
	 * Prints a string representation of a 2D array 
	 * Parameters: does not require parameters 
	 * Returns: a string representing the contents of the board 
	 */
	public String toString(){
		return board.toString();
	}
	
	//setters for size
	public void setNumRows(int NumRows){
		this.board = new Board(NumRows, board.getNumColumns());
	}
	public void setNumCols(int NumCols){
		this.board = new Board(board.getNumRows(), NumCols);
	}
	
	/*
	 * Clears a given grid board and resets all the cells on the board to an int value of 0, representing its state
	 * Parameters: does not require parameters
	 * Returns: does not return anything 
	 */
	public void clear(){
		board.clearBoard();
	}
	
	/*
	 * Validates the position of a cell on the board
	 * Parameters: two int values represent the row number and column number
	 * Returns: a boolean value representing the possibility of the existence of a cell on a given grid board
	 */
	public boolean isValidLoc(int row, int column){
		if(row >= board.getNumRows() || row < 0){
			return false;
		}
		if(column >= board.getNumColumns() || column<0){
			return false;
		}
		return true;
	}
	
	/*generates a random board where 50% of cells are randomly set to alive
	 * doesn't not take in or return anything
	 */
	public void randomGame(){
		Random generator = new Random();
		for (int row = 0; row < board.getNumRows(); row++){	
			for (int col = 0; col < board.getNumColumns(); col++){
				if(generator.nextInt(100) < 50){
					board.setCell(row, col, 1);
				}
				else{
					board.setCell(row, col, 0);
				}
			}	
		}
	}
	

	
}
