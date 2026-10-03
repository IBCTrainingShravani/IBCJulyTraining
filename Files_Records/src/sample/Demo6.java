package sample;

public class Demo6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			int result = 10 / 0;

		} catch (ArithmeticException e) {
			System.out.println("Arithmetic exception");
		}
	}

}
