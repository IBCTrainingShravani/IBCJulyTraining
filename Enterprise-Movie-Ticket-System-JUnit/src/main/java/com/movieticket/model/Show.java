package com.movieticket.model;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

public class Show {
	private final String id;
	private final Movie movie;
	private final Theatre theatre;
	private final Screen screen;
	private final LocalDateTime startTime;
	private final LocalDateTime endTime;
	private final Map<String, ShowSeat> showSeats;
	private final ConcurrentLinkedQueue<String> waitingListQueue;
	private final ReentrantLock showLock;
	
	/*Movie movie = new Movie.MovieBuilder("M-101", "KGF-3", "Kannada", Genre.ACTION).duration(180).rating(4.9)
			.director("Prashanth Neel").build();
	repository.movies.put(movie.getId(), movie);

	List<Seat> screenSeats = Arrays.asList(new Seat("A1", 1, 1, SeatType.REGULAR, 200.0),
			new Seat("A2", 1, 2, SeatType.REGULAR, 200.0), new Seat("A3", 1, 3, SeatType.PREMIUM, 350.0));

	Screen screen = new Screen("SCR-1", "IMAX Screen 1", screenSeats);
	Theatre theatre = new Theatre("TH-1", "PVR Cinemas", "Bengaluru", "Koramangala",
			Collections.singletonList(screen));
	repository.theatres.put(theatre.getId(), theatre);

	Show show = new Show("SH-501", movie, theatre, screen, LocalDateTime.now().plusHours(2),
			LocalDateTime.now().plusHours(5));*/
	

	public Show(String id, Movie movie, Theatre theatre, Screen screen, LocalDateTime startTime,
			LocalDateTime endTime) {
		this.id = id;
		this.movie = movie;
		this.theatre = theatre;
		this.screen = screen;
		this.startTime = startTime;
		this.endTime = endTime;
		this.waitingListQueue = new ConcurrentLinkedQueue<>();
		this.showLock = new ReentrantLock();

		this.showSeats = new ConcurrentHashMap<>();
		screen.getSeats().forEach((seatId, seat) -> this.showSeats.put(seatId,
				new ShowSeat(seat.getSeatId(), seat.getSeatType(), seat.getPrice())));
	}

	public String getId() {
		return id;
	}

	public Movie getMovie() {
		return movie;
	}

	public Theatre getTheatre() {
		return theatre;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public Map<String, ShowSeat> getShowSeats() {
		return showSeats;
	}

	public ConcurrentLinkedQueue<String> getWaitingListQueue() {
		return waitingListQueue;
	}

	public ReentrantLock getShowLock() {
		return showLock;
	}

	@Override
	public String toString() {
		return "Show [id=" + id + ", movie=" + movie + ", theatre=" + theatre + ", screen=" + screen + ", startTime="
				+ startTime + ", endTime=" + endTime + ", showSeats=" + showSeats + ", waitingListQueue="
				+ waitingListQueue + ", showLock=" + showLock + "]";
	}
	
}
