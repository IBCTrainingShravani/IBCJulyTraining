package Sample2;

public class InitializationDemo {
	int defaultInt;
	String defaultRef;
	int[] defaultArrayRef;
	public void runDemo()
	{
		
		System.out.println("===1. Heap Field Automatically Initialized===");
		System.out.println("Default primitive int:"+ defaultInt);
		System.out.println("Default Object reference:"+defaultRef);
		System.out.println("Dafault Array Reference:"+ defaultArrayRef);
		
		System.out.println("\n===2.loacal Stack Variables===");
		int localPrimitive;
		String localReference;
		
		
		//System.out.println(localPrimitive);
		
		localPrimitive=108;
		System.out.println("Explicitly assigned local primitive:"+ localPrimitive);
		
		
	}
	
	public static void main(String[]args) {
		new InitializationDemo().runDemo();
	}
}
