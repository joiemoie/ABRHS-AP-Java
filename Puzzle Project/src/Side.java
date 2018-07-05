
/**
 * Enum Side
 * 
 * Stores the side types with int values. Suits In and Out are opposite so we can tell if they match simply by adding and checking if it equals zero.
 * 
 * @author Sean
 *
 */
public enum Side {
	/**
	 * Side with an inward Club.
	 */
	CLUB_IN(1),
	
	/**
	 * Side with an outward Club.
	 */
	CLUB_OUT(-1),
	
	/**
	 * Side with an inward Spade.
	 */
	SPADE_IN(2),
	
	
	/**
	 * Side with an outward Spade.
	 */
	SPADE_OUT(-2),
	
	/**
	 * Side with an inward Heart.
	 */
	HEART_IN(3),
	
	/**
	 * Side with an outward Heart.
	 */
	HEART_OUT(-3),
	
	/**
	 * Side with an inward Diamond. 
	 */
	DIAMOND_IN(4),
	
	/**
	 * Side with an outward Diamond. 
	 */
	DIAMOND_OUT(-4);
	
	private int value;

	/**
	 * @param enumValue
	 * 
	 * Constructor of a Side.
	 */
	Side(int enumValue){
		this.value = enumValue;
	}

	/**
	 * Just a getter for Side's value.
	 * 
	 * @return integer value of the Side
	 */
	public int getValue(){
		return value;
	}
	
}
