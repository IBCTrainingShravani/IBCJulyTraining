package sample;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ExceptionHierarchyDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			FileReader fr = new FileReader("missing.txt");
			fr.read();

		} catch (FileNotFoundException e) {
			System.err.println("Handled specific err:file not found");
		}

		catch (IOException e) {
			System.err.println("Error:General i/o issue");
		} catch (Exception e) {
			System.out.println("Handled catch all:" + e.getMessage());
		}
	}

}
