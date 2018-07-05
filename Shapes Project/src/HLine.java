//This creates a horizontal line with a certain length
public class HLine extends Line{
	
	//This creates a horizontal line with zero length.
	HLine() {} 
	
	//This creates a horizontal line given a certain length
	HLine(int length){
		super(length);
	}
	
	//This creates a horizontal line given a certain length and paint character
	HLine(int length, char paintCharacter){
		super(length, paintCharacter);
	}

	//This paints the line on the screen at location (x,y)
	public void paintOn(Screen screen, int x, int y) {
		for (int i = 0; i < this.getLength(); i++)
			screen.paintAt(x+i, y, this.getPaintCharacter());
	}

	//This paints the line on the screen at (0,0)
	public void paintOn(Screen screen) {
		paintOn(screen, 0, 0);
	}
	
	//This tests horizontal lines, two of which have length five and one of
	//which has length zero. One has the default paint character, while 
	//the other has the paint character of '/'. They test 
	//the locations (0,0) (5,5) and (10,10)
	public static void main(String[] args){
		Screen screen = new Screen(25,25,'%');
		Line line = new HLine(5);
		Line line2 = new HLine(5,'/');
		Line line3 = new HLine();
		line.paintOn(screen, 5, 5);
		line.paintOn(screen);
		line2.paintOn(screen,10,10);
		line3.paintOn(screen);
		screen.draw();
	}
}
