package com.movieticketing.dao.impl;

import com.movieticketing.dao.MovieDao;
import com.movieticketing.model.Movie;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by nagal_000 on 12/13/2015.
 */
public class MovieDaoImpl extends HibernateDaoSupport implements MovieDao {

	@Override
	public Boolean createMovie(Movie movie) {
		try {
			getHibernateTemplate().save(movie);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List getMovie(String movieId) {
		return getHibernateTemplate().find("from Movie where movieId = ?", movieId);

	}
}
