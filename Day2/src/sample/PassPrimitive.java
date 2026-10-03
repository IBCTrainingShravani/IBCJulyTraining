package sample;

public class PassPrimitive {
	
	public static void main(String[]args) {
		
		int x=10;
		changeNumber(x);
		
		System.out.println(x);
		
		
	}
	
	public static void changeNumber(int copy) {
		copy=99;
	}

}
