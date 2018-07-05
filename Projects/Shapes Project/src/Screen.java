// This class will create a Screen object which different shapes can be drawn on.
public class Screen {
	
	private char borderCharacter;
	private int width;
	private int height;
	private char[][] grid; //the grid does not include the border
	
	//creates a screen with a certain width, height, and border character
	Screen(int width, int height, char borderCharacter){
		this.width = width;
		this.height = height;
		this.borderCharacter = borderCharacter;
		this.grid = new char[height][width];
	}
	
	//creates a default screen with width 15, height 10, and border character %
	Screen(){
		this.width = 15;
		this.height = 10;
		this.borderCharacter = '%';
		this.grid = new char[height][width];
	}
	
	//draws the screen
	public void draw(){
		//this draws the borders
		for(int i = 0; i<width+2; i++)
			System.out.print(borderCharacter);
		System.out.println();
		for(int i = 1; i<height+1; i++){
			System.out.print(borderCharacter);
			for(int j = 1; j<width+1; j++)
				System.out.print(grid[i-1][j-1]); //this fills in the grid with the paint character
			System.out.print(borderCharacter);
			System.out.println();
		}
		for(int i = 0; i<width+2; i++)
			System.out.print(borderCharacter);
	}
	
	//removes all pixels drawn on the grid
	public void clearScreen(){
		grid = new char[height][width];
	}
	
	//this paints a pixel at a specified location (x,y)
	public void paintAt(int x, int y, char paintCharacter){
		if(isValid(x,y))
			grid[y][x] = paintCharacter;
	}
	
	//this checks to see if a location is on the grid
	public boolean isValid(int x, int y){
		if(0<=x && x<width & 0<=y && y<height)
			return true;
		else return false;
	}
	public static void main(String[] args) {

	}

}
