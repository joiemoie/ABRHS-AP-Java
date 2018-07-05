import java.awt.Graphics;
import javax.swing.JComponent;

//This class creates a JComponent representing the shape that can be drawn on the display
public class ShapeGraphics extends JComponent{

	//This class takes in a shape and a display
	private Shape shape;
	private Display display;
	
	//This creates a ShapeGraphics given a shape and display
	public ShapeGraphics(Shape shape, Display display){
		this.shape = shape;
		this.display = display;
	}
	
	//This returns the shape. This is needed since the Display class
	//may change the shape that is stored within ShapeGraphics
	public Shape getShape(){
		return shape;
	}
	
	//This sets the shape.
	public void setShape(Shape shape){
		this.shape = shape;
	}
	
	//paints the shape
	public void paintComponent(Graphics g){
		//This stores the greater of either width or height
		double width = (int) (display.getFrameWidth());
		double height = (int) (display.getFrameHeight());
		int max = (int) ((width > height)? width:height);
		
		Point center = shape.getCenter();
		
		//This draws a line if the shape is a line. The length is greater than "max"
		if(shape instanceof Line){
			
			double theta = shape.getTheta();
			g.drawLine((int)(center.getX() - max*Math.cos(theta)), (int)(center.getY() - max*Math.sin(theta)),
					(int)(center.getX() + max*Math.cos(theta)), (int)(center.getY() + max*Math.sin(theta)));
			}
		
		//This draws a polygon if the shape is a polygon. 
		if (shape instanceof Polygon){
			int numSides = ((Polygon) shape).getNumSides();
			Point[] vertices = ((Polygon) shape).calculateVertices(max/4);
			
			//This draws lines connecting the vertices
			for(int i = 0; i < numSides - 1; i++){
				
				g.drawLine((int) vertices[i].getX(), (int) vertices[i].getY(), 
						(int) vertices[i+1].getX(), (int) vertices[i+1].getY());
			}
			
			g.drawLine( (int) vertices[numSides-1].getX(), (int) vertices[numSides -1].getY(), 
					(int) vertices[0].getX(), (int) vertices[0].getY());
				
		}
		
	}
	
}

