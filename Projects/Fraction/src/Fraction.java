/*
 * This program will allow the user to form a fraction using an input of
 * either one or two integers. The user will be able to perform the following
 * operations on the fractions: addition, subtraction, multiplication, and division.
 * The program will then print the fractions along with the resulting fraction
 * formed after the operation has been performed onto the fractions.
 */

public class Fraction {
	// data fields
	private int numerator;
	private int denominator;
	public int toString;
	
	//constructs a whole number given a single integer input
	public Fraction(int numerator){
		this.numerator = numerator;
		this.denominator = 1;
	}
	
	//constructs a fraction given two integer inputs
	public Fraction(int numerator, int denominator){
		if (denominator < 0){
			this.denominator = denominator * (-1);
			this.numerator = numerator * (-1);
		}
		else{
			this.numerator = numerator;
			this.denominator = denominator;
		}
	}

	//finds the greatest common factor
	public int GCD(int a, int b) {
		   if (b==0) return a;
		   return GCD(b,a%b);
		}
	//divides the fraction by the greatest common factor
	public Fraction simplify(){
		int GCD = GCD(this.numerator, this.denominator);
		return new Fraction(this.getNumerator()/GCD,this.getDenominator()/GCD);
	}
	/*
	 * Prints out a string representation of the fraction
	 * in the form "numerator / denominator".
	 */
	public String toString(){
		/*
		 * If the denominator is one, then this prints out
		 * the whole number representation of the fraction.
		 */
		if (this.simplify().getDenominator() == 1){
		
			return Integer.toString(this.getNumerator());

		}

		else{
			
			return this.simplify().numerator + "/" + this.simplify().denominator;
		
		}

	}

	/*
	 * In the following add(), subtract(), multiply(), divide(), and reciprocal() functions, all of the inputs will be in their unsimplified forms,
	 * while the output will be in its simplest form.
	 */
	
	//adds two fractions together and returns a string in the form "'fraction' plus 'fraction' equals 'fraction'"
	public String add(Fraction fraction){
		Fraction frac = new Fraction(this.getNumerator()*fraction.getDenominator()+
				fraction.getNumerator()*getDenominator(),
				this.denominator*fraction.getDenominator());
		return this.toString() + " plus " + fraction.toString() + " equals " + frac.simplify().toString();
		
	}
	
	//subtracts two fractions and returns a string in the form "'fraction' minus 'fraction' equals 'fraction'"
	public String subtract(Fraction fraction){
		Fraction frac = new Fraction(this.getNumerator()*fraction.getDenominator()-
				fraction.getNumerator()*getDenominator(),
				this.denominator*fraction.getDenominator());
		return this.toString() + " minus " + fraction.toString() + " equals " + frac.toString();
		
	}
	
	//multiplies two fractions and returns a string in the form "'fraction' times 'fraction' equals 'fraction'"
	public String multiply(Fraction fraction){
		Fraction frac = new Fraction(this.getNumerator()*fraction.getNumerator(),
				this.denominator*fraction.getDenominator());
		return this.toString() + " times " + fraction.toString() + " equals " + frac.toString();
		
	}
	
	//divides two fractions and returns a string in the form "'fraction' divided by 'fraction' equals 'fraction'"
	public String divide(Fraction fraction){
		Fraction frac = new Fraction(this.getNumerator()*fraction.getDenominator(),
				this.denominator*fraction.getNumerator());
		return this.toString() + " divided by " + fraction.toString() + " equals " + frac.toString();
		
	}
	
	//finds the decimal form of a fraction and returns a string in the form "The decimal representation of 'fraction' is 'decimal'"
	public String toDecimal(){
		double decimal = (double)this.getNumerator()/this.getDenominator();
		return "The decimal representation of " +this.toString() + " is " + decimal;
	}
	
	//finds the reciprocal of a fraction a returns a string in the form "'fraction' is the reciprocal of 'fraction'
	public String reciprocal(){
		Fraction frac = new Fraction(this.getDenominator(), this.getNumerator());
		return frac.toString() + " is the reciprocal of " + this.toString();
	}

	public boolean equals(Fraction b){
		if (this.simplify().getNumerator()==b.simplify().getDenominator() &&
				this.simplify().getDenominator()==b.simplify().getDenominator()){
			return true;
		}
		else
			return false;
	}
	/**
	 * @return the numerator
	 */
	public int getNumerator() {
		return numerator;
	}

	/**
	 * @param numerator the numerator to set
	 */
	public void setNumerator(int numerator) {
		this.numerator = numerator;
	}

	/**
	 * @return the denominator
	 */
	public int getDenominator() {
		return denominator;
	}

	/**
	 * @param denominator the denominator to set
	 */
	public void setDenominator(int denominator) {
		this.denominator = denominator;
	}
	
	
	
}
