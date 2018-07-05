import java.util.ArrayList;

// This class contains methods which calculate the various Statistics
// of an array. These methods return either an ArrayList or double.
// If the array is empty, the methods return either an empty 
// Array list or NaN. In all the methods, the original array is not changed.

public class Statistics {
	
	// This method sorts an array.
	public static void sort(double[] a){
		for(int i=0; i<a.length; i++){
			for(int j=0; j<a.length; j++){
				if (a[i]<a[j]){
					double temp = a[i];
					a[i] = a[j];
					a[j] = temp;
					
				}
			}
		}
	}
	
	//This method calculates the mean of array.
	public static double mean(double[] a){
		if(a.length==0)
			return Double.NaN;
		double sum=0;
		for(double doubles:a){
			sum+=doubles;
		}
		return sum/a.length;
	}
	
	//This method finds the median of an array.
	public static double median(double[] a){
		if(a.length==0)
			return Double.NaN;
		double[] b = a;
		sort(b);
		if(b.length%2 == 1)
			return b[(b.length-1)/2];
		return (b[b.length/2-1]+b[b.length/2])/2;
	}
	
	//This method finds the mode of an array. If the all the numbers in
	//the array occur an equal amount of times, there will be no mode.
	
	public static ArrayList<Double> mode(double[] a){
		if (a.length == 0)
			return new ArrayList<Double>();
		double[] b = a;
		sort(b);
		//This method uses counters to count how many times a number appears
		//in consecutive order after sorting. If the current counter 
		//is larger than the previous counter, then the previous counter 
		//is replaced and the current counter is reset to 1.
		int previousCounter = 1;
		int newCounter = 1;
		ArrayList<Double> modes = new ArrayList<Double>();
		
		//If the mode has not been cleared at least once, modeCleared will stay
		//false throughout the entirety of the loop. At the end of the loop,
		//if modeCleared is still false, then that indicated that each number
		//in the loop occurred the same amount of times, indicating that
		//there is no mode.
		boolean modeCleared = false;
		
		//firstReplacement will remain false until the loop has moved onto the next
		//number. This is done because the previousCounter is set to be 1 at the
		//beginning, and it will be changed immediately after the first repeated number
		//completes in the loop. Since we do not want modeCleared to activate true
		//in this case, modeCleared can only become true after firstReplacement
		//becomes true.
		boolean firstReplacement = false;
		
		for(int i = 1; i<b.length; i++){
			if(b[i]==b[i-1]){
				newCounter++;
			}
			else{
				
				if (firstReplacement){
					if(newCounter<previousCounter){
						modeCleared = true;
						newCounter = 1;
					}
					if(newCounter>previousCounter){
						previousCounter = newCounter;
						newCounter = 1;
						modes.clear();
						modeCleared = true;
						modes.add(b[i-1]);
					}
					if (newCounter == previousCounter){
						modes.add(b[i-1]);
						newCounter = 1;
					}
				}
				if (!firstReplacement){
					previousCounter = newCounter;
					newCounter = 1;
					modes.add(b[i-1]);
					firstReplacement = true;
				}
			}
		}
		
		//Once the loop reaches the end of the array, we would like to check
		//if the newCounter is greater than previousCounter. However, the loop
		//will only check for that if the following number is not equal to the previous
		//number. Since there is no following number at the end of the array, the
		//final check is coded in at the end.
		if(newCounter<previousCounter){
			modeCleared = true;
		}
		if(newCounter>previousCounter){
			previousCounter = newCounter;
			newCounter = 1;
			modes.clear();
			if(firstReplacement)
				modeCleared = true;
			modes.add(b[b.length-1]);
		}
		if (newCounter == previousCounter){
			modes.add(b[b.length-1]);
			newCounter = 1;
		}
		
		if(!modeCleared){
			return new ArrayList<Double>();
		}
		return modes;

		
	}
	
	//This method will calculate the range of an array.
	public static double range(double[] a){
		if(a.length==0)
			return Double.NaN;
		double currentHighest=a[0];
		double currentLowest=a[0];
		for(double doubles:a){
			if (doubles>currentHighest){
				currentHighest = doubles;
			}
			if (doubles<currentLowest){
				currentLowest = doubles;
			}
		}
		
		return currentHighest - currentLowest;
	}
	
	//This method will calculate the upperQuartile of an array.
	public static double upperQuartile(double[] a){
		if(a.length==0)
			return Double.NaN;
		double[] b = a;
		sort(b);
		/*
		The position of the upperQuartile is calculated using a mathematical formula,
		which can be derived by making a graph of "position of upper quartile"
		vs "number of values in the array". If the upperQuartile is the mean of two
		consecutive values, the position is defined to be halfway in between. For
		example, a position between 2 and 3 is 2.5. 
		
		The function is equal to the composition
		of two step functions, one of which has a step-function slope of .5, 
		and the other which has a step-function slope of 1.
		*/
		double position = (int)(.5*b.length-1)*.5+(int)((b.length+1)/2);
		if (a.length==1){
			position = 0;
		}
		if ((position*2)%2==0){
			return b[(int)position];
		
		}
		return (b[(int)position]+b[(int)position+1])/2;
	}
	
	//This method will calculate the lowerQuartile of an array.
	public static double lowerQuartile(double[] a){
		if(a.length==0)
			return Double.NaN;
		double[] b = a;
		sort(b);
		/*
		 * A process similar to the calculate of the upper quartile was done to
		 * calculate the lowerQuartile, with the difference being that the y axis
		 * is "position of lower quartile".
		 */
		double position = ((int)(.5*(b.length-2)))*.5;
		if ((position*2)%2==0){
			return b[(int)position];
		}
		return (b[(int)position]+b[(int)position+1])/2;
	}
	
	//This method will calculate the IQR of an array.
	public static double interquartileRange(double[] a){
		return upperQuartile(a) - lowerQuartile(a);
	}
	
	//This method creates an ArrayList of outliers in an array. The definition
	//used for outliers is: Any data point 1.5 IQR above the upper quartile and
	//1.5 IQR below the lower quartile.
	public static ArrayList<Double> outliers(double[] a){
		double[] b = a;
		sort(b);
		double IQR = interquartileRange(b);
		ArrayList<Double> outliers = new ArrayList<Double>();
		for(int i = 0; lowerQuartile(b)-b[i]>IQR*1.5; i++){
			outliers.add(b[i]);
		}
		for(int i = b.length-1; b[i]-upperQuartile(b)>IQR*1.5; i--){
			outliers.add(b[i]);
		}
		return outliers;
	}
	
	//This method calculates the standard deviation of an array.
	public static double standardDeviation(double[] a){
		if(a.length==0)
			return Double.NaN;
		double mean = mean(a);
		double deviations = 0;
		for(int i = 0; i<a.length; i++){
			deviations+=Math.pow((a[i]-mean),2);
		}
		return Math.sqrt(deviations/(a.length-1));
	}
	
	//This method calculates the zScore of a data value in an array.
	public static double zScore(double[] array, double dataValue){
		double standardDeviation = standardDeviation(array);
		//This checks if the dataValue inputed for the second parameter
		//is in fact a member of the array. If it is not a member, then 
		//the method will return NaN.
		boolean isInArray = false;
		for(double values: array)
			if (values==dataValue)
				isInArray = true;
		if(array.length==0||standardDeviation==0||!isInArray)
			return Double.NaN;
		return (dataValue-mean(array))/standardDeviation(array);
	}
	
	public static void main(String[] args) {
		
	}

}
