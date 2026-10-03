package sample;

public class SimpleBlocks {
//1. Static blocks
	
	static {
		System.out.println("Static block: I run once at the very beginning.");
		
	
	}
	
	//3. Constructor
	
		public SimpleBlocks() {
			
			System.out.println("Constructor: Finished building the object.");
			
		}
	
	//2. Instance block
	{
		System.out.println("Instance Block: A new object is being created!");
		
	}
	
	
	
	public static void main(String[]args) {
		
		System.out.println(">> Main Starts<<");
		
		SimpleBlocks obj1=new SimpleBlocks();
		
		System.out.println("_____");
		
		SimpleBlocks obj2=new SimpleBlocks();
		SimpleBlocks obj3=new SimpleBlocks();
		SimpleBlocks obj4=new SimpleBlocks();
		SimpleBlocks obj5=new SimpleBlocks();
	}
}
