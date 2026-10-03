package sample;

public class ExceptionFlowDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("----Starting Program Execution---");

		int dividend = 100;
		int divisor = 0;

		try {
			System.out.println("Entering try block..");
			int result = dividend / divisor;
			System.out.println("THis line will never execcute due to exception:" + result);
		} catch (ArithmeticException e) {
			System.err.println("Caught Exception:Cannot divide by zero!" + e.getMessage());
		} finally {
			System.out.println("Finally Block:");
		}
		System.out.println("---Program continued normally past catch block--");
	}

}
