package com.movieticket.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import com.movieticket.enums.BookingStatus;
import com.movieticket.enums.PaymentMethod;
import com.movieticket.enums.PaymentStatus;
import com.movieticket.enums.SeatStatus;
import com.movieticket.model.Booking;
import com.movieticket.model.Customer;
import com.movieticket.model.Payment;
import com.movieticket.model.Show;
import com.movieticket.model.ShowSeat;
import com.movieticket.model.Ticket;
import com.movieticket.model.User;
import com.movieticket.notification.EmailNotificationService;
import com.movieticket.notification.NotificationService;
import com.movieticket.notification.SMSNotificationService;
import com.movieticket.repository.DataRepository;
import com.movieticket.strategy.PaymentStrategy;
import com.movieticket.strategy.PaymentStrategyFactory;

public class BookingService {
	private final DataRepository repository;
	private final NotificationService emailNotifier;
	private final NotificationService smsNotifier;
	private static final int LOCK_TIMEOUT_MINUTES = 5;

	public BookingService(DataRepository repository) {
		super();
		this.repository = repository;
		this.emailNotifier = new EmailNotificationService();
		this.smsNotifier = new SMSNotificationService();
	}

	/*
	 * Optional<Ticket> ticketOpt =
	 * bookingFacade.bookMovieTicketWithAddons(winningUserId, "SH-501",
	 * Collections.singletonList("A1"), PaymentMethod.UPI, true, // Add Popcorn
	 * true, // Add 3D Glasses false // Add Insurance );
	 *
	 * 
	 */

	public Optional<Booking> reserveSeats(String userId, String showId, List<String> seatId) {

		System.out.println("user ID:" + userId);

		System.out.println("show ID:" + showId);
		User user = repository.users.get(userId);
		System.out.println("user:" + user);
		Show show = repository.shows.get(showId);
		System.out.println("show:" + show);

		/*
		 * Facade Orchestrating ticket reservation Bookingw.. user ID:USR-9 show
		 * ID:SH-501 user:User [id=USR-9, name=Customer_9, email=user9@example.com,
		 * phone=987654329, passwordHash=password, role=CUSTOMER] show:Show [id=SH-501,
		 * movie=Movie [id=M-101, title=KGF-3, language=Kannada, genre=ACTION,
		 * durationInMinutes=180, rating=4.9, releaseDate=2026-08-17T20:58:39.686047300,
		 * director=Prashanth Neel], theatre=Theatre [id=TH-1, name=PVR Cinemas,
		 * city=Bengaluru, location=Koramangala, screens=[Screen [id=SCR-1, name=IMAX
		 * Screen 1, seats={A1=Seat [seatId=A1, row=1, column=1, seatType=REGULAR,
		 * price=200.0], A2=Seat [seatId=A2, row=1, column=2, seatType=REGULAR,
		 * price=200.0], A3=Seat [seatId=A3, row=1, column=3, seatType=PREMIUM,
		 * price=350.0]}]]], screen=Screen [id=SCR-1, name=IMAX Screen 1, seats={A1=Seat
		 * [seatId=A1, row=1, column=1, seatType=REGULAR, price=200.0], A2=Seat
		 * [seatId=A2, row=1, column=2, seatType=REGULAR, price=200.0], A3=Seat
		 * [seatId=A3, row=1, column=3, seatType=PREMIUM, price=350.0]}],
		 * startTime=2026-08-17T22:58:39.690500800,
		 * endTime=2026-08-18T01:58:39.690500800, showSeats={A1=ShowSeat [seatId=A1,
		 * seatType=REGULAR, price=200.0, status=LOCKED, lockedByUserId=USR-9,
		 * lockTimestamp=null], A2=ShowSeat [seatId=A2, seatType=REGULAR, price=200.0,
		 * status=AVAILABLE, lockedByUserId=null, lockTimestamp=null], A3=ShowSeat
		 * [seatId=A3, seatType=PREMIUM, price=350.0, status=AVAILABLE,
		 * lockedByUserId=null, lockTimestamp=null]}, waitingListQueue=[USR-14, USR-6,
		 * USR-11, USR-12, USR-7, USR-5, USR-17, USR-20, USR-3, USR-2, USR-8, USR-1,
		 * USR-4, USR-13, USR-10, USR-15, USR-18, USR-19, USR-37, USR-16, USR-21,
		 * USR-23, USR-22, USR-24, USR-25, USR-26, USR-27, USR-28, USR-29, USR-30,
		 * USR-31, USR-32, USR-33, USR-34, USR-35, USR-36, USR-38, USR-39, USR-40,
		 * USR-41, USR-42, USR-43, USR-44, USR-45, USR-46, USR-47, USR-48, USR-49,
		 * USR-50],
		 * showLock=java.util.concurrent.locks.ReentrantLock@1e55019c[Unlocked]]
		 * 
		 * Lock Failed for User :[Customer_9]:Requested seats[A1]Already Locked.
		 * ->Customer[Customer_9]enqueued into waiting list for show:SH-501
		 */

		if (user == null || !(user instanceof Customer) || show == null) {
			System.out.println("Booking Refused");

			return Optional.empty();
		}

		ReentrantLock showLock = show.getShowLock();
		showLock.lock();

		try {
			Map<String, ShowSeat> showSeats = show.getShowSeats();

			showSeats.values().stream().filter(seat -> seat.isLockExpired(LOCK_TIMEOUT_MINUTES))
					.forEach(ShowSeat::releaseLock);

			boolean allAvailable = seatId.stream().allMatch(seatIds -> {
				ShowSeat seat = showSeats.get(seatIds);
				return seat != null && seat.getStatus() == SeatStatus.AVAILABLE;
			});

			if (!allAvailable) {
				System.out.println(
						"Lock Failed for User :[" + user.getName() + "]:Requested seats" + seatId + "Already Locked.");

				show.getWaitingListQueue().add(userId);
				System.out.println("->Customer[" + user.getName() + "]enqueued into waiting list for show:" + showId);

				return Optional.empty();
			}

			List<ShowSeat> reservedSeats = new ArrayList<>();
			for (String seatIds : seatId) {
				ShowSeat seat = showSeats.get(seatIds);

				seat.lockSeat(userId);
				reservedSeats.add(seat);

			}

			double totalAmount = reservedSeats.stream().mapToDouble(ShowSeat::getPrice).sum();

			String bookingId = "BK-" + UUID.randomUUID().toString().substring(0, 8);

			Booking booking = new Booking(bookingId, (Customer) user, show, reservedSeats, totalAmount);

			repository.bookings.put(bookingId, booking);

			((Customer) user).addBooking(booking);

			System.out.println("SUCCESS: seats:" + seatId + "locked for 5 mins for user[" + user.getName()
					+ "].Booking Id:" + bookingId);

			return Optional.of(booking);

		} finally {
			showLock.unlock();
		}

	}

