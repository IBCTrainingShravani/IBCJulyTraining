package sample;

public class Demo1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			int a = 10;
			int b = 0;
			System.out.println(a / b);
		} catch (ArithmeticException e) {
			System.out.println("cannot divide by0");
		}

		System.out.println("Program end");
	}

}
