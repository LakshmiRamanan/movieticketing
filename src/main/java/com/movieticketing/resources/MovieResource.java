package com.movieticketing.resources;

import com.movieticketing.bo.MovieBO;
import com.movieticketing.common.MemcachedInstance;
import com.movieticketing.memcached.MemcachedClient;
import com.movieticketing.model.Movie;
import com.movieticketing.model.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by nagal_000 on 12/13/2015.
 */
@Component
@Path("/movie")
@Produces(MediaType.APPLICATION_JSON)
public class MovieResource {
	@Autowired
	MovieBO movieBo;

	MemcachedClient memcachedClient = MemcachedInstance.getMemcachedMovieInstance();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createMovie(Movie movie) {
		ResultBean result = movieBo.createMovie(movie);
		if (memcachedClient != null && memcachedClient.get("getAllMoviesInTheatres") != null) {
			memcachedClient.delete("getAllMoviesInTheatres");
		}
		if (memcachedClient != null && memcachedClient.get("getAllMovies") != null) {
			memcachedClient.delete("getAllMovies");
		}
		return Response.status(result.getStatus()).entity(result).build();
	}

	@GET
	@Path("{movieId}")
	public Response getMovie(@PathParam("movieId") String movieId) {
		System.out.println("Movie:" + movieId);
		ResultBean result = null;
		if (memcachedClient != null && memcachedClient.get(movieId) != null) {
			System.out.println("Getting movie from cache");
			result = (ResultBean) memcachedClient.get(movieId);
		} else {
			System.out.println("Retrieving data for movie from DB");
			result = movieBo.getMovie(movieId);
			memcachedClient.add(movieId, result);
		}
		return Response.status(result.getStatus()).entity(result).build();
	}

	@GET
	public Response getAllMovies() {
		ResultBean result = null;
		System.out.println("Get all the movies present in the server");
		if (memcachedClient != null && memcachedClient.get("getAllMovies") != null) {
			System.out.println("Getting all movies from cache");
			result = (ResultBean) memcachedClient.get("getAllMovies");
		} else {
			System.out.println("Retrieving data for all movies from DB");
			result = movieBo.getAllMovie();
			memcachedClient.add("getAllMovies", result);
		}
		return Response.status(result.getStatus()).entity(result).build();
	}

}
