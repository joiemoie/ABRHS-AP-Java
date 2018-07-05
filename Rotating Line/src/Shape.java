//This abstract class is the base class for shapes with a center and angle
public abstract class Shape {
	
	private Point center;
	private double theta;

	//Creates a shape given a center and angle
	public Shape(Point center, double theta){
		this.center = center;
		this.theta = theta;
	}
	
	//Creates a shape given a center with angle 0
	public Shape(Point center){
		this(center,0);
	}
	
	//Creates a shape given an angle at (0,0)
	public Shape(double theta){
		this(new Point(0,0), theta);
	}
	
	//Creates a shape with center (0,0) and angle 0
	public Shape(){
		this(new Point(0,0), 0);
	}
	
	//gets the center
	public Point getCenter() {
		return center;
	}

	//sets the center
	public void setCenter(Point center) {
		this.center = center;
	}

	//gets the angle
	public double getTheta() {
		return theta;
	}
	
	//sets the angle
	public void setTheta(double theta) {
		this.theta = theta;
	}
	

}
