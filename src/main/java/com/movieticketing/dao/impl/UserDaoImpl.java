package com.movieticketing.dao.impl;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.movieticketing.common.TicketConfirmationDetails;
import com.movieticketing.common.UserDetails;
import com.movieticketing.dao.UserDao;
import com.movieticketing.model.Login;
import com.movieticketing.model.Shows;
import com.movieticketing.model.Ticket;
import com.movieticketing.model.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Override
	public String confirmTicket(TicketConfirmationDetails ticket) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date;

			date = sdf.parse(ticket.getDate());

			java.sql.Date sqlDate = new java.sql.Date(date.getTime());

			List result = getHibernateTemplate().find(
					"from Shows where userId = ? and screen = ? and showName = ? and movieId = ? and date = ?",
					ticket.getTheatreId(), ticket.getScreen(), ticket.getShow(), ticket.getMovieId(), sqlDate);
			System.out.println("Result size:" + result.size());
			if (result != null && result.size() == 1) {
				Shows shows = (Shows) result.get(0);
				System.out.println("Seats:" + shows.getSeat());
				if (shows.getSeat() > 0) {
					int remainingSeats = shows.getSeat() - ticket.getSeatCount();
					System.out.println(remainingSeats);
					if (remainingSeats >= 0) {

						SecureRandom random = new SecureRandom();
						String confCode = new BigInteger(130, random).toString(32);

						Ticket userTicket = new Ticket();
						userTicket.setUserId(ticket.getUserId());
						userTicket.setTheatreId(ticket.getTheatreId());
						userTicket.setScreen(ticket.getScreen());
						userTicket.setShow(ticket.getShow());
						userTicket.setSeatCount(ticket.getSeatCount());
						userTicket.setDate(sqlDate);
						userTicket.setMovieId(ticket.getMovieId());
						userTicket.setConfCode(confCode);
						getHibernateTemplate().save(userTicket);

						shows.setSeat(remainingSeats);
						getHibernateTemplate().merge(shows);
						return confCode;
					}

				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Boolean createUser(UserDetails user) {
		try {
			User usr = new User();
			usr.setUserId(user.getUserId());
			usr.setPhone(user.getPhoneNumber());
			usr.setName(user.getName());
			getHibernateTemplate().save(usr);
			
			Login login = new Login();
			login.setUserId(user.getUserId());
			login.setPassword(user.getPassword());
			login.setRole("USER");
			getHibernateTemplate().save(login);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
