package sample;

public class SimpleInit {
int heapInt;

public static void main(String[]args) {
	
	//1. Heap default test
	
	SimpleInit obj = new SimpleInit();
	//System.out.println(heapInt);
	
	System.out.println(obj.heapInt);
	
	//2.Local stack test
	
	int localInt =10;
	
	System.out.println(localInt);
			
}
}
