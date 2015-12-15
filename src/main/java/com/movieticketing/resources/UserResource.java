package com.movieticketing.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.movieticketing.bo.UserBO;
import com.movieticketing.common.TicketConfirmationDetails;
import com.movieticketing.common.UserDetails;
import com.movieticketing.model.ResultBean;
import com.movieticketing.model.Ticket;

@Component
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

	@Autowired
	UserBO userBO;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response confirmTicket(TicketConfirmationDetails ticket) {
		ResultBean result = userBO.confirmTicket(ticket);
		return Response.status(result.getStatus()).entity(result).build();
	}
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(UserDetails user){
		ResultBean result = userBO.createUser(user);
		return Response.status(result.getStatus()).entity(result).build();
	}
	
}
