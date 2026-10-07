package sample;

import java.util.Scanner;

class Room {
	private final int roomNumber;
	private final double roomRent;

	static {
		System.out.println("=================================");
		System.out.println("Hotel System Initializing...");
		System.out.println("Loading Room Master Data...");
		System.out.println("=================================");
	}

	{
		System.out.println("\n[Lifecycle] Room Object Created (IIB executed)");
	}

	public Room(int roomNumber, double roomRent) {
		this.roomNumber = roomNumber;
		this.roomRent = roomRent;
		System.out.println("[Lifecycle] Constructor Executed for Room #" + roomNumber);
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public double getRoomRent() {
		return roomRent;
	}
}

public class InteractiveHotelManagementDemo {

	public static void calculateBill(double rent, int days) {
		double totalBill = rent * days;
		System.out.println("-> Total Bill for " + days + " days @ ₹" + rent + "/day = ₹" + totalBill);
	}

	public static void main(String[] args) {

		System.out.println("\n===== INTERACTIVE HOTEL MANAGEMENT SYSTEM =====");

		
		try (Scanner scanner = new Scanner(System.in)) {

			
			System.out.print("Enter Customer Name: ");
			String customerName = scanner.nextLine();

			
			System.out.print("Enter Floor Number (byte): ");
			byte floorNumber = scanner.nextByte();

			System.out.print("Enter Room Number (int): ");
			int roomNumber = scanner.nextInt();

			System.out.print("Enter Guest Mobile Number (long): ");
			long mobileNumber = scanner.nextLong();

			
			System.out.print("Enter Room Base Rent (double): ₹");
			double roomRent = scanner.nextDouble();

			System.out.print("Enter Applied Discount Percentage (float): ");
			float discount = scanner.nextFloat();

			
			System.out.print("Enter Room Category [D: Deluxe, P: Premium, S: Suite]: ");
			char roomCategory = scanner.next().toUpperCase().charAt(0);

			
			System.out.print("Is Room Available? (true/false): ");
			boolean isAvailable = scanner.nextBoolean();

		
			System.out.print("Enter Number of Stay Days: ");
			int stayDays = scanner.nextInt();

			
			System.out.println("\n=================================");
			System.out.println("       GUEST DETAILS SUMMARY      ");
			System.out.println("=================================");
			System.out.println("Guest Name    : " + customerName);
			System.out.println("Floor Number  : " + floorNumber);
			System.out.println("Room Number   : " + roomNumber);
			System.out.println("Mobile Number : " + mobileNumber);
			System.out.println("Category      : " + roomCategory);
			System.out.println("Discount      : " + discount + "%");
			System.out.println("Base Rent     : ₹" + roomRent);
			System.out.println("Available     : " + isAvailable);

			System.out.println("\n--- Calculating Final Tariff ---");
			calculateBill(roomRent, stayDays);

			System.out.println("\n--- Creating Room Entity ---");
			Room room = new Room(roomNumber, roomRent);
			System.out.println(
					"Confirmed Room Key: #" + room.getRoomNumber() + " with Daily Rate: ₹" + room.getRoomRent());

		} catch (Exception e) {
			System.err.println("\n[Input Error] Invalid data format entered: " + e.getMessage());
		}

		System.out.println("\n===== APPLICATION SESSION CLOSED =====");
	}
}
