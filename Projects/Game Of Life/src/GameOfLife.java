import java.util.ArrayList;

/*
 * This class will take a GameBoard object with cells either dead or alive.
 * Dead cells contain the value 0, while alive cells contain the value of 1.
 * After each generation, each cell will change to a different cell using the
 * following rules:
 * 1. If a living cell has less than 2 or more than 3 living neighbors, it die.
 * 2. If a dead cell has exactly 3 living neighbors, it will come to life.
 * 3. Otherwise, the cell will remain the same.
 * 
 * By Joseph Liba
 */
public class GameOfLife {
	private GameBoard board;
	private ArrayList<GameBoard> prevGenBoard = new ArrayList<GameBoard>();
	private int genNum; // generation number
	private ArrayList<Integer> cellsAlive = new ArrayList<Integer>();
	
	public GameOfLife(GameBoard board){
		this.board = board;
		prevGenBoard.add(board);
		genNum = 0;
		cellsAlive.add(getCellsAlive());
	}
	
	//constructs a GameOfLife given a number of rows and columns
	public GameOfLife(int numRows, int numCols){
		this(new GameBoard(numRows, numCols));
	}
	
	//constructs a GameOfLife given a certain board
	public GameOfLife(int[][] board){
		this(new GameBoard(board));
	}
	
	//counts the number of cells alive
	public int getCellsAlive(){
		int total = 0;
		for(int i = 0; i<board.getRows(); i++)
			for(int j = 0; j<board.getCols(); j++)
				total+=getCell(i, j);
		return total;
	}
	
	//adds a the number of cells alive in the last position for cellsAlive ArrayList
	public int addCellsAlive(int num){
		return cellsAlive.set(cellsAlive.size()-1, cellsAlive.get(cellsAlive.size()-1)+1);
	}
	
	//returns cellsAlive
	public ArrayList<Integer> getArrayListCellsAlive(){
		return cellsAlive;
	}
	//sets the cell at the specified location
	public void setCell(int rowNum, int colNum, int newValue){
		board.setPiece(rowNum, colNum, newValue);
	}
	
	//gets the value of the cell at a specified location
	public int getCell(int rowNum, int colNum){
		return board.getPiece(rowNum, colNum);
	}

	//returns true if the cell is alive and false if it is dead
	public boolean isAlive(int rowNum, int colNum){
		if(board.getPiece(rowNum, colNum)==1)
			return true;
		else return false;
	}
	//returns the game board
	public GameBoard getBoard(){
		return board;
	}
	//randomly assigns a value of either 0 or 1 to each cell on the board
	public void randomize(){
		cellsAlive.clear();
		for(int rowNum = 0; rowNum<board.getRows(); rowNum++)
			for(int colNum = 0; colNum<board.getCols(); colNum++){
				if(Math.random()<.5)
					setCell(rowNum, colNum, 0);
				else setCell(rowNum, colNum, 1);
			}
		genNum = 0;
		cellsAlive.add(getCellsAlive());
	}
	
	//clears the game board and resets the generation number to zero
	public void clear(){
		board.clear();
		genNum = 0;
		cellsAlive.clear();
		cellsAlive.add(0);
	}
	
	//counts the number of cells adjacent to a given cell with the value of 1
	private int numNeighbors(int rowNum, int colNum){
		int numNeighbors = 0;
		for(int row = rowNum - 1; row<rowNum+2; row++)
			for(int col = colNum - 1; col<colNum+2; col++)
				if(board.isValid(row, col)&&(getCell(row,col)==1))
					numNeighbors += 1;
		numNeighbors -= getCell(rowNum,colNum);
		return numNeighbors;
	}
	
	/*
	 * Determines the state of the cell in the next generation.
	 */
	private int nextState(int rowNum, int colNum){
		int cellValue = getCell(rowNum, colNum);
		int numNeighbors = numNeighbors(rowNum, colNum);
		if(cellValue == 1 && (numNeighbors<2 || numNeighbors>3))
				return 0;
		if(cellValue == 0 && (numNeighbors==3))
			return 1;
		else return cellValue;
	}
	
	//returns to to the previous generation. This does not work at generation 0.
	public void prevGen(){
		if(genNum>0){
			prevGenBoard.remove(genNum);
			cellsAlive.remove(genNum);
			genNum--;
			board = prevGenBoard.get(genNum);
		}
	}
	
	//restarts the game of life to the first generation
	public void restart(){
		genNum = 0;
		GameBoard tempBoard = prevGenBoard.get(0);
		int tempCellsAlive = cellsAlive.get(0);
		prevGenBoard.clear();
		prevGenBoard.add(tempBoard);
		cellsAlive.clear();
		cellsAlive.add(tempCellsAlive);
		board = tempBoard;
	}
	//Increases the generation number by 1 and changes all the cells on the board
	//according to the rules of the Game of Life.
	public void nextGen(){
		GameBoard tempBoard = new GameBoard(board.getRows(),board.getCols());
		for(int rowNum = 0; rowNum<board.getRows(); rowNum++)
			for(int colNum = 0; colNum<board.getCols(); colNum++)
				tempBoard.setPiece(rowNum, colNum, nextState(rowNum,colNum));
		board = tempBoard;
		genNum++;
		prevGenBoard.add(board);
		cellsAlive.add(getCellsAlive());
	}
	
	
	//Returns the generation number
	public int getGenNum(){
		return genNum;
	}
	
	//Prints the board and the generation number
	public String toString(){
		return board.toString() + "\n" + "Gen Number: " + getGenNum();	
	}
	
	public static void main(String[] args) {
		GameOfLife hi = new GameOfLife(5,5);
		hi.randomize();
		System.out.println(hi.board.getPiece(0, 0));
		System.out.println(hi);
		hi.nextGen();
		System.out.println(hi);
		hi.prevGen();
		System.out.println(hi);
	}

}
