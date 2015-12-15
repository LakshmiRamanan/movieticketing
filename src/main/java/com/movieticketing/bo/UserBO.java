package com.movieticketing.bo;

import com.movieticketing.common.TicketConfirmationDetails;
import com.movieticketing.common.UserDetails;
import com.movieticketing.model.ResultBean;

public interface UserBO {

	ResultBean confirmTicket(TicketConfirmationDetails ticket);

	ResultBean createUser(UserDetails user);
}