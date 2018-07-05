//This class creates a new text line. It is not a shape so does not extend the Shape class.
public class TextLine{
	String text;
	
	//This creates a TextLine given a certain text.
	TextLine(String text){
		this.text = text;
	}

	//This paints the textline on the screen at particular (x,y) location
	public void paintOn(Screen screen, int x, int y) {
		for(int i = 0; i<text.length(); i++)
			screen.paintAt(x+i, y, text.charAt(i));
	}

	//This paints the textline on the screen at (0,0)
	public void paintOn(Screen screen) {
		paintOn(screen,0,0);
	}
	
	//Gets the text string
	public String getText(){
		return text;
	}
	
	//This tests the textLine "Hello World" at (0,0) and (5,5)
	public static void main(String[] args){
		Screen screen = new Screen(25,25,'%');
		TextLine textLine = new TextLine("Hello World");
		textLine.paintOn(screen, 5, 5);
		textLine.paintOn(screen);
		screen.draw();
	}
}
