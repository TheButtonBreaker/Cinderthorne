package cinderthorne.util;

import java.util.Random;

public class MathUtil {
	public static Random rand = new Random();
	
	public static void setSeed(long seed){
		//rand = new Random(seed);
		rand.setSeed(seed);
	}
	
	public static int randInt(int min, int max){
		max++; //In the equation below, max is exclusive but min is inclusive
		return rand.nextInt(max - min) + min; 
	}
	
	public static double randDouble(double min, double max){
		return min + (rand.nextDouble() * ((max - min) + 1)); 
	}
	
	public static long randLong(double min, double max){
		return (long) (min + (rand.nextDouble() * ((max - min) + 1))); 
	}
}
