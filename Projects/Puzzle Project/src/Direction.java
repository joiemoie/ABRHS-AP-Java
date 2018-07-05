
/**
 * Enum Direction
 * 
 * Stores the Directions of a Piece as integer values. Goes clockwise starting from TOP.
 * 
 * @author Sean
 *
 */
public enum Direction{
	
	/**
	 * Refers to the Side on the Top.
	 */
	TOP(0),
	
	/**
	 * Refers to the Side on the Right.
	 */
	RIGHT(1),
	
	/**
	 * Refers to the Side on the Bottom.
	 */
	BOTTOM(2),
	
	/**
	 * Refers to the Side on the Left.
	 */
	LEFT(3);

	private int value;

	/**
	 * @param enumValue
	 * 
	 * Constructor for Direction.
	 */
	Direction(int enumValue){
		this.value = enumValue;
	}

	/**
	 * Just a getter for Direction's value.
	 * 
	 * @return integer value of the Direction
	 */
	public int getValue(){
		return value;
	}
}
