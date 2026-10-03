package sample;

class InsufficientBalanceException extends Exception {
	public InsufficientBalanceException(String message) {
		super(message);
	}
}

class BankAccount {
	private double balance = 1000.00;

	public void withdraw(double amount) throws InsufficientBalanceException {
		if (amount > balance) {
			throw new InsufficientBalanceException("Withdrawal of $" + amount + "exceeds balance of $" + balance);
		}
		balance -= amount;
		System.out.println("Withdrawal apprived:" + balance);
	}
}

public class CustomExceptionDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BankAccount account = new BankAccount();
		try {
			account.withdraw(1500.00);

		} catch (InsufficientBalanceException e) {
			System.err.println("Banking error:" + e.getMessage());
		}

	}

}
