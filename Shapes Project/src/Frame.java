//This creates a Frame object with a certain width and height
public class Frame extends Shape{
	int width;
	int height;
	
	//This creates a frame with zero length and width
	Frame(){} 
	
	//This constructs a frame given a width and height
	Frame(int width, int height){
		super();
		this.width = width;
		this.height = height;
	}
	
	//This constructs a frame given a width, height, and paint character
	Frame(int width, int height, char paintCharacter){
		super(paintCharacter);
		this.width = width;
		this.height = height;
	}

	//This paints the frame on the screen at location (x,y)
	public void paintOn(Screen screen, int x, int y) {
		HLine s1 = new HLine(width,this.getPaintCharacter());
		VLine s2 = new VLine(height,this.getPaintCharacter());
		s1.paintOn(screen, x, y);
		s1.paintOn(screen, x, y+height-1);
		s2.paintOn(screen, x, y);
		s2.paintOn(screen, x+width-1, y);
	}

	//This paints the frame on the screen at (0,0)
	public void paintOn(Screen screen) {
		paintOn(screen,0,0);
	}
	
	//Gets the width
	public int getWidth(){
		return width;
	}
	
	//Gets the height
	public int getHeight(){
		return height;
	}
	
	//This tests frames, two of which have dimensions of 5x5 and one
	//of which has dimensions of 0x0. One takes the default paint
	//character while the other has a paint character of '/'.
	//This tests locations (5,5), (10,10), and (0,0).
	public static void main(String[] args){
		Screen screen = new Screen(25,25,'%');
		Frame frame = new Frame(5,5);
		Frame frame2 = new Frame(5,5,'/');
		Frame frame3 = new Frame();
		frame.paintOn(screen, 5, 5);
		frame.paintOn(screen);
		frame2.paintOn(screen,10,10);
		frame3.paintOn(screen);
		screen.draw();
	}
}
