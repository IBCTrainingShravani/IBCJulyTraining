package Sample3;

public class StackHeapDemo {
public static void main(String[] args) {
	System.out.println("===Stack vs Heap Allocation Demo==");
	
	int localizedTicketPrice=450;
	
	int[] scores=new int[] {99,88,92};
	
	System.out.println("Primitive read directly from Stack:"+ localizedTicketPrice);
	System.out.println("Accessing heap array data via stack pointer:"+scores[0]);
	
}
}
