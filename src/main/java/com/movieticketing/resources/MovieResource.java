package com.movieticketing.resources;

import com.movieticketing.bo.MovieBO;
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

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createMovie(Movie movie) {
		ResultBean result = movieBo.createMovie(movie);
		return Response.status(result.getStatus()).entity(result).build();
	}

	@GET
	@Path("{movieId}")
	public Response getMovie(@PathParam("movieId") String movieId) {

		System.out.println("Movie:" + movieId);
		ResultBean result = movieBo.getMovie(movieId);
		return Response.status(result.getStatus()).entity(result).build();
	}

}
