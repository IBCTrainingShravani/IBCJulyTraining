package sample;

import java.io.IOException;

public class RethrowDemo {

	public void executeQuery() throws IOException {
		try {
			throw new IOException("Database connection timed out:");
		} catch (IOException e) {
			System.err.println("Query failed:logging trace locally==");
			throw e;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RethrowDemo service = new RethrowDemo();
		try {
			service.executeQuery();

		} catch (IOException e) {
			System.out.println("UI layer displaed to user:" + e.getMessage());
		}
	}

}
