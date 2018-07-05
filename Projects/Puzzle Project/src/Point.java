//This class stores 2D coordinates
public class Point {
	
	double x;
	double y;
	
	//This creates a point given an x and a y
	public Point(double x, double y){
		
		this.x = x;
		this.y = y;
	}
	
	//This defaults the x and y coordinates to 0
	public Point(){
		this(0,0);
	}

	//Gets the x coordinate
	public double getX() {
		return x;
	}

	//sets the X coordinate
	public void setX(double x) {
		this.x = x;
	}

	//Gets the y coordinate
	public double getY() {
		return y;
	}

	//sets the y coodrinate
	public void setY(double y) {
		this.y = y;
	}
	
	//prints out the x and y coodinates
	public String toString(){
		return "this is a point " + "(" + this.getX()+ "," + this.getY()+ ")" ;
	}
	
	public static void main(String[] args){
		Point point = new Point();
		
		point.toString();
	}

}
