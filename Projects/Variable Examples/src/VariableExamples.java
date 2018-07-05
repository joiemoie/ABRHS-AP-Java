/*
 * Introduces variable declaration, assignment,
 * and simple operations.
 * 
 * 9/11/2015
 */

public class VariableExamples {

	public static void main(String[] args) {
		
		//Declares an integer variable
		int x;
		
		x = 5; // variable assignment and initialization
		
		System.out.println(x);
		
		int num = 23;
		
		System.out.println(x + num);
		System.out.println("The value of num is " + num);
		System.out.println("The sum of x and num is " + x + num); // incorrect b/c of order of operations
		System.out.println("The sum of x and num is " + (x + num));
		System.out.println(x + num + " is the sum of x and num");
		
		double myDouble = 5.;
		
		System.out.println(num / myDouble);
		System.out.println(num / x); // truncates the decimal part
		
		// illustrates "type casting"
		System.out.println(num / (double)x);
		
		double d = num / x;
		System.out.println(d);
		
		System.out.print(num + " divided by " + x + " is " + num / x);
		System.out.println(" with remainder " + num % x);
		
		System.out.println("This is an \"example\" of\nsplitting output onto separate lines");
		
		String str = new String("tomato"); // invoking the String constructor
		System.out.println(str);
		System.out.println(str.toUpperCase());
	}

}







