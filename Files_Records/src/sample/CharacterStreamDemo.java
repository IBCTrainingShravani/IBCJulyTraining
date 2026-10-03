package sample;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CharacterStreamDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String logPath = "audit_log.txt";

		try (FileWriter writer = new FileWriter(logPath)) {

			writer.write("DRIVER_ID:KA-51-9934\nSTATUS:ACTIVE\nFARE:RS.250.00\n");

			System.out.println("Text audit log written.");

		} catch (IOException e) {
			System.out.println("Write error:" + e.getMessage());
		}
		try (FileReader reader = new FileReader(logPath)) {
			int characterData;
			System.out.println("---REading Audit Log----");
			while ((characterData = reader.read()) != -1) {
				System.out.print((char) characterData);
			}
		} catch (IOException e) {
			System.out.println("Read error:" + e.getMessage());
		}

	}

}
