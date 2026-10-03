package com.movieticket.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.movieticket.model.Booking;
import com.movieticket.model.Movie;
import com.movieticket.model.Show;
import com.movieticket.model.Theatre;
import com.movieticket.model.User;

public class DataRepository {
	
	/*Movie movie = new Movie.MovieBuilder("M-101", "KGF-3", "Kannada", Genre.ACTION).duration(180).rating(4.9)
			.director("Prashanth Neel").build();
	repository.movies.put(movie.getId(), movie);*/

	public final Map<String, User> users = new ConcurrentHashMap<>();
	public final Map<String, Movie> movies = new ConcurrentHashMap<>();
	public final Map<String, Theatre> theatres = new ConcurrentHashMap<>();
	public final Map<String, Show> shows = new ConcurrentHashMap<>();
	public final Map<String, Booking> bookings = new ConcurrentHashMap<>();

}
