//This class creates a Box object with a certain width and height
public class Box extends Shape{
	int width;
	int height;
	
	//This creates a Box with length and width of 0
	Box(){}
	
	//This creates a Box given a certain length and width
	Box(int width, int height){
		super();
		this.width = width;
		this.height = height;
	}
	
	//This creates a Box with a certain length, width, and paint character
	Box(int width, int height, char paintCharacter){
		super(paintCharacter);
		this.width = width;
		this.height = height;
	}
	
	//This paints the box on the screen at a certain (x,y) location
	public void paintOn(Screen screen, int x, int y) {
		for(int i = 0; i<width; i++)
			for(int j = 0; j<height; j++)
				screen.paintAt(x+i, y+j, this.getPaintCharacter());
	}

	//This paints the box on the screen at (0,0)
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

	//This tests boxes, two of which have dimensions of 5x5 and one
	//of which has dimensions of 0x0. One takes the default paint
	//character while the other has a paint character of '/'.
	//This tests locations (5,5), (10,10), and (0,0).
	public static void main(String[] args){
		Screen screen = new Screen(25,25,'%');
		Box box = new Box(5,5);
		Box box2 = new Box(5,5,'/');
		Box box3 = new Box();
		box.paintOn(screen, 5, 5);
		box.paintOn(screen);
		box2.paintOn(screen,10,10);
		box3.paintOn(screen);
		screen.draw();
	}
}
