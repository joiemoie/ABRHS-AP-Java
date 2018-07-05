//This class creates a Line object which extends the Shape Parent class
//It has no new methods or data fields
public class Line extends Shape{
	
	//This creates a line with a given center and angle
	public Line(Point center, double theta){
		super(center, theta);
	}
	
	//This creates a line with a certain angle at (0,0)
	public Line(double theta){
		
		this(new Point(0, 0), theta);
		
	}
	
	//This creates a line with angle zero at a defined center.
	public Line(Point center){
		
		this(center, 0);
		
	}
	
	//This creates a line with center (0,0) and angle 0
	public Line(){
		this(0);
	}

	//This toString will give the lines angle and center
	public String toString(){
		
		return "the line has theta " + getTheta() + "and the center is " + getCenter();
		
	}
	
	public static void main(String[] args){
		
		Line line = new Line();
		
		System.out.println(line.toString());
		
		
	}
	
	
	
	
	

}
