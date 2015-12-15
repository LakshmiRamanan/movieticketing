package com.movieticketing.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.movieticketing.bo.UserBO;
import com.movieticketing.common.TicketConfirmationDetails;
import com.movieticketing.common.UserDetails;
import com.movieticketing.dao.UserDao;
import com.movieticketing.model.ResultBean;

@Controller
public class UserBOImpl implements UserBO {

	@Autowired
	UserDao userDao;

	@Override
	public ResultBean confirmTicket(TicketConfirmationDetails ticket) {
		String confCode = userDao.confirmTicket(ticket);
		List<String> rows = new ArrayList<String>();
		ResultBean rb = new ResultBean();
		if (confCode != null) {
			rb.setStatus(200);
			rows.add("\"code\":\"" + confCode + "\"");
			rb.setRows(rows);
		} else {
			rb.setStatus(400);
		}
		return rb;
	}

	@Override
	public ResultBean createUser(UserDetails user) {
		ResultBean rb = new ResultBean();
		Boolean result = userDao.createUser(user);
		if (result) {
			rb.setStatus(200);
		} else {
			rb.setStatus(400);
		}
		return rb;
	}

}
