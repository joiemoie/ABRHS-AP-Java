//This class creates a Polygon object which extends the Shape Parent class
public class Polygon extends Shape {
	
	//This is the number of sides to the polygon
	private int numSides;
	
	//Creates a polygon given a center, angle, and number of sides.
	public Polygon(Point center, double theta, int numSides){
		super(center, theta);
		this.numSides = numSides;
	}
	
	//Creates a polygon given an angle and number of sides at point (0,0)
	public Polygon(double theta, int numSides){
		
		this(new Point(0, 0), theta, numSides);
		
	}
	
	//Creates a polygon given a center and number of sides with angle of 0
	public Polygon(Point center, int numSides){
		
		this(center, 0, numSides);
		
	}
	
	//creates a polygon given a number of sides with angle 0 at point (0,0)
	public Polygon(int numSides){
		this(new Point(0,0), 0, numSides);
	}

	//prints the angle, center, and number of sides to the polygon. It also prints the vertices coordinates
	public String toString(){
		
		String s = "the polygon has theta " + getTheta() + ", center is " + getCenter() + ", numsides is " + getNumSides();
		
		s += "\n the vertices of the polygon are" ;
		
		Point[] vertices = calculateVertices(200);
		
		for(int i = 0; i< getNumSides(); i++){
			s += "(" + vertices[i].getX() +","+ vertices[i].getY() + "), " ;
			
		}
		
		return s;
	}
	
	//gets the number of sides
	public int getNumSides(){
		
		return numSides;
		
	}
	
	//calculates the vertices to the polygon
	public Point[] calculateVertices(double radius){
		
		Point[] vertices = new Point[numSides];
		
		Point center = this.getCenter();
		
		double theta = getTheta();

		for(int i = 0; i < numSides; i++){
			
			double x = center.getX() + radius * Math.sin(Math.PI/numSides + (i + 1)  * 2 * Math.PI/numSides + theta);
			
			double y = center.getY() - radius * Math.cos(Math.PI/numSides + (i+ 1) * 2 * Math.PI/numSides + theta);
			
			Point vertex = new Point(x, y);
			
			vertices[i] = vertex;
			
		}
		
		return vertices;
		
	}
	
	public static void main(String[] args){
		
		Polygon poly = new Polygon(4);
		
		System.out.println(poly.toString());
	
	}
	
	
	
	
	

}

