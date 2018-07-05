
public class MixedNumber {
	// data fields
	private Fraction fraction;
	private int wholeNum;

	public MixedNumber(int wholeNum, int numer, int denom) {
		this.fraction = new Fraction(numer % denom, denom).simplify();
		/*
		 * In the case that someone inputs a mixed number such that the numerator
		 * is larger than the denominator, this program will extract an additional
		 * whole number from the fractional portion.
		 */
		int additionalWholeNum = numer / denom;
		this.wholeNum = wholeNum + additionalWholeNum;
		
	}

	// This constructor takes in the numerator and denominator of a fraction as inputs.
	public MixedNumber(int numer, int denom) {
		
		this.wholeNum = numer / denom;
		
		/*
		 * If the whole number component is zero and the fraction is negative, then the fraction component may be
		 * represented in the form -B/C
		 */
		
		if (this.getWholeNum()==0 && ((double)numer / denom) < 0){
			
			this.fraction = new Fraction(numer % denom, denom);
		
		}
		
		/*
		 * The fractional component of the mixed number (where the whole number is not equal to zero)
		 * is changed to its absolute value form. This is done in order 
		 * to avoid a negative mixed number being represented in the form "-A -B/C".
		 */
		
		else{
			
			this.fraction = new Fraction(java.lang.Math.abs(numer%denom), java.lang.Math.abs(denom)).simplify();
		
		}
	}

	public MixedNumber(int wholeNum) {
		
		this(wholeNum, 0, 1);
		
	}

	// This constructors takes in a Fraction object as input.
	public MixedNumber(Fraction frac) {
		
		this(frac.getNumerator(),frac.getDenominator());

	}

	//converts the current Mixed Number to a simplified fraction
	public Fraction MixToFrac(){
		
		/*
		 * Note: When converting mixed numbers to improper fractions, if the whole number is negative, the numerator
		 * must be subtracted rather than added to the product of the whole number and the denominator.
		 */
		if (this.getWholeNum() < 0){
			
			return new Fraction(this.getFraction().getDenominator() * this.getWholeNum() - this.getFraction().getNumerator(),
					this.getFraction().getDenominator()).simplify();
			
		}
		
		else{
			
			return new Fraction(this.getFraction().getDenominator() * this.getWholeNum() + this.getFraction().getNumerator(),
					this.getFraction().getDenominator()).simplify();
			
		}
		

	}

	// adds two fractions together and returns a string in the form "Mixed
	// Number plus Mixed Number equals Mixed Number"
	public String add(MixedNumber mixedNumber) {
		
		Fraction frac1 = this.MixToFrac();
		
		Fraction frac2 = mixedNumber.MixToFrac();
	
	//Note: We cannot simply use the add() function from the Fraction class because the add() function returns a string.
		
		Fraction fracSum = new Fraction(
				frac1.getNumerator() * frac2.getDenominator() + frac2.getNumerator() * frac1.getDenominator(),
				frac2.getDenominator() * frac1.getDenominator());
		
		MixedNumber mixedNum = new MixedNumber(fracSum);
		
		return this.toString() + " plus " + mixedNumber.toString() + " equals " + mixedNum.toString();

	}

	// subtracts two fractions and returns a string in the form "fraction minus
	// fraction equals fraction"
	public String subtract(MixedNumber mixedNumber) {
		Fraction frac1 = this.MixToFrac();
		
		Fraction frac2 = mixedNumber.MixToFrac();
		
		Fraction fracSum = new Fraction(
				frac1.getNumerator() * frac2.getDenominator() + frac2.getNumerator() * frac1.getDenominator(),
				frac2.getDenominator() * frac1.getDenominator());
		
		MixedNumber mixedNum = new MixedNumber(fracSum);
		
		return this.toString() + " minus " + mixedNumber.toString() + " equals " + mixedNum.toString();

	}

	// multiplies two fractions and returns a string in the form "fraction times
	// fraction equals fraction"
	public String multiply(MixedNumber mixedNumber) {
		Fraction frac1 = this.MixToFrac();
		
		Fraction frac2 = mixedNumber.MixToFrac();
		
		Fraction fracProduct = new Fraction(frac1.getNumerator() * frac2.getNumerator(),
				frac1.getDenominator() * frac2.getDenominator());
		
		MixedNumber mixedNum = new MixedNumber(fracProduct);
		
		return this.toString() + " times " + mixedNumber.toString() + " equals " + mixedNum.toString();

	}

	// divides two fractions and returns a string in the form "fraction divided
	// by fraction equals fraction"
	public String divide(MixedNumber mixedNumber) {
		Fraction frac1 = this.MixToFrac();
		
		Fraction frac2 = mixedNumber.MixToFrac();
		
		Fraction fracQuotient = new Fraction(frac1.getNumerator() * frac2.getDenominator(),
				frac1.getDenominator() * frac2.getNumerator());
		
		MixedNumber mixedNum = new MixedNumber(fracQuotient);
		return this.toString() + " divided by " + mixedNumber.toString() + " equals " + mixedNum.toString();

	}
	
	//returns the decimal representation of a fraction in the form "The decimal representation of 'Mixed Number' is 'decimal'"
	public String toDecimal() {
		
		Fraction frac1 = this.MixToFrac();
		double decimal = (double)frac1.getNumerator() / frac1.getDenominator();
		return "The decimal representation of " + this.toString() + " is " + decimal;
	}

	//returns the reciprocal of a mixed number. If reciprocal is a mixed number, it will return a mixed number.
	public String reciprocal() {
		
		Fraction frac1 = this.MixToFrac();
		
		Fraction frac2 = new Fraction(frac1.getDenominator(), frac1.getNumerator()).simplify();
		
		MixedNumber reciprocal = new MixedNumber(frac2);
		
		return reciprocal.toString() + " is the reciprocal of " + this.toString();
	}
	//returns a String representation of a Mixed Number Input in the form "Wholenumber numerator/denominator"
	public String toString(){
		

		/*
		 * If the denominator is one, then this prints out
		 * the whole number representation of the fraction.
		 */
		
		if (this.getFraction().getDenominator() == 1 && this.getWholeNum()!=0){
			
			return Integer.toString(this.getWholeNum());
		
		}

		//If the Whole number component of the mixed number is 0, this will only return the fraction
		if (this.getWholeNum()==0){
			
			return this.getFraction().toString();
		
		}

		else {			

			return this.getWholeNum()+ " " + this.getFraction().toString();

		}

	}

	/**
	 * @return the fraction
	 */
	public Fraction getFraction() {
		return fraction;
	}

	/**
	 * @param fraction
	 *            the fraction to set
	 */
	public void setFraction(Fraction fraction) {
		this.fraction = fraction;
	}

	/**
	 * @return the wholeNum
	 */
	public int getWholeNum() {
		return wholeNum;
	}

	/**
	 * @param wholeNum
	 *            the wholeNum to set
	 */
	public void setWholenum(int wholeNum) {
		this.wholeNum = wholeNum;
	}

}
