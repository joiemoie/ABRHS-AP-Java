
/**
 * Piece.java
 * 
 * This class contains the information for a Piece object, namely the side values and its orientation. It also has methods related to a piece like rotating a piece.
 * Note that numberOfRotates represents the number of counterclockwise rotations.
 * 
 * @author Sean, Seth + Katie Edited/Tested.
 *
 */
public class Piece {
	private int numberOfRotates; 
	private Side[] sides;

	/**
	 * @param top
	 * @param right
	 * @param bottom
	 * @param left
	 * 
	 * Just a Piece constructor. Assigns clockwise starting at the top.
	 */
	public Piece(Side top, Side right, Side bottom, Side left) {
		sides = new Side[4];
		sides[0] = top;
		sides[1] = right;
		sides[2] = bottom;
		sides[3] = left;
	}
	
	/**
	 * Rotates the piece 90 degrees clockwise. Specifically, it changes numberOfRotates value. (3 counterclockwise rotations = 1 clockwise rotation)
	 */
	public void rotateClockwise() {
		numberOfRotates += 3;
		numberOfRotates %= 4; //Seth's credit
	}
	
	/**
	 * Rotates the piece 90 degrees counterclockwise. Specifically, it changes numberOfRotates value.
	 */
	public void rotateCounterClockwise() {
		numberOfRotates += 1;
		numberOfRotates %= 4;

	}
	
	/**
	 * @param direction
	 * 
	 * Just gets the enum Side for a specific direction.
	 * 
	 * @return the Side that corresponds to a direction(spot) on the piece.
	 */
	public Side getSide(Direction direction) {
		int actualIndex = (direction.getValue() + numberOfRotates) % 4;

		return sides[actualIndex]; 
	}

	/**
	 * @return the numberOfRotates (as clockwise Rotations)
	 */
	public int getOrientation() { 
		return (4 - numberOfRotates) % 4;
	}
	
	/**
	 * Just a toString for testing.
	 */
	public String toString() {
		String answer = new String();
			answer += "Number of Rotations = " + getOrientation();
			answer += ". Top: " + getSide(Direction.TOP);
			answer += ". Right: " + getSide(Direction.RIGHT);
			answer += ". Bottom: " + getSide(Direction.BOTTOM);
			answer += ". Left: " + getSide(Direction.LEFT);
		
		return answer;
	}

	/**
	 * @param args
	 * 
	 * Just a main method for testing.
	 */
	public static void main(String[] args) {
		System.out.println(-3 % 4);
		
		Piece one = new Piece(Side.CLUB_IN, Side.CLUB_OUT, Side.DIAMOND_IN, Side.DIAMOND_OUT);
		System.out.println(one.toString());
		one.rotateClockwise();
		
		System.out.println(one.toString());
		
	}

}
