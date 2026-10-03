package sample;

public class Demo2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Start");

		try {
			int result = 10 / 0;

		} catch (ArithmeticException e) {
			System.out.println("Division by 0 is not allowed");
		}
		System.out.println("End");
	}

}
