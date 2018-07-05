import java.util.ArrayList;
import java.util.List;

/**
 * Puzzle.java
 * 
 * This Class holds the information for a Puzzle - the board and the list of pieces that haven't been used yet (those that have been
 * used are in the Board). There are also methods for Puzzle, like solve, reset, etc. that go with a Puzzle game.
 * 
 * @author Sean & Katie, Edited by Seth
 *
 */
public class Puzzle {
	private Board board;
	private List<Piece> unusedList; 
	
	
	/**
	 * @param rows
	 * @param cols
	 * @param pieces
	 * 
	 * Puzzle Constructor that takes in the number of rows and columns and a list of pieces. Note that if the Puzzle receives
	 * negative numbers as parameters, it will throw an exception.
	 */
	public Puzzle(int rows, int cols, List<Piece> pieces){ //What about receiving negative beginnings?
		this.board = new Board(rows, cols);
		this.unusedList = pieces;
	}
	
	/**
	 * @param size
	 * @param pieces
	 * 
	 * Puzzle Constructor that takes in a size (refers to the number of rows and columns) and a list of pieces. Note that if the Puzzle receives
	 * negative numbers as parameters, it will throw an exception.
	 */
	public Puzzle(int size, List<Piece> pieces){
		this.board = new Board(size);
		this.unusedList = pieces;
	}
	
	/**
	 * Just a getter for the Puzzle's row count (in Board).
	 * 
	 * @return number of Rows in the Puzzle's Board object.
	 */
	public int getRows(){
		return board.getRows();
	}
	
	/**
	 * Just a getter for the Puzzle's column count (in Board).
	 * 
	 * @return number of Columns in the Puzzle's Board object
	 */
	public int getCols(){
		return board.getCols();
	}
	
	/**
	 * @param piece
	 * @param row
	 * @param col
	 * 
	 * This method checks if a piece can fit in a spot. This means that all sides of the piece need to be against a side that corresponds to it or
	 * against a spot without a piece (null). Note that the bottomVal, topVal, rightVal, and leftVal variables are not necessary, but
	 * are kept for readability.
	 * 
	 * @return true if the piece doesFit in the spot, false if it does not fit.
	 * 
	 */
	public boolean doesFit(Piece piece, int row, int col){ 
		if(piece != null && board.isValid(row, col)){
			
			if(board.isOccupied(row, col)) return false;
			
			if(board.isOccupied(row + 1, col)){
				int bottomVal = piece.getSide(Direction.BOTTOM).getValue();
				if(!(bottomVal + board.getPiece(row+1,col).getSide(Direction.TOP).getValue()==0)) return false; 
			}	
			
			if(board.isOccupied(row - 1, col)){
				int topVal = piece.getSide(Direction.TOP).getValue();
				if(!(topVal + board.getPiece(row-1,col).getSide(Direction.BOTTOM).getValue()==0)) return false;
			}	
			
			if(board.isOccupied(row, col + 1)){
				int rightVal = piece.getSide(Direction.RIGHT).getValue();
				if(!(rightVal + board.getPiece(row,col + 1).getSide(Direction.LEFT).getValue()==0)) return false;
			}
			
			if(board.isOccupied(row, col- 1)){
				
				int leftVal = piece.getSide(Direction.LEFT).getValue();
				if(!(leftVal + board.getPiece(row,col - 1).getSide(Direction.RIGHT).getValue()==0)) return false;
			
			}
			
			return true;
		}
		
		return false;
		
	}
	
	/**
	 * Just a getter for the Puzzle's list of unused Pieces.
	 * 
	 * @return the list of Unused Pieces.
	 */
	public List<Piece> getUnused(){
		return unusedList;
	}

	/**
	 * @param row
	 * @param col
	 * 
	 * Just a getter that for a specific piece on the Board.
	 * 
	 * @return the Piece at the indicated position on the Puzzle's Board.
	 */
	public Piece getPiece(int row, int col){
		return board.getPiece(row, col);
	}
	
	/**
	 * @param piece
	 * @param row
	 * @param col
	 * 
	 * This method puts a Piece in the Board, removes it from the unusedList, adds the Piece that was in the original spot to the unusedList, if there 
	 * was one, and then returns the Piece that used to be in the spot.
	 * 
	 * @return the Piece that used to be in the spot that was set. Null is returned if nothing was there originally.
	 */
	public Piece setPiece(Piece piece, int row, int col){
		Piece removedPiece = board.setPiece(piece, row, col);
		unusedList.remove(piece); 
		
		if(removedPiece != null){
			unusedList.add(removedPiece);
		}
		
		return removedPiece;
	}
	
	/**
	 * @param row
	 * @param col
	 * 
	 * This method removes the piece from the Puzzle's Board in the indicated position.
	 * 
	 * @return the Piece that was removed. Null is returned if nothing was there originally.
	 */
	public Piece removePiece(int row, int col){ 
		Piece removedPiece = board.removePiece(row, col);
		
		if(removedPiece != null){
			unusedList.add(removedPiece);
		}
		
		return removedPiece;
	}
	
