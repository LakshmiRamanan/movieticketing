package com.movieticketing.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.movieticketing.common.ShowDetails;
import com.movieticketing.common.TheatreDetails;
import com.movieticketing.dao.TheatreDao;
import com.movieticketing.model.Login;
import com.movieticketing.model.Screens;
import com.movieticketing.model.Shows;
import com.movieticketing.model.Theatre;

public class TheatreDaoImpl extends HibernateDaoSupport implements TheatreDao {

	@Override
	public List getTheatre(String userid) {
		return getHibernateTemplate().find("from Theatre where userId = ?", userid);
	}

	@Override
	public Boolean createTheatre(TheatreDetails theatre) {
		try {
			Theatre theatreDB = new Theatre();
			theatreDB.setTheatreId(theatre.getTheatreId());
			theatreDB.setName(theatre.getName());
			theatreDB.setLocation(theatre.getLocation());
			theatreDB.setPhone(theatre.getPhone());
			getHibernateTemplate().save(theatreDB);

			Login login = new Login();
			login.setUserId(theatre.getTheatreId());
			login.setPassword(theatre.getPassword());
			login.setRole("TADMIN");
			getHibernateTemplate().save(login);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean createScreen(Screens screens) {
		getHibernateTemplate().save(screens);
		return true;
	}

	@Override
	public Boolean createShows(ShowDetails shows) {
		List result = getHibernateTemplate().find("from Screens where userId = ? and screen = ?", shows.getUserId(),
				shows.getScreen());
		try {
			if (result != null && result.size() == 1) {
				Screens screen = (Screens) result.get(0);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				Date startDate = sdf.parse(shows.getStartDate());

				Date endDate = sdf.parse(shows.getEndDate());
				Calendar start = Calendar.getInstance();
				start.setTime(startDate);
				Calendar end = Calendar.getInstance();
				end.setTime(endDate);

				while (!start.after(end)) {
					Date targetDay = start.getTime();
					for (int i = 1; i <= 4; i++) {
						Shows show = new Shows();
						show.setMovieId(shows.getMovieId());
						show.setTheatreId(shows.getUserId());
						show.setScreen(shows.getScreen());
						show.setShowName(Integer.toString(i));
						show.setDate(new java.sql.Date(targetDay.getTime()));
						show.setSeat(screen.getSeat());
						getHibernateTemplate().save(show);
					}
					start.add(Calendar.DATE, 1);
				}
				return true;

			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List getMoviesByTheatre(String theatreId) {
		List lst = getHibernateTemplate().find("from Movie where movieId in ( select distinct movieId from Shows where theatreId = ?)", theatreId);
		return lst;
	}

	@Override
	public List getAllMovies() {
		List lst = getHibernateTemplate().find("from Movie where movieId in ( select distinct movieId from Shows )");
		return lst;
	}

}