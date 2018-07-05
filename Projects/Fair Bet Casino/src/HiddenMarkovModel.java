
public class HiddenMarkovModel {
	private char[] input;
	
	public static double[][] sum(char[] input){
		double[][] sum = new double[input.length][2];
		sumHelper(input,sum,sum.length-1);
		return sum;
	}
	private static void sumHelper(char[] input, double[][] sum, int position){
		double value = (input[position]=='H')? .75 : .25;
		if(position < sum.length){
			if(position==0){
				sum[0][0] = .5*.5;
				sum[0][1] = .5*value;
			}
			else{
				sumHelper(input, sum, position-1);
				sum[position][0] = sum[position-1][0]*.7*.5+sum[position-1][1]*.3*.5;
				sum[position][1] = sum[position-1][0]*.3*value+sum[position-1][1]*.7*value;
			}
		}
	}
	
	public static double[][] max(char[] input){
		double[][] max = new double[input.length][2];
		maxHelper(input,max,max.length-1);
		return max;
	}
	private static void maxHelper(char[] input, double[][] max, int position){
		double value = (input[position]=='H')? .75 : .25;
		if(position < max.length){
			if(position==0){
				max[0][0] = .5*.5;
				max[0][1] = .5*value;
			}
			else{
				maxHelper(input, max, position-1);
				max[position][0] = Math.max(max[position-1][0]*.9*.5,max[position-1][1]*.1*.5);
				max[position][1] = Math.max(max[position-1][0]*.1*value,max[position-1][1]*.9*value);
			}
		}
	}
	
	public static void main(String[] args){
		char[] input = {'H','H','H','H','H','T','T','T','T','T'};
		double[][] max = max(input);
		for(double[] values:max){
			System.out.print(values[0]+", ");
		}
		System.out.println();
		for(double[] values:max){
			System.out.print(values[1]+" ");
		}
	}
}
