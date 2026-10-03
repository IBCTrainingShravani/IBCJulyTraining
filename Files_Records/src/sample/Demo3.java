package sample;

public class Demo3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String s = null;
			System.out.println(s.length());

		} catch (ArithmeticException e) {

			System.out.println("Null Error");
		}

	}
}
