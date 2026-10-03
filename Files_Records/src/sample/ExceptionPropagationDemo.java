package sample;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ExceptionPropagationDemo {
	public static void readDatabaseConfig(String path) throws IOException {

		try {
			if (path == null || path.isEmpty()) {
				throw new FileNotFoundException("Config path cannot be empty.");

			}
			throw new IOException("Strorage hardware failed to respond");
		} catch (IOException e) {
			System.out.println("Log system recorder low level failure:" + e.getMessage());

		}
	}

	public static void initializeServices() throws IOException {

		readDatabaseConfig("");
	}

	public static void main(String[] args) {
		try {
			initializeServices();

		} catch (IOException e) {
			System.out.println("UI layer :" + e.getMessage());
		}
	}

}
