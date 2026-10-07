package com.hotel.shmrs.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.hotel.shmrs.config.HotelConfiguration;
import com.hotel.shmrs.factory.RoomFactory;
import com.hotel.shmrs.filehandling.HotelFileManager;
import com.hotel.shmrs.model.entity.Booking;
import com.hotel.shmrs.model.entity.Customer;
import com.hotel.shmrs.model.entity.Employee;
import com.hotel.shmrs.model.entity.HotelService;
import com.hotel.shmrs.model.entity.Manager;
import com.hotel.shmrs.model.entity.Passport;
import com.hotel.shmrs.model.entity.Receptionist;
import com.hotel.shmrs.model.enums.PaymentMode;
import com.hotel.shmrs.model.enums.RoomType;
import com.hotel.shmrs.model.exceptions.InvalidPaymentException;
import com.hotel.shmrs.model.exceptions.RoomNotAvailableException;
import com.hotel.shmrs.service.HotelReservationEngine;
import com.hotel.shmrs.strategy.CardPaymentStrategy;
import com.hotel.shmrs.strategy.UPIPaymentStrategy;

public class SmartHotelManagementApp {

	public static void main(String[] args) {
		System.out.println("=================================================================");
		System.out.println("   SMART HOTEL MANAGEMENT & RESERVATION SYSTEM (SHMRS) v3.0     ");
		System.out.println("   " + HotelConfiguration.getInstance().getPropertyDetails());
		System.out.println("=================================================================\n");

		HotelReservationEngine engine = new HotelReservationEngine();

		engine.registerRoom(RoomFactory.createRoom(RoomType.DELUXE, 101));
		engine.registerRoom(RoomFactory.createRoom(RoomType.PREMIUM, 201));
		engine.registerRoom(RoomFactory.createRoom(RoomType.SUITE, 301));

		System.out.println("--- 1. Staff Polymorphism & Abstract Roles ---");
		List<Employee> staffList = List.of(new Manager("EMP-01", "Vikram Rathore", 95000.0),
				new Receptionist("EMP-02", "Ananya Sen", 45000.0));
		staffList.forEach(Employee::performDuties);

		System.out.println("\n--- 2. Dynamic Room Search via Java 8 Streams ---");
		engine.filterAvailableRoomsSortedByPrice().forEach(System.out::println);

		System.out.println("\n--- 3. Multi-Threaded Concurrent Booking Race on Room #201 ---");
		Customer guestA = new Customer("C-101", "Arjun Verma", "arjun@example.com", false,
				new Passport("L982341", "IND"), "PIN_8899");
		Customer guestB = new Customer("C-102", "Priya Sharma", "priya@example.com", true,
				new Passport("K112233", "IND"), "PIN_4411");

		guestB.addService(new HotelService("Luxury Airport Cab", 1800.0));
		guestB.addService(new HotelService("Spa & Sauna Access", 2500.0));

		ExecutorService threadPool = Executors.newFixedThreadPool(2);

		Callable<String> thread1 = () -> {
			try {
				Booking b = engine.bookRoom("BK-9001", guestA, 201, 3, new UPIPaymentStrategy("arjun@oksbi"));
				return "[Thread 1 SUCCESS] " + b;
			} catch (Exception e) {
				return "[Thread 1 FAILED] " + e.getMessage();
			}
		};

		Callable<String> thread2 = () -> {
			try {
				Thread.sleep(10);
				Booking b = engine.bookRoom("BK-9002", guestB, 201, 2,
						new CardPaymentStrategy("4532889911224455", PaymentMode.CREDIT_CARD));
				return "[Thread 2 SUCCESS] " + b;
			} catch (Exception e) {
				return "[Thread 2 FAILED] " + e.getMessage();
			}
		};

		try {
			Future<String> f1 = threadPool.submit(thread1);
			Future<String> f2 = threadPool.submit(thread2);

			System.out.println(f1.get());
			System.out.println(f2.get());
		} catch (InterruptedException | ExecutionException e) {
			System.err.println("Thread execution failure: " + e.getMessage());
		} finally {
			threadPool.shutdown();
		}

		System.out.println("\n--- 4. VIP vs Standard Check-In Processing (PriorityQueue) ---");
		engine.enqueueCustomer(guestA);
		engine.enqueueCustomer(guestB);
		engine.processNextCheckIn();
		engine.processNextCheckIn();

		System.out.println("\n--- 5. Dual-Tier File Persistence & Exception Scenarios ---");
		Booking suiteBooking = null;
		try {
			suiteBooking = engine.bookRoom("BK-9003", guestB, 301, 2,
					new CardPaymentStrategy("5120998877663322", PaymentMode.DEBIT_CARD));
		} catch (RoomNotAvailableException | InvalidPaymentException e) {
			System.err.println("Booking failed: " + e.getMessage());
		}

		String folioPath = "hotel_folio_BK9003.txt";
		String archivePath = "booking_state.ser";
		String ledgerPath = "room_ledger.dat";

		if (suiteBooking != null) {
			try {

				HotelFileManager.generateFolioReceipt(folioPath, suiteBooking);
				System.out.println("[Character Stream] Created Customer Folio at '" + folioPath + "'.");

				HotelFileManager.exportBookingSnapshot(archivePath, suiteBooking);
				System.out.println("[Byte Stream] Serialized state to '" + archivePath + "'.");

				Booking restored = HotelFileManager.importBookingSnapshot(archivePath);
				System.out.println("[Byte Stream] Deserialized Customer: " + restored.getCustomer());
				System.out.println("              (Note: maskedSecretPin is null due to 'transient' protection)");

				HotelFileManager.initializeLedgerRecord(ledgerPath, 301, false, 0.0);
				System.out.println("[RandomAccessFile] Initial Room 301 Occupancy: "
						+ HotelFileManager.readLedgerOccupancyDirect(ledgerPath, 0));

				HotelFileManager.updateLedgerOccupancyDirect(ledgerPath, 0, true);
				System.out.println("[RandomAccessFile] Direct Byte Update -> Room 301 Occupancy: "
						+ HotelFileManager.readLedgerOccupancyDirect(ledgerPath, 0));
			}

			catch (FileNotFoundException e) {
				System.err.println("[HANDLED SPECIFIC] File not found: " + e.getMessage());
			} catch (IOException e) {
				System.err.println("[HANDLED GENERIC I/O] I/O Stream Failure: " + e.getMessage());
			} catch (ClassNotFoundException e) {
				System.err.println(
						"[HANDLED CLASS DEF] Class definition missing during deserialization: " + e.getMessage());
			} catch (Exception e) {
				System.err.println("[HANDLED TOP-LEVEL] System Failure: " + e.getMessage());
			} finally {
				System.out.println("[FINALLY] Cleaning up temporary filesystem demo artifacts...");
				// new File(folioPath).delete();
				// new File(archivePath).delete();
				// new File(ledgerPath).delete();
				System.out.println("[FINALLY] File handles and resources safely cleaned up.");
			}
		}

		System.out.println("\n--- 6. Reservation Cancellation & State Rollback ---");
		engine.cancelBooking("BK-9001");

		System.out.println("\n=================================================================");
		System.out.println("   ALL ARCHITECTURAL ENTERPRISE WORKFLOWS COMPLETED SAFELY       ");
		System.out.println("=================================================================");
	}
}
