package com.movieticketing.dao;

import com.movieticketing.common.TicketConfirmationDetails;
import com.movieticketing.common.UserDetails;

public interface UserDao {
	
	String confirmTicket(TicketConfirmationDetails ticket);

	Boolean createUser(UserDetails user);

}
