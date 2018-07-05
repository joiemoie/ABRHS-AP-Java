//This abstract class creates a generic shape
public abstract class Shape {
	
	private char paintCharacter;
	
	//This creates a shape given a particular paint character
	public Shape(char paintCharacter){
		this.paintCharacter = paintCharacter;
	}
	
	//This creates a shape with default paint character '*'.
	public Shape(){
		this.paintCharacter = '*';
	}
	
	//This paints the shape on the screen at (x,y) location.
	abstract void paintOn(Screen screen, int x, int y);
	
	//This paints the shape at (0,0)
	abstract void paintOn(Screen screen);
	
	//This returns the paint character.
	public char getPaintCharacter(){
		return paintCharacter;
	}
	
	//This sets the paint character.
	public char setPaintCharacter(char paintCharacter){
		return this.paintCharacter = paintCharacter;
	}
}
