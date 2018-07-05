
public class FractionTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Fraction frac = new Fraction(2,4);
		Fraction frac2 = new Fraction(2,3);		
		System.out.println(frac.toDecimal());
		System.out.println(frac2.reciprocal());
		System.out.println(frac.divide(frac2));
		System.out.println(frac.multiply(frac2));
		System.out.println(frac.add(frac2));
		System.out.println(frac.subtract(frac2));
		Fraction frac3 = new Fraction(-7,3);
		Fraction frac4 = new Fraction(15,-3);
		System.out.println(frac3.divide(frac4));
		System.out.println(frac3.multiply(frac4));
		System.out.println(frac3.add(frac4));
		System.out.println(frac3.subtract(frac4));
		Fraction frac5 = new Fraction(-9,12);
		Fraction frac6 = new Fraction(5,3);
		System.out.println(frac5.divide(frac6));
		System.out.println(frac5.multiply(frac6));
		System.out.println(frac5.add(frac6));
		System.out.println(frac5.subtract(frac6));
		Fraction frac7 = new Fraction(3,17);
		Fraction frac8 = new Fraction(4,-12);
		System.out.println(frac7.divide(frac8));
		System.out.println(frac7.multiply(frac8));
		System.out.println(frac7.add(frac8));
		System.out.println(frac7.subtract(frac8));
		MixedNumber mix1 = new MixedNumber(4,3);
		MixedNumber mix2 = new MixedNumber(5,2);
		System.out.println(mix1.add(mix2));
		System.out.println(mix1.subtract(mix2));
		System.out.println(mix1.multiply(mix2));
		System.out.println(mix1.divide(mix2));
		MixedNumber mix3 = new MixedNumber(4,3);
		MixedNumber mix4 = new MixedNumber(5,-2);
		System.out.println(mix3.add(mix4));
		System.out.println(mix3.subtract(mix4));
		System.out.println(mix3.multiply(mix4));
		System.out.println(mix3.divide(mix4));
		MixedNumber mix5 = new MixedNumber(4,-3);
		MixedNumber mix6 = new MixedNumber(-5,2);
		System.out.println(mix5.add(mix6));
		System.out.println(mix5.subtract(mix6));
		System.out.println(mix5.multiply(mix6));
		System.out.println(mix5.divide(mix6));
		System.out.println(mix5.toDecimal());
		System.out.println(mix5.reciprocal());

	}

}
