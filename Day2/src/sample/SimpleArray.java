package sample;

public class SimpleArray {
	
	public static void main(String[]args) {
		int[] myBox;
		
		myBox=new int[3];
		System.out.println("Step 2 (Default values):");
		
		printArray(myBox);
		
		myBox[0]=55;
		myBox[1]=66;
		myBox[2]=77;
		
		System.out.println("Step 3 (Initiazed values):");
		
		printArray(myBox);
		
		int[] shortcut= {1,2,3};
		
		System.out.println("Shortcut Syntax:");
		
		printArray(shortcut);
		
	}
	
	public static void printArray(int[]arr) {
		
		System.out.print("[");
		for(int i=0;i<arr.length;i++)
		{
			System.out.print(arr[i]);
			
			if(i<arr.length-1) {
				System.out.print(",");
			}
		}
		System.out.println("]");
	}

}
