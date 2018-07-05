
public class StatisticsTester {
	
	//This method will run through every single Statistics method on any array sent to it.
	private static void methodTester(double[] a){
		System.out.print("Array: ");
		for(double values:a)
			System.out.print(values+" ");
		if(a.length==0)
			System.out.print("Empty");
		System.out.println();
		System.out.println("Mean: " + Statistics.mean(a));
		System.out.println("Median " + Statistics.median(a));
		System.out.print("Mode: ");
		for(double values:Statistics.mode(a))
			System.out.print(values+" ");
		System.out.println();
		System.out.println("Range: "+Statistics.range(a));
		System.out.println("Upper Quartile: "+Statistics.upperQuartile(a));
		System.out.println("Lower Quartile: "+Statistics.lowerQuartile(a));
		System.out.println("IQR: "+Statistics.interquartileRange(a));
		System.out.println("Standard Deviation: "+Statistics.standardDeviation(a));
		if (a.length==0)
			System.out.println("zScore: "+Statistics.zScore(a, 0));
		else{
			//The data value in the array selected for zScore will be 
			//picked at random 5 times.
			for(int i = 0; i<5; i++){
				double zScoreValue = (a[(int)(Math.random()*a.length)]);
				System.out.println("zScore of "+zScoreValue + " : "+Statistics.zScore(a, zScoreValue));
			}
		}
	}
	
	public static void main(String[] args){
		double[] a = {-5,-4,-3,-2,-1,0,1,2,3,4,5};
		methodTester(a);
		System.out.println();
		double[] b = {};
		methodTester(b);
		System.out.println();
		double[] c = {2,2,2,2,2,2,2,2,2,2};
		methodTester(c);
		System.out.println();
		double[] d = {1};
		methodTester(d);
		System.out.println();
		double[] e = {-2,-2,-2,-2,-2,-2,-2};
		methodTester(e);
		System.out.println();
		double[] f = {1,1,1,1,1,2,2,2,2,2,3,3,3,4,4,4,5};
		methodTester(f);
		System.out.println();
		for(int i = 0; i<3;i++){
			double[] g = {Math.random()*100,Math.random()*100,Math.random()*100,Math.random()*100,Math.random()*100};
			methodTester(g);
			System.out.println();
		}
	}
	
}
