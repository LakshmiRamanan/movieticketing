package com.movieticketing.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.movieticketing.bo.TheatreBO;
import com.movieticketing.common.ShowDetails;
import com.movieticketing.common.TheatreDetails;
import com.movieticketing.model.ResultBean;
import com.movieticketing.model.Screens;

@Component
@Path("/theatre")
@Produces(MediaType.APPLICATION_JSON)
public class TheatreResource {

	@Autowired
	TheatreBO theatreBO;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTheatre(TheatreDetails theatre) {
		ResultBean result = theatreBO.createTheatre(theatre);
		return Response.status(result.getStatus()).entity(result).build();
	}

	@GET
	@Path("{theatreId}")
	public Response getTheatre(@PathParam("theatreId") String theatreId) {
		ResultBean result = theatreBO.getTheatre(theatreId);
		return Response.status(result.getStatus()).entity(result).build();
	}

	@POST
	@Path("/screens")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createScreen(Screens screens) {
		ResultBean result = theatreBO.createScreen(screens);
		return Response.status(result.getStatus()).entity(result).build();
	}

	@POST
	@Path("/shows")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createShows(ShowDetails shows) {
		ResultBean result = theatreBO.createShows(shows);
		return Response.status(result.getStatus()).entity(result).build();
	}

	@GET
	@Path("/movies/{theatreId}")
	public Response getMovies(@PathParam("theatreId") String theatreId) {
		ResultBean result = theatreBO.getMoviesByTheatre(theatreId);
		return Response.status(result.getStatus()).entity(result).build();
	}

	@GET
	@Path("/movies")
	public Response getAllMovies() {
		ResultBean result = theatreBO.getAllMovies();
		return Response.status(result.getStatus()).entity(result).build();
	}

}
