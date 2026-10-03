package sample;
import java.util.Arrays;

public class ArrayLifecycleDemo {
	public static void main(String[] args) {
        System.out.println("=== 1. Array Declaration ===");
        int[] mySteelTiffin; 
        System.out.println("\n=== 2. Array Construction ===");
        
        mySteelTiffin = new int[3]; 
        System.out.println("Freshly constructed array contents (Defaults): " + Arrays.toString(mySteelTiffin));

        System.out.println("\n=== 3. Array Explicit Initialization ===");
        mySteelTiffin[0] = 15; 
        mySteelTiffin[1] = 30; 
        mySteelTiffin[2] = 45; 
        System.out.println("Fully initialized array contents: " + Arrays.toString(mySteelTiffin));

        System.out.println("\n=== 4. Array Shortcut Initialization Syntax ===");
        
        int[] dynamicHalwaiBox = { 108, 501, 1001 };
        System.out.println("Shortcut array contents: " + Arrays.toString(dynamicHalwaiBox));
	}
}