	public Optional<Ticket> confirmBooking(String bookingId, PaymentMethod paymentMethod) {

		Booking booking = repository.bookings.get(bookingId);

		if (booking == null || booking.getStatus() != BookingStatus.PENDING) {

			System.out.println("Confirmation Failed:");
			return Optional.empty();
		}

		PaymentStrategy paymentStrategy = PaymentStrategyFactory.getStrategy(paymentMethod);

		boolean paymentSuccess = paymentStrategy.processPayment(bookingId, booking.getTotalAmount());

		ReentrantLock showLock = booking.getShow().getShowLock();

		showLock.lock();

		try {
			if (paymentSuccess) {
				booking.setStatus(BookingStatus.CONFIRMED);

				booking.getBookedSeats().forEach(seat -> seat.setStatus(SeatStatus.BOOKED));

				Payment payment = new Payment("PAY-" + UUID.randomUUID().toString().substring(0, 8), bookingId,
						booking.getTotalAmount(), paymentMethod);

				payment.setStatus(PaymentStatus.SUCCESSFUL);

				booking.setPayment(payment);

				String ticketId = "TCK-" + UUID.randomUUID().toString().substring(0, 8);

				List<String> seatIds = booking.getBookedSeats().stream().map(ShowSeat::getSeatId)
						.collect(Collectors.toList());

				Ticket ticket = new Ticket(ticketId, bookingId, booking.getShow().getMovie().getTitle(),
						booking.getShow().getTheatre().getName(), seatIds, booking.getShow().getStartTime());

				booking.setTicket(ticket);

				String msg = "Booking Confirmed! Ticket Id:" + ticketId + "for movie"
						+ booking.getShow().getMovie().getTitle();

				emailNotifier.sendNotification(booking.getCustomer(), msg);
				smsNotifier.sendNotification(booking.getCustomer(), msg);

				return Optional.of(ticket);
			} else {
				booking.setStatus(BookingStatus.CANCELLED);

				booking.getBookedSeats().forEach(ShowSeat::releaseLock);
				System.out.println("Payment Failed:" + bookingId);
				return Optional.empty();
			}

		} finally {
			showLock.unlock();

		}

	}

	public boolean cancelBooking(String bookingId) {
		Booking booking = repository.bookings.get(bookingId);

		if (booking == null || booking.getStatus() != BookingStatus.CONFIRMED) {
			System.out.println("Cancellation Failed:Booking not found or not confirmed");
			return false;

		}

		if (LocalDateTime.now().isAfter(booking.getShow().getStartTime())) {
			System.out.println("Cancellation Failed:Show has alreadt started or completed");
			return false;

		}

		ReentrantLock showLock = booking.getShow().getShowLock();
		showLock.lock();
		try {
			booking.setStatus(BookingStatus.CANCELLED);

			booking.getBookedSeats().forEach(ShowSeat::releaseLock);

			PaymentStrategy paymentStrategy = PaymentStrategyFactory.getStrategy(PaymentMethod.UPI);

			paymentStrategy.processRefund(bookingId, booking.getTotalAmount());

			System.out.println("SUCCESS booking:" + bookingId + "cancelled and seats released.");

			ConcurrentLinkedQueue<String> queue = booking.getShow().getWaitingListQueue();

			if (!queue.isEmpty()) {
				String nextUserId = queue.poll();
				User nextUser = repository.users.get(nextUserId);
				if (nextUser != null) {
					emailNotifier.sendNotification(nextUser, "PRIORITY ALERT:Seats realeased for Show:"
							+ booking.getShow().getMovie().getTitle() + "!Book now");

				}
			}
			return true;
		} finally {
			showLock.unlock();
		}

	}

}
