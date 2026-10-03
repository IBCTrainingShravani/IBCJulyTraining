package sample;

public class StackPropagationDemo {

	void level3() {
		int fault = 10 / 0;
	}

	void level2() {
		level3();
	}

	void level1() {
		try {
			level2();
		} catch (ArithmeticException e) {
			System.err.println("Caught propagated exception inside level()" + e.toString());

		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new StackPropagationDemo().level1();
		System.out.println("prpgarm resumed execution safely");
	}

}