	/**
	 * Checks whether the Puzzle is solved yet. While there is a faster way to check by only checking every other Piece, that method
	 * could mess up given the way that doesFit says being next to an empty space is okay. In any case, this version of isSolved
	 * is satisfactory in terms of runtime for our purposes.
	 * 
	 * @return true if the Puzzle is solved, false if the Puzzle isn't solved.
	 */
	public boolean isSolved(){ //Possible weirdness
		int numRows = board.getRows();
		int numCols = board.getCols();
		
		for(int row = 0; row < numRows; row++){
			
			for(int col = 0; col < numCols; col++){
				Piece pieceChecked = board.getPiece(row, col); 
				
				if(pieceChecked == null || !doesFit(pieceChecked, row, col)){ 
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Removes all pieces from the board and adds those pieces to the unusedList.
	 */
	public void reset(){
		int numRows = getRows();
		int numCols = getCols();
		
		for(int i = 0; i < numRows; i++ ){
			for(int j = 0; j < numCols; j++){
				if(board.isOccupied(i, j)){
					unusedList.add(board.removePiece(i, j));
				}
			}
		}
	}
		
	/**
	 * The Puzzle solve method. Calls solveHelper after reseting the Puzzle and making a copy of the Pieces so nothing weird 
	 * happens to unusedList.
	 */
	public void solve(){ //What if no solution?
		reset();
		List<Piece> sentPieces = new ArrayList<Piece>(); //To avoid stupid nulls creeping in from being linked to unusedList
		for(int i = 0 ; i < unusedList.size(); i++){
			sentPieces.add(unusedList.get(i));
		}
		
		solveHelper(0,0, sentPieces); //Should probably reset before solving to get the unusedListback
	}
		
	/**
	 * @param row
	 * @param col
	 * @param unusedListUpdated
	 * 
	 * This method does the main work in solving the Puzzle. The logic is similar to a selection sort but the method short circuits
	 * once the Puzzle is solved. For each spot, each piece and each piece's orientation must be checked which seems inefficient, but
	 * the runtime is still very low (<1 seconds) for a 3x3 Jigsaw Puzzle.
	 * 
	 * Note that there are still Sysouts from testing. These are very helpful to see where the solve method is if it messes up and are left in
	 * in case further change to the method becomes necessary.
	 * 
	 * Weird things may happen if the List given contains nulls.
	 * 
	 * @return whether or not the Puzzle is solved.
	 */
	public boolean solveHelper(int row, int col, List<Piece> unusedListUpdated){ //No valid checks since only used with my own params. Also order?
		if(unusedListUpdated.size() == 0){
			return true; //Puzzle is already solved! Don't go on.
		}

		int currentSize = unusedListUpdated.size(); //Helper variable so no need to do the size() method multiple times
		
		for(int i = 0; i < currentSize; i++){ //Go through the pieces left for a specific spot.
			
			Piece checkedPiece = unusedListUpdated.get(i); //This is the piece we are checking
			
			//	System.out.println("Start of spot sequence: Size of unused Pieces List: " + unusedListUpdated.size());
			//	System.out.println("Number of Pieces in the List fully checked so far: " + i);
			//	System.out.println("Spot being checked: (" + col + "," + row + ")"); 

			for(int numberOfRotates = 0; numberOfRotates < 4; numberOfRotates++){  //Checks all orientations of a specific Piece
				
				if(doesFit(checkedPiece, row, col)){ //Does the Piece at this rotation fit?
					
					//	System.out.println("This Piece Fits!");

					List<Piece> unusedListTry = new ArrayList<Piece>(unusedListUpdated); 
					unusedListTry.remove(checkedPiece); //Copied List for next spot through recursion

					//	System.out.println("Passed on: " + unusedListTry);

					setPiece(checkedPiece, row, col); //Sets the piece into the board.

					//	System.out.println("The Board is now: " + board.toString());

					int nextRow = row;
					int nextCol = col + 1;
					if(col == getCols() - 1){ //Finding the next spot to check
						nextRow = row + 1;
						nextCol = 0;
					}

					boolean alreadySolved = solveHelper(nextRow, nextCol, unusedListTry); //Moves to next spot. 
					
					//Only continues once a sequence has been exhausted
					
					if(!alreadySolved){ //if the sequence didn't work, remove the Piece
					
						//	System.out.println("That Sequence didn't work. :(");
						removePiece(row,col);
					}
					
					//Otherwise, return true!
					else{ //Failed to catch an if(alreadySolved) here...
						return true;
					}

					//	System.out.println(unusedListUpdated); 
					//	System.out.println("The actual data: " + unusedList);
					//	System.out.println("We're going back to when the size of unused Pieces List = " + unusedListUpdated.size());
					//	System.out.println("Number of Pieces in the List fully checked so far: " + i);
					//	System.out.println("spot being checked: (" + col + "," + row + ")"); 

				}
				
				checkedPiece.rotateCounterClockwise(); //If the piece failed, try rotating it.
				
				//	System.out.println("Rotating piece...");
				
			}
		}	
		return false; //Necessary to make the boolean method be happy
	}
	
	/**
	 * Just a toString for testing.
	 */
	public String toString(){
		System.out.println("-----------------------------------------------------------------------------------");
		String answer = new String();
		answer += "The Board has: " + board.toString() + "\n\n";
		
		
		answer += "The Unused Pieces are:";
		for(int i = 0; i < unusedList.size(); i++){
			
			if(unusedList.get(i) != null){
				answer += " " + unusedList.get(i).toString() + "\n";
			}
		}
		
		return answer;
	}
	
	
	/**
	 * @param args
	 * 
	 * Just a main method for testing.
	 */
	public static void main(String[] args){
		Piece one = new Piece(Side.CLUB_OUT, Side.HEART_OUT, Side.DIAMOND_IN, Side.CLUB_IN);
		Piece two = new Piece(Side.SPADE_OUT, Side.DIAMOND_OUT, Side.SPADE_IN, Side.HEART_IN);
		Piece three = new Piece(Side.HEART_OUT, Side.SPADE_OUT, Side.SPADE_IN, Side.CLUB_IN);
		Piece four = new Piece(Side.HEART_OUT, Side.DIAMOND_OUT, Side.CLUB_IN, Side.CLUB_IN);
		Piece five = new Piece(Side.SPADE_OUT, Side.SPADE_OUT, Side.HEART_IN, Side.CLUB_IN);
		Piece six = new Piece(Side.HEART_OUT, Side.DIAMOND_OUT, Side.DIAMOND_IN, Side.HEART_IN);
		Piece seven = new Piece(Side.SPADE_OUT, Side.DIAMOND_OUT, Side.HEART_IN, Side.DIAMOND_IN);
		Piece eight = new Piece(Side.CLUB_OUT, Side.HEART_OUT, Side.SPADE_IN, Side.HEART_IN);
		Piece nine = new Piece(Side.DIAMOND_OUT, Side.CLUB_OUT, Side.CLUB_IN, Side.DIAMOND_IN);
		
		Piece ten = new Piece(Side.CLUB_IN, Side.HEART_IN,Side.DIAMOND_OUT, Side.DIAMOND_OUT);
		Piece again = new Piece(Side.CLUB_IN, Side.HEART_IN, Side.CLUB_OUT, Side.DIAMOND_IN);
		Piece finalP = new Piece(Side.CLUB_IN, Side.HEART_IN, Side.CLUB_OUT, Side.HEART_OUT);
		
		
		ArrayList<Piece> twobytwo = new ArrayList<Piece>();
		twobytwo.add(nine);
		twobytwo.add(ten);
		twobytwo.add(again);
		twobytwo.add(finalP);
		
		ArrayList<Piece> pieces = new ArrayList<Piece>();
		pieces.add(one);
		pieces.add(two);
		pieces.add(three);
		pieces.add(four);
		pieces.add(five);
		pieces.add(six);
		pieces.add(seven);
		pieces.add(eight);
		pieces.add(nine);
		
		Puzzle woohoo = new Puzzle(3, 3, pieces);
		
		System.out.println("-------------------------------------Three by Three (Actual) Puzzle--------------------------------------");
		
		System.out.println(woohoo);
		
		System.out.println("isSolved result: " + woohoo.isSolved()); 
		
		woohoo.setPiece(one, 0, 0);

		System.out.println("After one setPiece: " + woohoo);
		
		woohoo.reset();
		
		System.out.println("After Reset: " + woohoo);
		
		System.out.println("-------------------------------------Solving...--------------------------------------");
		woohoo.solve();
		
		System.out.println("After Solve: " + woohoo); 
		System.out.println("isSolved result: " + woohoo.isSolved()); 
		System.out.println("Does Piece finalP fit in the center of woohoo?: " + woohoo.doesFit(finalP, 1, 1));
		
		System.out.println("-------------------------------------Two By Two Puzzle--------------------------------------");

		Puzzle second = new Puzzle(2, 2, twobytwo);
		
		System.out.println(second);
		
		System.out.println("isSolved result: " + second.isSolved()); 
		
		second.setPiece(nine, 0, 0);

		System.out.println("After one setPiece: " + second);
		
		second.reset();
		
		System.out.println("After Reset: " + second);
		
		System.out.println("-------------------------------------Solving...--------------------------------------");
		second.solve();
		
		System.out.println("After Solve: " + second); 
		System.out.println("isSolved result: " + second.isSolved()); 
		System.out.println("Does Piece finalP fit in the center of second?: " + second.doesFit(finalP, 1, 1));
	}
	
}
