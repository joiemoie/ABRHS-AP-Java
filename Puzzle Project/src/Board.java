
/**
 * Board.java
 * 
 * This class contains the information for a board, specifically, it holds a 2D array of Pieces (which corresponds to the slots). It also contains methods related
 * to Board like getPiece, setPiece, etc.
 * 
 * @author Seth, Katie & Sean Tested/Edited
 *
 */
public class Board {

	private Piece[][] pieces;
	private int numRows;
	private int numCols;

	/**
	 * @param rows
	 * @param cols
	 * 
	 * Constructor for a Board that receives the number of rows and columns the Board will have. Note that if the Board receives
	 * negative numbers as parameters, it will throw an exception.
	 */
	public Board(int rows, int cols) {
		this.numRows = rows;
		this.numCols = cols;
		this.pieces = new Piece[rows][cols];
	}

	/**
	 * @param size
	 * 
	 * Constructor for a Board that receives the size of the Board (refers to both the number of Rows and Columns). Note that if the Board receives
	 * negative numbers as parameters, it will throw an exception.
	 */
	public Board(int size) {
		this.numCols = size;
		this.numRows = size;
	}

	/**
	 * @return the numRows
	 */
	public int getRows() {
		return numRows;
	}

	/**
	 * @param numRows
	 *            the numRows to set
	 */
	public void setRows(int numRows) {
		this.numRows = numRows;
	}

	/**
	 * @return the numCols
	 */
	public int getCols() {
		return numCols;
	}

	/**
	 * @param numCols
	 *            the numCols to set
	 */
	public void setCols(int numCols) {
		this.numCols = numCols;
	}

	/**
	 * @param row
	 * @param col
	 * 
	 * Just returns the Piece object at the indicated position. If there's no piece or the position isn't on the board, this return null.
	 * 
	 * @return Piece at the indicated position
	 */
	public Piece getPiece(int row, int col) {
		if(isValid(row, col)){
			return pieces[row][col];
		}
		return null;
	}

	/**
	 * @param row
	 * @param col
	 * 
	 * Quickly checks if the position given is on the Board.
	 * 
	 * @return true if position is on the board, false if not on the board.
	 */
	public boolean isValid(int row, int col) {
		
		return !((row < 0 || col < 0) || (row > numRows - 1 || col > numCols - 1));
	}

	/**
	 * @param piece
	 * @param row
	 * @param col
	 * 
	 * Sets the indicated position on the Board to the given Piece.
	 * 
	 * @return the Piece that used to be in the position, if there was one. Otherwise (no piece or not on board), null is returned.
	 */
	public Piece setPiece(Piece piece, int row, int col) { 
		if (isOccupied(row, col)) {
			Piece temp = pieces[row][col];
			pieces[row][col] = piece;
			return temp;
		}
		
		if(isValid(row, col)){
			pieces[row][col] = piece;
		}
		
		return null;
	}

	/**
	 * @param row
	 * @param col
	 * 
	 * Checks if the indicated position has a Piece there.
	 * 
	 * @return true if there was a piece in the indicated position, false if otherwise or the position isn't on the Board.
	 */
	public boolean isOccupied(int row, int col) {
		
		return isValid(row,col) && pieces[row][col] != null;
	}

	/**
	 * Removes all pieces on the Board by using nested for loops.
	 */
	public void clear() {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				pieces[i][j] = null;
			}
		}
	}

	/**
	 * @param row
	 * @param col
	 * 
	 * Removes the piece at the indicated position on the Board.
	 * 
	 * @return the Piece that used to be in the indicated position, if no piece was there, this method returns null.
	 */
	public Piece removePiece(int row, int col) {
		if (isOccupied(row, col)) {
			Piece temp = pieces[row][col];
			pieces[row][col] = null;
			return temp;
		}
		return null;
	}
	
	/**
	 * Just a toString for testing.
	 */
	public String toString() {
		String answer = new String();
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				if (isOccupied(i, j)) {
					answer += pieces[i][j].toString() + "\n";
				}
			}
		}
		return answer;
	}

	

	/**
	 * @param args
	 * 
	 * Just a main method for testing.
	 */
	public static void main(String[] args) {
		Board board = new Board(3, 3);
		
		Piece one = new Piece(Side.CLUB_IN, Side.CLUB_OUT, Side.DIAMOND_IN, Side.DIAMOND_OUT);
		Piece two = new Piece(Side.CLUB_IN, Side.CLUB_OUT, Side.SPADE_IN, Side.SPADE_OUT);
		Piece three = new Piece(Side.HEART_IN, Side.HEART_OUT, Side.CLUB_IN, Side.CLUB_OUT);
		Piece four = new Piece(Side.CLUB_IN, Side.CLUB_OUT, Side.DIAMOND_IN, Side.DIAMOND_OUT);
		Piece five = new Piece(Side.CLUB_IN, Side.CLUB_OUT, Side.DIAMOND_IN, Side.DIAMOND_OUT);
		
		board.setPiece(one, 0, 0);
		board.setPiece(two, 0, 1);
		board.setPiece(three, 0, 2);
		board.setPiece(four, 1, 0);
		board.setPiece(five, 1, 1);
		
		System.out.println(board.toString());
		
		one.rotateClockwise();
		
		System.out.println(board.toString());

	}
}
