package sample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;

class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private double salary;

	public Employee(int id, String name, double salary) {

		this.id = id;
		this.name = name;
		this.salary = salary;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + "]";
	}

}

class InvalidSalaryException extends Exception {
	public InvalidSalaryException(String msg) {
		super(msg);
	}

}

class EmployeeWriter {
	// String empFile = "employees.txt";
	public static void writeData(String filename) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
			bw.write("101,Ram,500000");
			bw.newLine();
			bw.write("102,Seetha,60000");
			bw.newLine();
			bw.write("103,John,70000");
			bw.newLine();
		}
		System.out.println("Data saved to:" + filename);
	}
}

class EmployeeReader {
	public static void readData(String filename) throws IOException {
		System.out.println("Reading lines from" + filename + ":");
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

			String line;
			while ((line = br.readLine()) != null) {
				System.out.println("->" + line);

			}
		}
	}
}

class FileNavigation {
	public static void inspectFile(String filename) {
		File file = new File(filename);
		System.out.println("->Exists:" + file.exists());
		System.out.println("->Abs path" + file.getName());
		System.out.println("->File size:" + file.length() + "bytes");

	}
}

class FileBackup {
	// String empFile = "employees.txt";
	// String backupFile = "backup.txt";
	// FileBackup.backup(empFile, backupFile);

	public static void backup(String sourceFile, String backupFile) throws IOException {
		try (FileInputStream fis = new FileInputStream(sourceFile);
				FileOutputStream fos = new FileOutputStream(backupFile)) {
			int data;
			while ((data = fis.read()) != -1) {
				fos.write(data);
			}
		}

		System.out.println("Backup created succesfully" + backupFile);
	}
}

class RandomAccessService {
	// String empFile = "employees.txt";
	// RandomAccessService.readFromOffset(empFile, 15);

	public static void readFromOffset(String filename, long byteOffset) throws IOException {
		try (RandomAccessFile raf = new RandomAccessFile(filename, "r")) {
			raf.seek(byteOffset);

			String line = raf.readLine();

			System.out.println("Read from byte offset" + byteOffset + "->" + line);
		}
	}
}

class SerializationService {
	public static void serializeEmployee(String filename, Employee emp) throws IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
			out.writeObject(emp);

		}
		System.out.println("Object Serializated to" + filename);

	}

	public static Employee deserializeEmployee(String filename) throws IOException, ClassNotFoundException {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
			Employee emp = (Employee) in.readObject();
			System.out.println("Restored object->" + emp);
			return emp;
		}
	}
}

class SalaryValidator {

	public static void validateSalary(double salary) throws InvalidSalaryException {

		if (salary < 0) {
			throw new InvalidSalaryException("Salary cannot be negative:" + salary);
		}

		System.out.println("Custom exception check:" + salary + "is valid");
	}
}

class PropagationAndRethrowService {
	private static void lowLevelOperation() {
		int x = 10 / 0;

	}

	public static void midLevelLayer() {
		lowLevelOperation();

	}

	public static void testRethrow() {
		try {
			int x = 10 / 0;

		} catch (ArithmeticException e) {
			System.out.println("Caught locally,logging trace...");
			throw e;
		}
	}
}

public class EmployeeManagementSystem {

	public static void main(String[] args) {
		String empFile = "employees.txt";
		String backupFile = "backup.txt";
		String serFile = "employee.ser";

		System.out.println("=================================================");
		System.out.println("   EMPLOYEE MANAGEMENT SYSTEM - WORKFLOW DEMO   ");
		System.out.println("=================================================\n");

		try {

			EmployeeWriter.writeData(empFile);
			EmployeeReader.readData(empFile);

			System.out.println();
			FileNavigation.inspectFile(empFile);

			System.out.println();
			FileBackup.backup(empFile, backupFile);

			System.out.println();
			RandomAccessService.readFromOffset(empFile, 16);

			System.out.println();
			Employee emp1 = new Employee(101, "Ram", 50000.0);
			SerializationService.serializeEmployee(serFile, emp1);
			SerializationService.deserializeEmployee(serFile);

			System.out.println();
			SalaryValidator.validateSalary(45000.0);
			try {
				SalaryValidator.validateSalary(-5000.0);
			} catch (InvalidSalaryException e) {
				System.err.println("[Step 11 Handled] Custom Business Error: " + e.getMessage());
			}

			System.out.println();
			try {
				PropagationAndRethrowService.midLevelLayer();
			} catch (ArithmeticException e) {
				System.out.println("[Step 12 Handled] Call Stack Propagation caught at main level: " + e.getMessage());
			}

			System.out.println();
			try {
				PropagationAndRethrowService.testRethrow();
			} catch (ArithmeticException e) {
				System.out.println("[Step 13 Handled] Outer Handler captured rethrown exception: " + e.getMessage());
			}

		}

		catch (FileNotFoundException e) {
			System.err.println("[HANDLED SPECIFIC] File not found: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("[HANDLED GENERAL I/O] I/O Failure: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println("[HANDLED CLASS ERROR] Class not found during deserialization: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("[HANDLED TOP-LEVEL] General System Fault: " + e.getMessage());
		}

		finally {
			System.out.println("\n-------------------------------------------------");
			System.out.println("[FINALLY] Cleaning up generated demo files...");
			// new File(empFile).delete();
			// new File(backupFile).delete();
			// new File(serFile).delete();
			System.out.println("[FINALLY] All storage handles and demo files cleared.");
			System.out.println("-------------------------------------------------");
		}

		System.out.println("\n=================================================");
		System.out.println("       APPLICATION EXECUTED SUCCESSFULLY         ");
		System.out.println("=================================================");
	}
}
