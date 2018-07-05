//this abstract class creates an object Line with a specific length
public abstract class Line extends Shape{
	private int length;
	
	//creates a line with length 0
	public Line(){
		
	}
	
	//creates a line given a particular length
	public Line(int length){
		super();
		this.length = length;
	}
	
	public Line(int length, char paintCharacter){
		super(paintCharacter);
		this.length = length;
	}
	
	//returns the length
	public int getLength(){
		return length;
	}
	
	



}
