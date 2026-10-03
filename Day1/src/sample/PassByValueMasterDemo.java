package sample;

import java.util.Arrays;

public class PassByValueMasterDemo {
	public static void main(String[] args) {
		
		int localCash =500;
		int[] sharedAccountBalances= {1000,2000,3000};
		
		System.out.println("===1. Primitive Verification===");
		System.out.println("Before method call,localCash="+localCash);
		
		tryToModifyPrimitive(localCash);
		System.out.println("After method call,localCash="+localCash+"(Unchanged!)");
		
		System.out.println("\n===2.Object State Modification===");
		System.out.println("Balaces before modification:"+Arrays.toString(sharedAccountBalances));
		System.out.println("Balances after modification:"+Arrays.toString(sharedAccountBalances)+"(Mutated!)");
		
		System.out.println("\n===3.Object Refernce Reassignment Isolation===");
		System.out.println("Balances before reassignment attempt:"+Arrays.toString(sharedAccountBalances));
		
		tryReferenceReassignment(sharedAccountBalances);
		
		System.out.println("Balances after reassignment attempt:"+Arrays.toString(sharedAccountBalances)+"(Isolated/Unchanged!)");
		
		
	}
public static void tryToModifyPrimitive(int cashCopy) {
	cashCopy=99999;
	
}

public static void tryReferenceReassignment(int[]accountTokenCopy) {
	accountTokenCopy=new int[] {55,66,77};
	
	System.out.println("->Inside method, token rewrite to new array:"+Arrays.toString(accountTokenCopy));
}
}
