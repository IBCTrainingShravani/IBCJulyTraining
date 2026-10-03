package sample;

public class Demo4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			int x = 10 / 0;
		} catch (Exception e) {
			System.out.println("Handled");
		} finally {
			System.out.println("Finally block");
		}
		System.out.println("Program continues");
	}

}
