//This class creates a vertical line object with a particular length
public class VLine extends Line{
	
	//This creates a vertical line with length 0
	VLine(){}
	
	//This creates a vertical line given a certain length
	VLine(int length){
		super(length);
	}
	
	//This creates a vertical line with length with a certain length and desired paint character
	VLine(int length, char paintCharacter){
		super(length, paintCharacter);
	}

	//This paints the vertical line on the screen at (x,y) location
	public void paintOn(Screen screen, int x, int y) {
		for (int i = 0; i < this.getLength(); i++)
			screen.paintAt(x, y + i, this.getPaintCharacter());
	}

	//This paints the vertical line on the screen at (0,0)
	public void paintOn(Screen screen) {
		for (int i = 0; i < this.getLength(); i++)
			screen.paintAt(0, i, this.getPaintCharacter());
	}

	//This tests vertical lines, two of which have length five and one of
	//which has length zero. One has the default paint character, while 
	//the other has the paint character of '/'. They test 
	//the locations (0,0) (5,5) and (10,10)
	public static void main(String[] args){
		Screen screen = new Screen(25,25,'%');
		Line line = new VLine(5);
		Line line2 = new VLine(5,'/');
		Line line3 = new VLine();
		line.paintOn(screen, 5, 5);
		line.paintOn(screen);
		line2.paintOn(screen,10,10);
		line3.paintOn(screen);
		screen.draw();
	}
}
