package com.movieticketing.dao;

import java.util.List;

import com.movieticketing.common.ShowDetails;
import com.movieticketing.common.TheatreDetails;
import com.movieticketing.model.Screens;

public interface TheatreDao {

	List getTheatre(String userid);
	
	Boolean createTheatre(TheatreDetails theatre);

	Boolean createScreen(Screens screens);

	Boolean createShows(ShowDetails shows);

	List getMoviesByTheatre(String theatreId);

	List getAllMovies();
	
}