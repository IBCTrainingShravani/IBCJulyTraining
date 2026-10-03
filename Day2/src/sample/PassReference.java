package sample;

public class PassReference {
	public static void main(String[]args) {
	
		int[] scores= {10,20};
		
		modifyElement(scores);
		
		System.out.println(scores[0]);
		
		resetArray(scores);
		
		System.out.println(scores[0]);
		
		
	}
	
	public static void modifyElement(int[]arr) {
		arr[0]=99;
	}
	
	public static void resetArray(int[]arr) {
		arr[0]=67;
		
		arr=new int[] {5,6};
		
	}
}
