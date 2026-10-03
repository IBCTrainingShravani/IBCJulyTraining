package com.movieticket.facade;

import java.util.List;
import java.util.Optional;

import com.movieticket.decorator.BaseTicket;
import com.movieticket.decorator.CancellationInsuranceDecorator;
import com.movieticket.decorator.Glasses3DDecorator;
import com.movieticket.decorator.PopcornComboDecorator;
import com.movieticket.decorator.TicketComponent;
import com.movieticket.enums.PaymentMethod;
import com.movieticket.model.Booking;
import com.movieticket.model.Ticket;
import com.movieticket.service.BookingService;

public class BookingFacade {
	private final BookingService bookingService;

	public BookingFacade(BookingService bookingService) {
		super();
		this.bookingService = bookingService;
	}

	/*
	 * Optional<Ticket> ticketOpt =
	 * bookingFacade.bookMovieTicketWithAddons(winningUserId, "SH-501",
	 * Collections.singletonList("A1"), PaymentMethod.UPI, true, // Add Popcorn
	 * true, // Add 3D Glasses false // Add Insurance );
	 */

	public Optional<Ticket> bookMovieTicketWithAddons(String userId, String showId, List<String> seatIds,
			PaymentMethod paymentMethod, boolean addPopcorn, boolean addGlasses, boolean addInsurance) {

		System.out.println("\n Facade Orchestrating ticket reservation Bookingw..");

		Optional<Booking> bookingOpt = bookingService.reserveSeats(userId, showId, seatIds);
		System.out.println("booking option:" + bookingOpt);
		if (!bookingOpt.isPresent()) {
			return Optional.empty();
		}
		Booking booking = bookingOpt.get();
		System.out.println("booking :" + booking);
		TicketComponent itemizedBill = new BaseTicket(seatIds.toString(), booking.getTotalAmount());

		if (addPopcorn) {
			itemizedBill = new PopcornComboDecorator(itemizedBill);

		}
		if (addGlasses) {
			itemizedBill = new Glasses3DDecorator(itemizedBill);
		}
		if (addInsurance) {
			itemizedBill = new CancellationInsuranceDecorator(itemizedBill);
		}

		booking.setTotalAmount(itemizedBill.getCost());

		System.out.println("Itemeized bill:" + itemizedBill.getDescription());

		System.out.println("Final Price:" + itemizedBill.getCost());

		return bookingService.confirmBooking(booking.getBookingId(), paymentMethod);

	}

}
