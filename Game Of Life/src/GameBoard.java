/*
 * This class will construct a Game Board. The game board will be represented
 * by a 2D array which can contain piece values in each location in the array.
 * By Joseph Liba
 */
public class GameBoard {
	private int[][] cells;
	private int numRows, numCols;
	
	//constructs a rectangle GameBoard with a particular number of rows and columns
	public GameBoard(int numRows, int numCols){
		this.cells = new int[numRows][numCols];
		this.numRows = numRows;
		this.numCols = numCols;
	}
	
	//constructs a square GameBoard with length and width equal to size
	public GameBoard(int size){
		this(size, size);
	}
	
	//constructs a GameBoard object using a 2D int array of cell values
	public GameBoard(int[][] board){
		cells = board;
		this.numRows = board.length;
		this.numCols = board[0].length;
	}
	
	//sets the value of a cell and returns that value
	public int setPiece(int rowNum, int colNum, int pieceValue){
		this.cells[rowNum][colNum] = pieceValue;
		return pieceValue;
	}
	
	//returns the value of a piece at a particular cell
	public int getPiece(int rowNum, int colNum){
		return this.cells[rowNum][colNum];
	}
	
	//removes a game board piece from a cell by setting the cell to 0
	public int removePiece(int rowNum, int colNum){
		setPiece(rowNum, colNum, 0);
		return 0;
	}
	
	//checks if a cell is occupied
	public boolean isOccupied(int rowNum, int colNum){
		if (getPiece(rowNum, colNum)!=0)
			return true;
		return false;
	}
	
	//checks if a position is valid
	public boolean isValid(int rowNum, int colNum){
		if((rowNum>=getRows() || rowNum < 0) || (colNum>=getCols() || colNum<0))
			return false;
		return true;
	}
	
	//sets the values of all the cells in the board to 0
	public void clear(){
		this.cells = new int[this.numRows][this.numCols];
	}
	
	//returns the number of rows
	public int getRows(){
		return this.numRows;
	}
	
	//returns the number of columns
	public int getCols(){
		return this.numCols;
	}
	
	//returns the 2D array of cells
	public int[][] getCells(){
		return this.cells;
	}
	
	//creates a string representation of the board, with numbers indicating the
	//respective values of the cells
	public String toString(){
		String board = "";
		for(int[] rows:cells){
			for(int values:rows){
				board += values + " ";
			}
			board += "\n";
		}

		return board;
	}
	
	public static void main(String[] args) {
	}

}
